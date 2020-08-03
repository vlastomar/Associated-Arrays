import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DragonArmy {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int number = Integer.parseInt(scan.nextLine());
        Map<String, String> dragons = new LinkedHashMap<>();

        for (int i = 0; i <number ; i++) {
            String input = scan.nextLine();
            String[] words = input.split("\\s+");

            if ("null".equals(words[2])){
                words[2] = "45";
            }
            if ("null".equals(words[3])){
                words[3] = "250";
            }
            if ("null".equals(words[4])){
                words[4] = "10";
            }

            String keyMap = words[0] + " " + words[1];
            String valueMap = words[2] + " " + words[3] + " " + words[4];
            dragons.putIfAbsent(keyMap, "");
            dragons.put(keyMap, valueMap);
        }
        Map<String, String> typeDragons = new LinkedHashMap<>();
        Map<String, TreeMap<String,String>> category = new LinkedHashMap<>();
        double damageSum = 0.0;
        double healthSum = 0.0;
        double armorSum = 0.0;
        for (Map.Entry<String, String> entry : dragons.entrySet()) {
            String[] typeName = entry.getKey().split(" ");
            String[] numbers = entry.getValue().split(" ");
            String keyType = typeName[0];
            String keyName = typeName[1];
            typeDragons.putIfAbsent(keyType, "");
            category.putIfAbsent(keyType, new TreeMap<>());
            category.get(keyType).put(keyName, entry.getValue());
            if ("".equals(typeDragons.get(keyType))){
                damageSum = Double.parseDouble(numbers[0]);
                healthSum = Double.parseDouble(numbers[1]);
                armorSum = Double.parseDouble(numbers[2]);
                String valueType = damageSum + " " + healthSum + " " + armorSum;
                typeDragons.put(keyType, valueType);
            }else{
                String[] temp = typeDragons.get(keyType).split(" ");
                damageSum = Double.parseDouble(numbers[0]) + Double.parseDouble(temp[0]);
                healthSum = Double.parseDouble(numbers[1]) + Double.parseDouble(temp[1]);
                armorSum = Double.parseDouble(numbers[2]) + Double.parseDouble(temp[2]);
                String valueType = damageSum + " " + healthSum + " " + armorSum;
                typeDragons.put(keyType, valueType);
            }

        }
      for (Map.Entry<String, String> entry : typeDragons.entrySet()) {
           String[] numbers = entry.getValue().split(" ");
           int temp = category.get(entry.getKey()).size();
            damageSum = Double.parseDouble(numbers[0]) * 1.00/ temp;
            healthSum = Double.parseDouble(numbers[1]) * 1.00 / temp;
            armorSum = Double.parseDouble(numbers[2]) * 1.00 / temp;
          System.out.println(String.format("%s::(%.2f/%.2f/%.2f)", entry.getKey(), damageSum, healthSum, armorSum ));
          dragons.entrySet().stream()
                  .sorted((s1,s2) -> s1.getKey().compareTo(s2.getKey()))
                  .forEach(f ->{
                      String[] words = f.getKey().split(" ");
                      String[] numb = f.getValue().split(" ");
                      if (words[0].equals(entry.getKey())){
                          System.out.printf("-%s -> damage: %s, health: %s, armor: %s\n", words[1], numb[0], numb[1], numb[2]);
                      }
                  });

       }
    }
}
