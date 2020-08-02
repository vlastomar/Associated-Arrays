import java.util.*;

public class Courses {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        LinkedHashMap<String, List<String>> listStudents = new LinkedHashMap<>();


        String  input = scan.nextLine();

        while (!"end".equals(input)){
            String[] tokens = input.split(" : ");
            String course = tokens[0];
            String name = tokens[1];
            listStudents.putIfAbsent(course, new ArrayList<>());
            listStudents.get(course).add(name);

            input = scan.nextLine();
        }
        listStudents.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    int first = e1.getValue().size();
                    int second = e2.getValue().size();
                    return Integer.compare(second, first);
                })
                .forEach(p -> {System.out.println(String.format("%s: %d",
                        p.getKey(),
                        p.getValue().size()));
                p.getValue().stream()
                        .sorted((s1, s2) -> s1.compareTo(s2))
                        .forEach(s -> System.out.println(String.format("-- %s", s)));
                });
    }

}
