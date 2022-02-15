package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstructorCoursesDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration()
            .configure("main/java/hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .buildSessionFactory();
    // create a session
    Session session = factory.getCurrentSession();

    try {

      // begin a transaction
      session.beginTransaction();

      // get the instruction detail object
      int theId = 1;
      Instructor instructor = session.get(Instructor.class, theId);

      System.out.println("Instructor: " + instructor);
      // get course for the instructor
      System.out.println("Courses: " + instructor.getCourses());

      // commit a transaction
      session.getTransaction().commit();

    } catch (Exception e) {
      e.printStackTrace();
    }
    finally {
      // handle leak issue
      session.close();
      factory.close();
    }
  }
}
