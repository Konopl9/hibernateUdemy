package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import main.java.com.luv2code.hibernate.demo.entity.Review;
import main.java.com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaksymDemo {

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

      // get the student Maksym from database
      int id = 1;
      Student student = session.get(Student.class, id);

      System.out.println("\nLoaded student: " + student);
      System.out.println("Courses: " + student.getCourses());
      // create more courses
      Course course1 = new Course("Rubik's Cube");
      Course course2 = new Course("Atari 2600");

      // add student to courses
      course1.addStudent(student);
      course2.addStudent(student);

      // save
      System.out.println("\n Saving the courses...");
      session.save(course1);
      session.save(course2);

      // commit transaction
      session.getTransaction().commit();

    } finally {
      session.close();
      factory.close();
    }
  }
}
