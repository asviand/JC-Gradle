package org.example.entity;

import java.io.*;
import java.util.Scanner;

public class Person {
    private int id;
    private int age;
    private String name;

    public Person(int age, int id) {
        this.age = age;
        this.id = id;
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Person(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    //7.1
    public static void saveTo(File file, Person person) {
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            try (PrintWriter writer = new PrintWriter(outputStream)) {
                writer.println(person.getId());
                writer.println(person.getAge());
                writer.println(person.getName());
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Не удалось открыть файл" + e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    //7.2
    public static Person loadFrom(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream(file));
        int id = Integer.parseInt(scanner.nextLine());
        int age = Integer.parseInt(scanner.nextLine());
        String name = scanner.nextLine();
        scanner.close();
        return new Person(id, age, name);
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}




