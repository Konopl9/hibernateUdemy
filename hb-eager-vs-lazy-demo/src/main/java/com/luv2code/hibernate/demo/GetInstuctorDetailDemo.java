package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstuctorDetailDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration()
            .configure("main/java/hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .buildSessionFactory();
    // create a session
    Session session = factory.getCurrentSession();

    try {

      // begin a transaction
      session.beginTransaction();

      // get the instruction detail object
      int theId = 3;
      InstructorDetail instructorDetail = session.get(InstructorDetail.class, theId);

      System.out.println("instruction detail: " + instructorDetail);

      System.out.println("the associated instructor: " + instructorDetail.getInstructor());

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
