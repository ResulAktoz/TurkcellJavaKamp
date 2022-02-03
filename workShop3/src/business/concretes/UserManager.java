package business.concretes;

import java.util.ArrayList;

import business.abstracts.UserService;
import core.concretes.BusinessException;
import dataaccess.abstracts.UserDao;
import entities.concretes.User;

public class UserManager implements UserService {
	
	private UserDao userDao;
	

	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void add(User user) throws BusinessException {
		
		if(!nameAndlastNameLengthControl(user.getFirstName(),user.getLastName())) {
			throw new BusinessException("Ad ve soyad 2 karakterden küçük olamaz" +user.getFirstName()+ " " +user.getLastName());
		}
		else if(!passwordLengthControl(user.getPassword())) {
			throw new BusinessException("Þifre 6 karakterden az olamaz." +user.getFirstName()+ " adlý kullanýcýnýn þifresi eksik.");
		}
		else if(!emailContainControl(user.getemail())) {
			System.out.println("Email formatý yanlýþ." +user.getFirstName());
		}
		else if(!uniqueEmailControl(user.getemail())) {
			System.out.println("Email adresi ayný olamaz. " +user.getemail()+ " daha önce kullanýlmýþ.");
		}
		else if(!emailLengthControl(user.getemail())){
			System.out.println("Email boþ býrakýlamaz. " +user.getemail() );	
		}
		else {
			userDao.add(user);
			System.out.println("Kullanýcý baþarýyla eklendi: " +user.getFirstName()+ " " +user.getLastName());
		}
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logIn(String email, String password) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
	private boolean nameAndlastNameLengthControl(String name, String lastName) {
		if(name.length()<2 && lastName.length()<2) {
			return false;
		}
		return true;
	}
	
	private boolean passwordLengthControl(String password) {
		if(password.length()<6) {
			return false;
		}
		return true;
	}
	
	private boolean emailContainControl(String email) {
		if(!email.contains("@")) {
			return false;
		}
		return true;
		
	}
	
	private boolean uniqueEmailControl(String email) {
		for(User user : userDao.getAll()) {
			if(user.getemail().equals(email)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean emailLengthControl(String email) {
		if(email.length()<1) {
			return false;
		}
		return true;
	}
	
	
	}
	


