import java.io.*;
import java.util.*;

class Student {
    String name;
    String id;
    double[] marks = new double[3];

    public Student(String name, String id, double m1, double m2, double m3) {
        this.name = name;
        this.id = id;
        marks[0] = m1;
        marks[1] = m2;
        marks[2] = m3;
    }

    public double total() {
        return marks[0] + marks[1] + marks[2];
    }

    public void print() {
        System.out.println(name + " (" + id + ") - Total: " + total());
    }
}

public class GenAI2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Last Name") || line.contains("Unit") || line.isBlank()) continue;
                String[] parts = line.split(",");
                String name = parts[1].trim() + " " + parts[0].trim();
                String id = parts[2].trim();
                double m1 = parse(parts, 3);
                double m2 = parse(parts, 4);
                double m3 = parse(parts, 5);
                students.add(new Student(name, id, m1, m2, m3));
            }
        } catch (IOException e) {
            System.out.println("Error loading file.");
            return;
        }

        System.out.println("Enter option: 1 for threshold, 2 for top/bottom");
        int opt = sc.nextInt();

        if (opt == 1) {
            System.out.print("Enter threshold: ");
            double t = sc.nextDouble();
            for (Student s : students) {
                if (s.total() < t) s.print();
            }
        } else if (opt == 2) {
            students.sort(Comparator.comparingDouble(Student::total));

            System.out.println("Top 5:");
            for (int i = students.size() - 1; i >= students.size() - 5; i--) {
                students.get(i).print();
            }

            System.out.println("Bottom 5:");
            for (int i = 0; i < 5; i++) {
                students.get(i).print();
            }
        }
    }

    public static double parse(String[] arr, int i) {
        if (arr.length > i && !arr[i].isBlank()) {
            try { return Double.parseDouble(arr[i]); } catch (Exception e) {}
        }
        return 0.0;
    }
}
