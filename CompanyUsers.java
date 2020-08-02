

import java.util.*;

public class CompanyUsers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, List<String>> infoComp = new LinkedHashMap<>();

        String input = scan.nextLine();
        while (!"End".equals(input)){
            String[] tokens = input.split(" -> ");
            String compName = tokens[0];
            String idName = tokens[1];
            infoComp.putIfAbsent(compName, new LinkedList<>());
            //List<String> list = infoComp.get(compName);

            if (!infoComp.get(compName).contains(idName)){
                infoComp.get(compName).add(idName);
            }

            input = scan.nextLine();
        }
        infoComp.entrySet()
                .stream()
                .sorted((s1, s2) -> s1.getKey().compareTo(s2.getKey()))
                .forEach(p ->{
                    System.out.println(p.getKey());
                    p.getValue()
                            .forEach(f -> System.out.println(String.format("-- %s", f)));
                });
    }
}
