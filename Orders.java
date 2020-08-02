import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Orders {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LinkedHashMap<String, Double> first = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> second = new LinkedHashMap<>();
        String input = scan.nextLine();
        while (!"buy".equals(input)){
            String[] tokens = input.split("\\s+");
            String temp = tokens[0];
            double price = Double.parseDouble(tokens[1]);
            int pcs = Integer.parseInt(tokens[2]);
            first.putIfAbsent(temp,0.0);
            second.putIfAbsent(temp,0);
            first.put(temp, price);
            second.put(temp, second.get(temp) + pcs);


         input = scan.nextLine();
        }
        for (Map.Entry<String, Double> entry : first.entrySet()) {
            String temp = entry.getKey();
            double totalPrice = entry.getValue() * second.get(temp) * 1.00;
            System.out.printf("%s -> %.2f%n",entry.getKey(), totalPrice);
        }
    }
}
