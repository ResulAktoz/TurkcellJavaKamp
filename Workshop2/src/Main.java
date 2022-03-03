import business.concrete.CourseManager;
import dataAccess.concrete.hibernateImpls.HibernateProductDao;
import entities.concretes.Course;

public class Main {

	public static void main(String[] args) {
		
		Course course = new Course(1, "Java...", 15,new String[] {});
		CourseManager manager = new CourseManager(new HibernateProductDao());
		manager.add(course);
	}

}
