import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class OddOccurrences {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] words = scan.nextLine().split("\\s+");

        LinkedHashMap<String, Integer> counts = new LinkedHashMap<>();

        for (String word : words){
            String wordLower = word.toLowerCase();
            if (!counts.containsKey(wordLower)){
                counts.put(wordLower, 1);
            }else   {
                counts.put(wordLower,counts.get(wordLower) + 1);
            }
        }
        ArrayList<String> oddOccurWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() % 2 != 0){
                oddOccurWords.add(entry.getKey());
            }
        }
        for (int i = 0; i < oddOccurWords.size(); i++){
            System.out.print(oddOccurWords.get(i));
            if (i < oddOccurWords.size() -1){
                System.out.print(", ");
            }

        }

    }
}
