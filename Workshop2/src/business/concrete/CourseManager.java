package business.concrete;

import dataAccess.abstracts.CourseDao;
import entities.concretes.Course;


public class CourseManager  {

	private CourseDao courseDao;


	public CourseManager (CourseDao courseDao) { 
		this.courseDao = courseDao;
	}

	public void add(Course course) {
		this.courseDao.add(course);

	}



	public void update(Course course) {
		this.courseDao.update(course);
		

	}
	public void delete(Course course) {
		this.courseDao.delete(course);
		
	}



}
