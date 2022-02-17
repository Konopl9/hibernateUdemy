package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import main.java.com.luv2code.hibernate.demo.entity.Review;
import main.java.com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndStudentsDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration()
            .configure("main/java/hibernate.cfg.xml")
            .addAnnotatedClass(Student.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();
    // create a session
    Session session = factory.getCurrentSession();

    try {
      // begin a transaction
      session.beginTransaction();

      // create a course
      Course course = new Course("Pacman - How to Score one million");
      System.out.println("\nSaving course...");
      session.save(course);
      System.out.println("\nSaved the course: " + course);

      Student student = new Student("Maksym", "Mishchenko", "myEmail@gmail.com");
      Student student1 = new Student("Maksym1", "Mishchenko1", "myEmail1@gmail.com");

      course.addStudent(student);
      course.addStudent(student1);

      System.out.println("\nSaving students...");
      session.save(student);
      session.save(student1);
      System.out.println("\nSaving is complete: " + course.getStudents());

      session.getTransaction().commit();

    } finally {
      session.close();
      factory.close();
    }
  }
}
