import java.util.*;

public class MOBAChallenger {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TreeMap<String, TreeMap<String, Integer>> players = new TreeMap<>();
        TreeMap<String,List<String>> play1st = new TreeMap<>();
        TreeMap<String, Integer> play2nd = new TreeMap<>();
        List<String> temp = new ArrayList<>();

        String input = scan.nextLine();

        while (!"Season end".equals(input)){
            if (input.contains(">")){
                String[] comm = input.split("\\s*->\\s*");
                String name = comm[0];
                String position = comm[1];
                int skill = Integer.parseInt(comm[2]);
                if (!players.containsKey(name)){
                    players.putIfAbsent(name, new TreeMap<>());
                    players.get(name).putIfAbsent(position, 0);
                    players.get(name).put(position, skill);
                    play1st.putIfAbsent(name, new ArrayList<>());
                    play1st.get(name).add(position);
                    play2nd.putIfAbsent(position, 0);
                    play2nd.put(position, skill);
                }else {
                    if (!players.get(name).containsKey(position)){
                        players.get(name).putIfAbsent(position, 0);
                        players.get(name).put(position, skill);
                        play1st.get(name).add(position);
                        play2nd.putIfAbsent(position, 0);
                        play2nd.put(position, skill);
                    }else{
                        if (players.get(name).get(position) < skill){
                            players.get(name).put(position, skill);
                            play2nd.put(position, skill);
                        }
                    }
                }


            }else{
                String[] comm = input.split("\\s*vs\\s*");
                String player1 = comm[0];
                String player2 = comm[1];
                if (players.containsKey(player1) && players.containsKey(player2)){

                    List<String> posit1 = new ArrayList<>();
                    List<String> posit2 = new ArrayList<>();
                    String pos1 = "";
                     for (Map.Entry<String, List<String>> entry : play1st.entrySet()) {
                        if (entry.getKey().equals(player1)){
                            for (int i = 0; i <entry.getValue().size() ; i++) {
                                posit1.add(entry.getValue().get(i));
                            }

                        }else   if (entry.getKey().equals(player2)){
                            for (int i = 0; i <entry.getValue().size() ; i++) {
                                posit2.add(entry.getValue().get(i));
                            }

                        }
                    }

                     if (posit1.size() > posit2.size()){
                         for (int i = 0; i <posit1.size() ; i++) {
                             for (int j = 0; j < posit2.size() ; j++) {
                                 if (posit1.get(i).equals(posit2.get(j))){
                                     pos1 = posit1.get(i);
                                 }
                             }

                         }
                     }else {
                         for (int i = 0; i <posit2.size() ; i++) {
                             for (int j = 0; j < posit1.size() ; j++) {
                                 if (posit2.get(i).equals(posit1.get(j))){
                                     pos1 = posit1.get(i);
                                 }
                             }

                         }
                     }

                    int ski1 = 0;
                    int ski2 = 0;
                    String pla1 = "";
                    String pla2 = "";
                    if (!"".equals(pos1)){
                        for (Map.Entry<String, TreeMap<String, Integer>> entry : players.entrySet()) {
                            if (entry.getKey().equals(player1)){
                                ski1 = entry.getValue().get(pos1);

                            }else if (entry.getKey().equals(player2)){
                                ski2 = entry.getValue().get(pos1);
                            }
                        }
                        if (ski1 > ski2){
                            players.remove(player2);
                        }else if (ski2 > ski1){
                            players.remove(player1);
                        }
                    }

                }
            }

            input = scan.nextLine();
        }
        int[] sum = {0};
        Map<String, Integer> sumSkills = new HashMap<>();
        for (Map.Entry<String, TreeMap<String, Integer>> entry : players.entrySet()) {
            entry.getValue().entrySet().stream()
                    .forEach(f -> {
                        sum[0] += f.getValue();
                    });
                sumSkills.putIfAbsent(entry.getKey(), 0);
                sumSkills.put(entry.getKey(), sum[0]);
                sum[0] = 0;
        }
        sumSkills.entrySet()
                .stream()
                .sorted((s1,s2) -> s2.getValue().compareTo(s1.getValue()))
                .forEach(f -> {
                    System.out.println(String.format("%s: %d skill", f.getKey(), f.getValue()));
                    players.get(f.getKey()).entrySet()
                            .stream()
                            .sorted((sort1, sort2) -> sort2.getValue().compareTo(sort1.getValue()))
                            .forEach(p -> {
                                System.out.println(String.format("- %s <::> %d", p.getKey(), p.getValue()));
                            });
                });
    }
}
