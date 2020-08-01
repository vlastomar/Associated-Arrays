import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WordSynonyms {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int num = Integer.parseInt(scan.nextLine());
        LinkedHashMap<String, ArrayList<String>> words = new LinkedHashMap<>();

        for (int i = 0; i < num  ; i++) {
            String word = scan.nextLine();
            String synonym = scan.nextLine();
            /*if (!words.containsKey(word)){
                words.put(word, synonym);
            }else{

            }*/
            words.putIfAbsent(word, new ArrayList<>());
            words.get(word).add(synonym);
        }
        for (Map.Entry<String, ArrayList<String>> entry : words.entrySet()) {

            System.out.printf("%s - %s%n", entry.getKey(), String.join(", ", entry.getValue()));
        }
    }
}
