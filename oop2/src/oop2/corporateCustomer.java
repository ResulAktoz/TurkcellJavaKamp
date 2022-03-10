package oop2;

public class corporateCustomer extends Customer{
	
	private String companyName;
	private String taxNumber;
	
	public corporateCustomer() {
		super();
	}
	
	public corporateCustomer(int id, String companyName, String taxNumber, String customerNumber, String email) {
		super(id,customerNumber,email);//customer'�n constructor'�,temel s�n�f�m�z. Superde eksik kalan k�s�mlar� tamamlay�p do�rudan yaz�ma eklemi� oluruz.
		this.companyName = companyName;
		this.taxNumber = taxNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	
	

}
//extends ile corporateCustomer'�n Customer�n class�n�n alt class� oldu�unu yan� oradan miras ald���n� g�steriyor.