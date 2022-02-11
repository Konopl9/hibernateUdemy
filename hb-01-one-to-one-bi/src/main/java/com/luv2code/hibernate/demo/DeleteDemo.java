package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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

      // find instructor by primary key
      int theId = 1;
      Instructor instructor = session.get(Instructor.class, theId);

      System.out.println("Found instructor: " + instructor);

      // delete instructor
      if (instructor != null) {
        System.out.println("Deleting: " + instructor);

        // Note: will also delete instructor_detail object
        session.delete(instructor);
      }

      // commit a transaction
      session.getTransaction().commit();

    } finally {
      factory.close();
    }
  }
}
