package business.abstracts;

import java.util.ArrayList;

import core.concretes.BusinessException;
import entities.concretes.User;

public interface UserService {
	
	void add(User user) throws BusinessException;
	void delete(User user);
	ArrayList<User> getAll();
	void logIn (String email, String password) throws BusinessException;
	

}
