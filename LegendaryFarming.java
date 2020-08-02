import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LegendaryFarming {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TreeMap<String, Integer> legendaryFarming = new TreeMap<String, Integer>(){{
            put("fragments", 0);
            put("shards", 0);
            put("motes", 0);
        }};
        TreeMap<String,Integer> junk = new TreeMap<>();
        boolean check = false;

        while (true){
            String[] input = scan.nextLine().split("\\s+");
            for (int i = 0; i < input.length ; i+=2) {
                int temp = Integer.parseInt(input[i]);
                String tempKey = input[i+1].toLowerCase();
                if ("fragments".equals(tempKey) || "shards".equals(tempKey) || "motes".equals(tempKey)){
                    if (!legendaryFarming.containsKey(tempKey)){
                        legendaryFarming.put(tempKey, temp);
                    }else{
                        legendaryFarming.put(tempKey, legendaryFarming.get(tempKey) + temp);
                    }
                }else{
                    if (junk.containsKey(tempKey)){
                        junk.put(tempKey, junk.get(tempKey) + temp);
                    }else{
                        junk.put(tempKey, temp);
                    }
                }
                for (Map.Entry<String, Integer> entry : legendaryFarming.entrySet()) {
                    if (entry.getValue() >= 250){
                        if ("fragments".equals(entry.getKey())){
                            legendaryFarming.put(entry.getKey(), entry.getValue() - 250);
                            System.out.println("Valanyr obtained!");
                        }else if ("shards".equals(entry.getKey())){
                            legendaryFarming.put(entry.getKey(), entry.getValue() - 250);
                            System.out.println("Shadowmourne obtained!");
                        }else if ("motes".equals(entry.getKey())){
                            legendaryFarming.put(entry.getKey(), entry.getValue() - 250);
                            System.out.println("Dragonwrath obtained!");
                        }
                        check = true;
                        break;

                    }
                }
                if (check){
                    break;
                }
            }

            if (check){
                break;
            }

        }
        legendaryFarming.entrySet()
                .stream()
                .sorted((e1, e2) ->{
                int res = e2.getValue().compareTo(e1.getValue());
                if (res == 0){
                    e1.getKey().compareTo(e2.getKey());
                }
                return res;
        })
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
        /*for (Map.Entry<String, Integer> entry : legendaryFarming.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }*/
        for (Map.Entry<String, Integer> entry : junk.entrySet()) {
            System.out.printf("%s: %d%n", entry.getKey(), entry.getValue());
        }

    }
}

