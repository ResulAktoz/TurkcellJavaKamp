package dataaccess.concretes;

import java.util.ArrayList;

import dataaccess.abstracts.UserDao;
import entities.concretes.User;

public class UserDaoImpl implements UserDao{
	
	ArrayList<User> users = new ArrayList<User>();

	@Override
	public void add(User user) {
		users.add(user);
		
	}

	@Override
	public void delete(User user) {
		users.remove(user);
		
	}

	@Override
	public ArrayList<User> getAll() {
		// TODO Auto-generated method stub
		return users;
	}

}
