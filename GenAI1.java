import java.io.*;
import java.util.*;

public class GenAI1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Unit") || line.startsWith("Last Name") || line.trim().isEmpty()) continue;
                String[] data = line.split(",");
                if (data.length >= 3) {
                    students.add(data);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file.");
            return;
        }

        System.out.println("1. Students below threshold");
        System.out.println("2. Top and bottom 5 students");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter threshold: ");
            double threshold = scanner.nextDouble();
            for (String[] s : students) {
                double total = getTotal(s);
                if (total < threshold) {
                    System.out.println(s[1] + " " + s[0] + " (" + s[2] + ") - Total: " + total);
                }
            }
        } else if (choice == 2) {
            students.sort((a, b) -> Double.compare(getTotal(a), getTotal(b)));

            System.out.println("Top 5:");
            for (int i = students.size() - 1; i >= students.size() - 5 && i >= 0; i--) {
                String[] s = students.get(i);
                System.out.println(s[1] + " " + s[0] + " - " + getTotal(s));
            }

            System.out.println("Bottom 5:");
            for (int i = 0; i < 5 && i < students.size(); i++) {
                String[] s = students.get(i);
                System.out.println(s[1] + " " + s[0] + " - " + getTotal(s));
            }
        }
    }

    public static double getTotal(String[] data) {
        double total = 0.0;
        for (int i = 3; i <= 5; i++) {
            if (data.length > i && !data[i].isEmpty()) {
                try {
                    total += Double.parseDouble(data[i]);
                } catch (Exception e) {}
            }
        }
        return total;
    }
}
