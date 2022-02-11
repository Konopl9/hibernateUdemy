package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

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
      // create the objects

      Instructor instructor = new Instructor("Maksym", "Mishchenko", "test@gmail.com");
      Instructor instructor1 = new Instructor("Mahsa", "Bohdanets", "test@gmail.com");

      InstructorDetail instructorDetail = new InstructorDetail( "http://www.max.com/youtune", "Luv 2 code!!!");
      InstructorDetail instructorDetail1 = new InstructorDetail( "http://www.masha.com/youtune", "Luv 2 code!!!");

      // associate the objects
      instructor1.setInstructorDetail(instructorDetail1);

      // begin a transaction
      session.beginTransaction();

      // save a instructor
      // Note: this will ALSO save the details object because of CascadeType.ALL
      System.out.println("Saving instructor: " + instructor);
      session.save(instructor1);

      // commit a transaction
      session.getTransaction().commit();

    } finally {
      factory.close();
    }
  }
}
