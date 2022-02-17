package main.java.com.luv2code.hibernate.demo;

import main.java.com.luv2code.hibernate.demo.entity.Course;
import main.java.com.luv2code.hibernate.demo.entity.Instructor;
import main.java.com.luv2code.hibernate.demo.entity.InstructorDetail;
import main.java.com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReviewsDemo {

  public static void main(String[] args) {
    // create session factory
    SessionFactory factory =
        new Configuration()
            .configure("main/java/hibernate.cfg.xml")
            .addAnnotatedClass(Instructor.class)
            .addAnnotatedClass(InstructorDetail.class)
            .addAnnotatedClass(Course.class)
            .addAnnotatedClass(Review.class)
            .buildSessionFactory();
    // create a session
    Session session = factory.getCurrentSession();

    try {
      // begin a transaction
      session.beginTransaction();

      // create a course
      Course course = new Course("Pacman - How to Score one million");
      // add some reviews
      course.addReview(new Review("Great course ___ loved it"));
      course.addReview(new Review("Great course !!! loved it"));
      course.addReview(new Review("Great course ... loved it"));
      // save the course ... and leverage the cascade all :-)
      System.out.println("Saving the course");
      System.out.println(course);
      System.out.println(course.getReviewList());

      session.save(course);

      session.getTransaction().commit();

    } finally {
      session.close();
      factory.close();
    }
  }
}
