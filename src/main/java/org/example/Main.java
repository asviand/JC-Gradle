package org.example;

import org.example.entity.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("test.txt");

        Scanner loadingOrEnter = new Scanner(System.in);
        System.out.println("Would you like to upload or enter data?  Upload/Enter");
        String answer = loadingOrEnter.nextLine();
        if (answer.equalsIgnoreCase("Upload")) {
            System.out.println("Enter the path to the file");
            String filePath = loadingOrEnter.nextLine();
            File x = new File(filePath);
            Person person = null;
            try {
                person = Person.loadFrom(file);
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }

            if (person != null) {
                System.out.println("Data loaded:");
                System.out.println("Id: " + person.getId());
                System.out.println("Name: " + person.getName());
                System.out.println("Age: " + person.getAge());
            }

        } else if (answer.equalsIgnoreCase("Enter")) {
            System.out.println("Enter your id:");
            int id = Integer.parseInt(loadingOrEnter.nextLine());
            System.out.println("Enter your name:");
            String name = loadingOrEnter.nextLine();
            System.out.println("Enter your age:");
            int age = Integer.parseInt(loadingOrEnter.nextLine());
            System.out.println("Enter the path to the file to save data:");
            String filePath = loadingOrEnter.nextLine();
            Person.saveTo(new File(filePath), new Person(id, age, name));
        } else {
            System.out.println("Incorrect answer");
        }
    }

        }