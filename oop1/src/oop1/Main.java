package oop1;

public class Main {

	public static void main(String[] args) {
		String name = "Resul Aktoz";
		
		Category category1 = new Category();
		category1.setId(1);
		category1.setName("gsm");
		
		
		Product product1 = new Product();
		product1.setId(1);
		product1.setName("Ýphone 12");
		product1.setUnitPrice(12000) ;
		product1.setDiscount(1500) ;
		product1.setImages(new String[] {"image1.jpg","image2.jpg","image3.jpg"});
		product1.setCategory(category1);
		
		
		Product product2 = new Product(2,"Ýphone 13",12000,1500,new String[] {"image1.jpg","image2.jpg","image3.jpg"});
		//product2.setId(2);
		//product2.setName("Ýphone 13");
		//product2.setUnitPrice(12000) ;
		//product2.setDiscount(1500) ;
		//product2.setImages(new String[] {"image1.jpg","image2.jpg","image3.jpg"});
		//product2.setCategory(category1);
		
		Product product3 = new Product();
		product3.setId(3);
		product3.setName("Ýphone 14");
		product3.setUnitPrice(12000) ;
		product3.setDiscount(1500) ;
		product3.setImages(new String[] {"image1.jpg","image2.jpg","image3.jpg"});
		product3.setCategory(category1);
		
		String[] productNames = {"X","Y","Z"};
		Product[] products = {product1,product2,product3};
		
		for(Product product : products) {
			System.out.println(product.getName());
			System.out.println(product.getUnitPrice());
			System.out.println(product.getDiscount());
			System.out.println(product.getDiscountedPrice());
			System.out.println("------------");
			
		}
		
		
		//sea
		
	}
	//oopworkshop1 diye proje oluþtur ,4 tane nesnesi olsun kurslar gibi. Kurslarýn kategorileri olsun (veritabaný, programlama,gibi gibi), eðitmenleri olan)

}
