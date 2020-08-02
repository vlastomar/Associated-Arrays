import java.util.*;
import java.util.stream.Collectors;

public class CountCharsinString {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> letters = Arrays.stream(scan.nextLine().split(""))
                .filter(e -> !e.equals(" "))
                .collect(Collectors.toList());
        LinkedHashMap <String, Integer> letterOccur = new LinkedHashMap<>();

        for(String lett : letters){

                if (!letterOccur.containsKey(lett)){
                    letterOccur.put(lett, 1);
                }else{
                    letterOccur.put(lett, letterOccur.get(lett) + 1);
                }

        }
        for (Map.Entry<String, Integer> entry : letterOccur.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }

    }
}
