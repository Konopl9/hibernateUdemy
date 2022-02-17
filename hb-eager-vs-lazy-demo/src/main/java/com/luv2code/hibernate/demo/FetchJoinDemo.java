package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo {

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

      // option 2: Hibernate query with HQL
      Query<Instructor> query = session.createQuery("select i from Instructor i " + "JOIN FETCH i.courses " + " where i.id=:theInstructorId", Instructor.class);
      query.setParameter("theInstructorId", theId);

      // execute query and get the instructor
      Instructor instructor = query.getSingleResult();

      System.out.println("Instructor: " + instructor);
      session.getTransaction().commit();
      session.close();

      // get course for the instructor
      System.out.println("Courses: " + instructor.getCourses());

      // commit a transaction


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
