package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import main.java.com.luv2code.hibernate.demo.entity.Review;
import main.java.com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeletePacmanCourseDemo {

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

      int courseId = 10;
      Course course = session.get(Course.class, courseId);

      System.out.println("Deleting the course...");
      session.delete(course);

      // commit transaction
      session.getTransaction().commit();

    } finally {
      session.close();
      factory.close();
    }
  }
}
