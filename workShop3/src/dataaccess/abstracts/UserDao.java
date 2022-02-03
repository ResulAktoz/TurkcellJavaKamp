package dataaccess.abstracts;

import java.util.ArrayList;

import entities.concretes.User;

public interface UserDao {
	void add(User user);
	void delete(User user);
	ArrayList<User> getAll();

}
