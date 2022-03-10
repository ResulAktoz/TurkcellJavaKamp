package oop2;

public class corporateCustomer extends Customer{
	
	private String companyName;
	private String taxNumber;
	
	public corporateCustomer() {
		super();
	}
	
	public corporateCustomer(int id, String companyName, String taxNumber, String customerNumber, String email) {
		super(id,customerNumber,email);//customer'ýn constructor'ý,temel sýnýfýmýz. Superde eksik kalan kýsýmlarý tamamlayýp doðrudan yazýma eklemiþ oluruz.
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
//extends ile corporateCustomer'ýn Customerýn classýnýn alt classý olduðunu yaný oradan miras aldýðýný gösteriyor.