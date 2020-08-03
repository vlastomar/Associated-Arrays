import java.sql.SQLOutput;
import java.util.*;

public class Ranking {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        HashMap<String, String> contests = new HashMap<>();
        TreeMap<String,List<String>> users = new TreeMap<>();

        String input = scan.nextLine();
        while (!"end of contests".equals(input)){
            String[] tokens = input.split(":");
            contests.put(tokens[0], tokens[1]);

            input = scan.nextLine();
        }

        int sum = Integer.MIN_VALUE;
        String name = "";
        input = scan.nextLine();
        while (!"end of submissions".equals(input)){
            String[] tok = input.split("=>");
            if (contests.containsKey(tok[0])){
                if(contests.get(tok[0]).equals(tok[1])){
                    users.putIfAbsent(tok[2], new ArrayList<>());

                    if (users.get(tok[2]).contains(tok[0])){
                        int index = users.get(tok[2]).indexOf(tok[0]);
                        int savedPoints = Integer.parseInt(users.get(tok[2]).get(index + 1));
                        int newPoints = Integer.parseInt(tok[3]);
                        if (newPoints > savedPoints){
                            users.get(tok[2]).set(index + 1, tok[3]);
                        }
                    }else {
                        users.get(tok[2]).add(tok[0]);
                        users.get(tok[2]).add(tok[3]);
                    }
                }
            }

            input = scan.nextLine();
        }

        for (Map.Entry<String, List<String>> entry : users.entrySet()) {
            int tmpSum = 0;
            for (int i = 0; i <entry.getValue().size() ; i++) {
                if (i % 2 != 0){
                    tmpSum += Integer.parseInt(entry.getValue().get(i));
                }
            }
            if (tmpSum > sum){
                sum = tmpSum;
                name = entry.getKey();
            }
        }
        System.out.printf("Best candidate is %s with total %d points.%n", name, sum);
        System.out.println("Ranking:");
        for (Map.Entry<String, List<String>> entry : users.entrySet()) {
            System.out.println(entry.getKey());
            for (int i = 0; i < entry.getValue().size(); i += 2) {
                System.out.printf("#  %s -> %s%n", entry.getValue().get(i), entry.getValue().get(i+1));
            }
        }

    }
}
