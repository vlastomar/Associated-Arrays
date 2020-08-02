import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LinkedHashMap<String, String> register = new LinkedHashMap<>();
        int number = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < number ; i++) {
            String[] input = scan.nextLine().split("\\s+");
            String token = input[0];
            switch (token){
                case "register":
                    String name = input[1];
                    String licenseNumb = input[2];
                    if (register.containsKey(name)){
                        System.out.printf("ERROR: already registered with plate number %s%n", register.get(name));
                    }else {
                        register.put(name, licenseNumb);
                        System.out.printf("%s registered %s successfully%n", name, licenseNumb);
                    }

                    break;
                case "unregister" :
                    name = input[1];
                    if (register.containsKey(name)){
                        register.remove(name);
                        System.out.printf("%s unregistered successfully%n", name);
                    }else {
                        System.out.printf("ERROR: user %s not found%n", name);
                    }
                    break;
            }
        }
        for (Map.Entry<String, String> entry : register.entrySet()) {
            System.out.printf("%s => %s%n", entry.getKey(), entry.getValue());
        }
    }
}
