import java.util.*;

public class MinerTask {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> words = new ArrayList<>();
        List<Integer> number = new ArrayList<>();
        LinkedHashMap<String,Integer> resources = new LinkedHashMap<>();

        String input = scan.nextLine();
        int counter = 1;
        while (!"stop".equals(input)){

            if (counter % 2 != 0){
                words.add(input);
            }else{
                number.add(Integer.parseInt(input));
            }

            counter++;
            input = scan.nextLine();

        }

        for (int i = 0; i < words.size() ; i++) {
            String temp = words.get(i);
            int numb = number.get(i);
            if (resources.containsKey(temp)){
                resources.put(temp, resources.get(temp) + numb);
            }else {
                resources.put(temp, numb);
            }
        }

        for (Map.Entry<String, Integer> entry : resources.entrySet()) {
            System.out.printf("%s -> %d%n", entry.getKey(), entry.getValue());
        }
    }
}
