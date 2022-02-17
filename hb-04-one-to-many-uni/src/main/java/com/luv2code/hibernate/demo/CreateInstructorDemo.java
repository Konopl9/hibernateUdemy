package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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
      // create the objects

      Instructor instructor = new Instructor("Susan", "Public", "test@gmail.com");
      InstructorDetail instructorDetail1 = new InstructorDetail( "http://www.masha.com/youtune", "Luv 2 code!!!");

      // associate the objects
      instructor.setInstructorDetail(instructorDetail1);

      // begin a transaction
      session.beginTransaction();

      session.save(instructor);

      // commit a transaction
      session.getTransaction().commit();

    } finally {
      session.close();
      factory.close();
    }
  }
}
