package dataAccess.concrete.jdbcImpls;

import dataAccess.abstracts.CourseDao;
import entities.concretes.Course;

public class JdbcProductDao implements CourseDao{
	
	@Override
	public void add(Course course) {
		
		System.out.println("Jdbc ile eklendi " +course.getName());
		
	}

	@Override
	public void update(Course course) {
		
		System.out.println("Jdbc ile g�ncellendi " +course.getName());
	}

	@Override
	public void delete(Course course) {
		
		System.out.println("Jdbc ile ��kar�ld� " +course.getName());		
	}

}
