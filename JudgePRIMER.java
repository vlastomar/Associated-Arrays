import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JudgePRIMER {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        LinkedHashMap<String, LinkedHashMap<String, Integer>> studensData1 = new LinkedHashMap<>();
        HashMap<String, Integer> sumResult = new HashMap<>();
        //AtomicInteger count = new AtomicInteger();


        String input = scan.nextLine();
        while (!"no more time".equals(input)){
            String[] loadArr = input.split(" -> ");
            String username = loadArr[0];
            String contest = loadArr[1];
            int points = Integer.parseInt(loadArr[2]);
            studensData1.putIfAbsent(contest, new LinkedHashMap<>());
            if (studensData1.get(contest).containsKey(username)){
                int temp = studensData1.get(contest).get(username);
                temp = Math.max(temp, points);
                studensData1.get(contest).put(username, temp);
            }else{
                studensData1.get(contest).putIfAbsent(username, 0);
                studensData1.get(contest).put(username, points);
            }

            input = scan.nextLine();
        }
        int[] counter = {1};
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : studensData1.entrySet()) {
            System.out.printf("%s: %d participants%n", entry.getKey(), entry.getValue().size() );

            entry.getValue().entrySet()
                    .stream()
                    .sorted((e1, e2) -> {
                        int res = e2.getValue().compareTo(e1.getValue());
                        if (res == 0){
                            res = e1.getKey().compareTo(e2.getKey());
                        }
                        return res;
                    })
                    .forEach(p ->   {
                        int count = counter[0];
                        System.out.printf("%s. %s <::> %d%n", count, p.getKey(), p.getValue());
                        counter[0]++;
            });
            counter[0] = 1;

        }
        System.out.println("Individual standings:");

        counter[0] = 1;
        int sum = 0;
        for (Map.Entry<String, LinkedHashMap<String, Integer>> entry : studensData1.entrySet()) {
            entry.getValue().keySet()
                    .forEach((s) -> {
                        if (sumResult.containsKey(s)){
                            int temp = entry.getValue().get(s);
                            int tmp1 = sumResult.get(s);
                            int sumtemp = temp + tmp1;
                            sumResult.put(s, sumtemp);
                        }else{
                            sumResult.putIfAbsent(s,0);
                            sumResult.put(s,entry.getValue().get(s));
                        }
                    });
        }
        sumResult.entrySet()
                .stream()
                .sorted((s1, s2) -> {
                    int res = s2.getValue().compareTo(s1.getValue());
                    if (res == 0){
                       res =  s1.getKey().compareTo(s2.getKey());
                    }
                    return res;
                })
                .forEach(p -> {
                    int count = counter[0];
                    //count.getAndIncrement();
                    System.out.printf("%s. %s -> %d%n", count,p.getKey(), p.getValue());
                    counter[0]++;
                });

    }
}
