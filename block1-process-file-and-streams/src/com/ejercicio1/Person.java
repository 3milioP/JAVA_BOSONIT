package com.ejercicio1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// He copiado vilmente a ChatGPT y a continuación comento el código para ver si lo he entendido
public class Person { // Esta parte es fácil, crea la clase Persona, sus atributos y métodos para acceder a dichos atributos
    private String name;
    private String town;
    private int age;

    public Person(String name, String town, int age) {
        this.name = name;
        this.town = town;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }

    public int getAge() {
        return age;
    }

    /* A continuación crea la función que lee el archivo CSV, guarda los datos en una lista del tipo objeto Persona.
    * Lanza una IOException para manejar el flujo de datos, también usa la InvalidLineFormatException.
    * Itera el archivo CSV separando los campos con el line.split.
    * Controla que al menos el primer campo exista y que tampoco se pase de 3 campos o de lo contrario lanza nuestro error.
    * Guarda los datos pasándole "trim" para eliminar posibles espacios en blanco */
    public static List<Person> readCSV(String filePath) throws IOException, InvalidLineFormatException {
        List<Person> people = new ArrayList<>();
        List<String> lines = Files.readAllLines(Path.of(filePath));
        for (String line : lines) {
            String[] fields = line.split(":");
            if (fields.length < 1 || fields.length > 3) {
                throw new InvalidLineFormatException("Invalid line format: " + line);
            }
            String name = fields[0].trim();
            String town = fields.length > 1 ? fields[1].trim() : "unknown";
            int age = fields.length > 2 ? Integer.parseInt(fields[2].trim()) : 0;
            if (age == 0) {
                age = -1; // set age to -1 for unknown age
            }
            Person person = new Person(name, town, age);
            people.add(person);
        }
        return people;
    }

    /* Aquí hay miga. Es lo que estuviste explicando en clase.
    *  Un método que recoge una lista de objetos Person usando el filtro que no es más que una comparación de un objeto Person con resultado booleano
    *  Retorna el stream de la lista, pasándole el filtro y recoge los datos creando una lista nueva filtrada */
    public static List<Person> filterPeople(List<Person> people, PersonFilter filter) {
        return people.stream().filter(filter::test).collect(Collectors.toList());
    }

    /* El main aplica los métodos anteriormente declarados y se especifican las comparaciones que retornarán true o false
    *  y servirán para recoger os datos deseados. En la sintaxis se usan expresiones lambda*/
    public static void main(String[] args) {
        try {
            List<Person> people = readCSV("people.csv");
            System.out.println("People younger than 25:");
            filterPeople(people, p -> p.getAge() > 0 && p.getAge() < 25).forEach(p -> {
                System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge());
            });
            System.out.println("\nPeople whose name does not start with A:");
            filterPeople(people, p -> !p.getName().startsWith("A")).forEach(p -> {
                System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge());
            });
            System.out.println("\nFirst person from Madrid:");
            people.stream().filter(p -> p.getTown().equals("Madrid")).findFirst()
                    .ifPresent(p -> System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge()));
            System.out.println("\nFirst person from Barcelona:");
            people.stream().filter(p -> p.getTown().equals("Barcelona")).findFirst()
                    .ifPresent(p -> System.out.println("Name: " + p.getName() + ". Town: " + p.getTown() + ". Age: " + p.getAge()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidLineFormatException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(); // Realmente creo que con el catch de InvalidLineFormatException sería suficiente ya que extiende de Exception
        }
    }
}

class InvalidLineFormatException extends Exception {
    public InvalidLineFormatException(String message) {
        super(message);
    }
}

interface PersonFilter {
    boolean test(Person p);
}
