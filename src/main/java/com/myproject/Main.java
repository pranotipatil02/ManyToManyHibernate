package com.myproject;

import com.myproject.model.Student;
import com.myproject.model.Course;
import com.myproject.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        // Initialize Hibernate
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Create a session
        Session session = sessionFactory.openSession();

        try {
            // Begin a transaction
            session.beginTransaction();

            // Create some students and courses
            Student student1 = new Student("John");
            Student student2 = new Student("Jane");

            Course course1 = new Course("Mathematics");
            Course course2 = new Course("History");

            student1.getCourses().add(course1);
            student2.getCourses().add(course1);
            student2.getCourses().add(course2);

            // Save students and courses
            session.save(student1);
            session.save(student2);
            session.save(course1);
            session.save(course2);

            // Commit the transaction
            session.getTransaction().commit();

            // Retrieve and print students and courses
            Student retrievedStudent = session.get(Student.class, student1.getId());
            System.out.println("Retrieved Student: " + retrievedStudent.getName());

            System.out.println("Courses enrolled by the student:");
            for (Course course : retrievedStudent.getCourses()) {
                System.out.println(course.getCourseName());
            }

            Course retrievedCourse = session.get(Course.class, course1.getId());
            System.out.println("\nRetrieved Course: " + retrievedCourse.getCourseName());

            System.out.println("Students enrolled in the course:");
            for (Student student : retrievedCourse.getStudents()) {
                System.out.println(student.getName());
            }
        } finally {
            // Close the session and the session factory when done
            session.close();
            sessionFactory.close();
        }
    }
}
