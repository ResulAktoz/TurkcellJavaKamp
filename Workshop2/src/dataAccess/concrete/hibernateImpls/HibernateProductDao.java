package dataAccess.concrete.hibernateImpls;

import dataAccess.abstracts.CourseDao;
import entities.concretes.Course;

public class HibernateProductDao implements CourseDao {
	
	@Override
	public void add(Course course) {
		
		System.out.println("hibernate ile eklendi " +course.getName());
		
	}

	@Override
	public void update(Course course) {
		
		System.out.println("hibernate ile g�ncellendi " +course.getName());
	}

	@Override
	public void delete(Course course) {
		
		System.out.println("hibernate ile ��kar�ld� " +course.getName());		
	}

}
