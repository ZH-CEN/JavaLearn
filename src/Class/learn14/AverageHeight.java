package Class.learn14;

import java.util.HashSet;
import java.util.Set;

public class AverageHeight {
    static class Student {
        String name;
        int height;

        public Student(String name, int height) {
            this.name = name;
            this.height = height;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Student) {
                Student student = (Student) obj;
                return this.name.equals(student.name);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        students.add(new Student("John", 5));
        students.add(new Student("Jane", 6));
        students.add(new Student("Doe", 7));

        getAverage(students);
    }

    private static void getAverage(Set<Student> students) {
        int totalHeight = students.stream().mapToInt(student -> student.height).sum();
        double averageHeight = (double) totalHeight / students.size();
        System.out.println("Average height: " + averageHeight);
    }


}
