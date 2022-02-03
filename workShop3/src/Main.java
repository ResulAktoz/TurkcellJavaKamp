
import business.concretes.UserManager;
import core.concretes.BusinessException;
import dataaccess.concretes.UserDaoImpl;
import entities.concretes.User;

public class Main {

	public static void main(String[] args) throws BusinessException {
		
		User user1 = new User("Furkan", "Er", "furkan@gmail.com", "parola" );
		User user2 = new User("Resul", "Aktoz", "resul@gmail.com", "parola" );
		User user3 = new User("Engin", "Demiroð", "engin@gmail.com", "parola" );
		
		UserManager userManager = new UserManager(new UserDaoImpl());
		try {
			userManager.add(user1);
			userManager.add(user2);
			userManager.add(user3);
			
		}catch (BusinessException e) {
			System.out.println(e.getMessage());
		}
		
		

}
}
