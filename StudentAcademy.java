import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class StudentAcademy {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LinkedHashMap<String, Double> students = new LinkedHashMap<>();

        int number = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < number ; i++) {
            String name = scan.nextLine();
            double evaluation = Double.parseDouble(scan.nextLine());
            if (students.containsKey(name)){
                double average = (students.get(name) + evaluation)/2;
                students.put(name, average);
            }else{
                students.put(name, evaluation);
            }
        }

        students.entrySet()
                .stream()
                .filter(f -> f.getValue() >= 4.50)
                .sorted((s1, s2) -> {
                    int res = s2.getValue().compareTo(s1.getValue());
                    if (res == 0){
                        s2.getKey().compareTo(s1.getKey());
                    }
                    return res;
                })
                .forEach(p -> System.out.printf(("%s -> %.2f%n"), p.getKey(), p.getValue()));
    }
}
