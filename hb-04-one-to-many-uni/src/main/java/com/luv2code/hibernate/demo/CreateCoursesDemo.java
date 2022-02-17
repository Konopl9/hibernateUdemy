package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

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

      // associate the objects

      // begin a transaction
      session.beginTransaction();

      int theID = 1;
      Instructor instructor = session.get(Instructor.class, theID);

      // get thee instructor from db

      // create some cources
      Course course = new Course("Air Guitar");
      Course course1 = new Course("Udemy");
      // add courses to insturctor
      instructor.add(course);
      instructor.add(course1);
      // save the courcses
      session.save(course);
      session.save(course1);
      // commit a transaction
      session.getTransaction().commit();

    } finally {
      session.close();
      factory.close();
    }
  }
}
