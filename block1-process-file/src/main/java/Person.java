import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Person {
    private String name;

    private String town;
    private String age;

    public Person(String name, String town, String age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getTown() {
        return this.town;
    }

    public String getAge() {
        return this.age;
    }

    public String setAge(String age) {
        return this.age = age;
    }

    public static List<Person> readCSV(String filePath) throws IOException, InvalidLineFormatException {
        List<Person> people = new ArrayList();
        List<String> lines = Files.readAllLines(Path.of(filePath));
        Iterator var3 = lines.iterator();

        while (var3.hasNext()) {
            String line = (String) var3.next();
            String[] fields = line.split(":");
            if (fields.length < 1 || fields.length > 3) {
                throw new InvalidLineFormatException("Formato de línea no válida: " + line);
            } else if (fields.length < 3 && !line.endsWith(":")) {
                throw new InvalidLineFormatException("Falta el último delimitador de campo (:): " + line);
            }
            if (fields[0].isEmpty() || fields[0] == " ") {
                throw new InvalidLineFormatException("EL nombre es obligatorio: " + line);
            }

            String name = fields[0].trim();
            String town = fields.length > 1 ? fields[1].trim() : "unknown";
            String age = fields.length > 2 ? fields[2].trim() : "0";

            Person person = new Person(name, town, age);
            if (person.town.isEmpty()) {
                person.town = "unknown";
            }
            people.add(person);
        }

        return people;
    }

    public static List<Person> filterPeople(List<Person> people, PersonFilter filter) {
        Stream <Person>peopleList = people.stream();
        Objects.requireNonNull(filter);
        return (List)peopleList.filter(filter::test).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            List<Person> people = readCSV("block1-process-file/src/main/resources/people.csv");
            System.out.println("People younger than 25:");
            filterPeople(people, (p) -> Integer.parseInt(p.getAge()) > 0 && Integer.parseInt(p.getAge()) < 25).forEach((p) -> {
                System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge());
            });
            System.out.println("\nPeople whose name does not start with A:");
            filterPeople(people, (p) -> {
                return !p.getName().startsWith("A");
            }).forEach((p) -> {
                if (p.getAge() == "0") {
                    p.setAge("unknown");
                }
                System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge());
            });
            System.out.println("\nFirst person from Madrid:");
            people.stream().filter((p) -> {
                return p.getTown().equals("Madrid");
            }).findFirst().ifPresent((p) -> {
                System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge());
            });
            System.out.println("\nFirst person from Barcelona:");
            people.stream().filter((p) -> {
                return p.getTown().equals("Barcelona");
            }).findFirst().ifPresent((p) -> {
                System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge());
            });
        } catch (IOException var2) {
            var2.printStackTrace();
        } catch (InvalidLineFormatException var3) {
            System.out.println(var3.getMessage());
            var3.printStackTrace();
        }

    }

}