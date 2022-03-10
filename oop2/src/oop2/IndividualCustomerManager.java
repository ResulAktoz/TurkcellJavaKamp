package oop2;
//operasyon tutucu sýnýf, bunun içinde method tutacaz
public class IndividualCustomerManager {
	public void add(IndividualCustomer customer) {
		System.out.println("added : " +customer.getFirstName());
		
	}
	public void update(IndividualCustomer customer) {
		System.out.println("updated : " +customer.getFirstName());
	}
	public void delete(IndividualCustomer customer) {
		System.out.println("deletted : " +customer.getFirstName());
	}


}
