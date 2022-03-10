package oop2;

public class Main {

	public static void main(String[] args) {
		
		corporateCustomer corporateCustomer1 = new corporateCustomer(1,"Turkcell","123456","123","email");
		IndividualCustomer individualCustomer1 = new IndividualCustomer(1,"Resul","Aktoz","1234566","1234","email");
		Customer[] customers= {corporateCustomer1, individualCustomer1};
		
		IndividualCustomerManager individualCustomerManager = new IndividualCustomerManager();
		individualCustomerManager.add(individualCustomer1);

	}
//kurs, kursun eðitmeni veya eðitmenleri var, kategoriler var, eðitmenler, kurs kategoriye dahil, eðitmenler ayný zamanda kullanýcý, sistemde kullanýcýlar da var. 
}
