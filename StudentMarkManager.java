/**
 * Assessment 2 - Fundamentals of Programming
 *
 * @author Felipe Sepulveda
 * @version v1
 */
import java.io.*;
import java.util.*;

// Class Person using Abstraction
abstract class Person {
    protected String name;
    protected String id;
    
    public Person(String name, String id){
        this.name = name;
        this.id = id;
    }
    
    public abstract void printInfo();
}

// Student class with marks using Inheritance, Encapsulation, and Polymorphism
class Student extends Person {
    private double mark1;
    private double mark2;
    private double mark3;

    public Student(String name, String id, double mark1, double mark2, double mark3) {
        super(name, id);
        this.mark1 = mark1;
        this.mark2 = mark2;
        this.mark3 = mark3;
    }

    public double getTotal() {
        return mark1 + mark2 + mark3;
    }
    
    @Override
    public void printInfo() {
        System.out.println(name + " (" + id + ") - Total: " + getTotal());
    }
}

public class StudentMarkManager {
    public static void main(String[] args) {
        String fileName = "students.txt";
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("Unit") || line.startsWith("Last Name") || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String fullName = parts[1].trim() + " " + parts[0].trim();
                String id = parts[2].trim();

                double mark1 = 0.0;
                double mark2 = 0.0;
                double mark3 = 0.0;
                
                if (parts.length > 3) { mark1 = parseMark(parts[3]); }
                if (parts.length > 4) { mark2 = parseMark(parts[4]); }
                if (parts.length > 5) { mark3 = parseMark(parts[5]); }

                Student student = new Student(fullName, id, mark1, mark2, mark3);
                students.add(student);
            }

            System.out.println("Students and total marks:\n");
            for (Student s : students) {
                s.printInfo();
            }
            
            showBelowThreshold(students, 50);
            showTopAndBottomFive(students);

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    private static double parseMark(String mark) {
        try {
            return Double.parseDouble(mark.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
    
    private static void showBelowThreshold(List<Student> students, double threshold){
        System.out.println("\nStudents with total marks below" + threshold + ":\n");
        for (Student s : students){
            if(s.getTotal() < threshold){
                s.printInfo();
            }
        }
    }
    
    private static void showTopAndBottomFive(List<Student> students){
        List<Student> sorted = new ArrayList<>(students);
        
        // Bubble sort
        for(int i = 0; i<sorted.size(); i++ ){
            for( int j=0; j<sorted.size() - 1 - i; j++){
                if(sorted.get(j).getTotal() > sorted.get(j + 1).getTotal()){
                    Student temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("\nTop 5 Students:");
        int countTop = 0;
        for(int i = sorted.size()-1; i>=0; i--){
            sorted.get(i).printInfo();
            countTop++;
            if(countTop == 5){
                break;
            }
        }
        
        System.out.println("\nBotton 5 Students");
        int countBottom = 0;
        for(int i=0; i<sorted.size(); i++){
            sorted.get(i).printInfo();
            countBottom++;
            if(countBottom == 5){
                break;
            }
        }
    }
}
