package dataAccess.abstracts;

import entities.concretes.Course;


public interface CourseDao {
	
	void add(Course Course);
	void update(Course Course);
	void delete(Course Course);

}
