

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double[] numbers = Arrays.stream(scan.nextLine().split("\\s+")).mapToDouble(Double::parseDouble)
                .toArray();
        Map<Double,Integer> map = new TreeMap<>();

        for (Double number : numbers) {
            if (!map.containsKey(number)){
                map.put(number, 1);
            }else{
                map.put(number, map.get(number) + 1);
            }

        }
        for(Map.Entry<Double,Integer> entry : map.entrySet() ){
            DecimalFormat vlado = new DecimalFormat("#.#######");
            System.out.printf("%s -> %d%n", vlado.format(entry.getKey()), entry.getValue() );
        }


    }
}
