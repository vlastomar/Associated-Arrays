import java.util.*;
import java.util.stream.Collectors;

public class SoftUniExamResults {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

            HashMap<String, List<String>> studentsLanguage = new HashMap<>();
        TreeMap<String, Integer> studntsPoints = new TreeMap<>();

        String input = scan.nextLine();
        while (!"exam finished".equals(input)){
            String[] tokens = input.split("-");
            String username = tokens[0];
            String language = tokens[1];
            //int checkLang = 0;

            if ("banned".equals(language)){
                studntsPoints.remove(username);
            }else{
                int points = Integer.parseInt(tokens[2]);
                studentsLanguage.putIfAbsent(username, new ArrayList<>());
                studentsLanguage.get(username).add(language);
                studntsPoints.putIfAbsent(username, 0);
                if (studntsPoints.get(username) < points){
                    studntsPoints.put(username, points);
                }
            }

            input = scan.nextLine();
        }
        System.out.println("Results:");
        studntsPoints.entrySet()
                .stream()

                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())/*{
                    int res = e2.getValue().compareTo(e1.getValue());
                    if (res == 0){
                        e1.getKey().compareTo(e2.getKey());
                    }
                    return res;
                }*/)
                .forEach(f -> {

                    System.out.println(f.getKey() + " | " + f.getValue());
                });
        //List<String> lang = new ArrayList<>();
        TreeMap<String, Integer> lang = new TreeMap<>();
        int sum = 1;
        for (Map.Entry<String, List<String>> entry : studentsLanguage.entrySet()) {
            for (int i = 0; i < entry.getValue().size() ; i++) {
                String temp = entry.getValue().get(i);
                 lang.putIfAbsent(temp,0);
                 lang.put(temp, lang.get(temp) + 1);
            }
        }
        System.out.println("Submissions:");
        lang.entrySet().stream()
                .forEach(f -> {

                    System.out.printf("%s - %d%n", f.getKey(), f.getValue());
                });
    }
}
