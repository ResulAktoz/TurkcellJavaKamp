package oop3;

public class Main {

	public static void main(String[] args) {
		
		Product product = new Product(1,"elma",15,1,new String[] {});
		ProductManager manager = new ProductManager(new JdbcProductDao());
		ProductManager manager2 = new ProductManager(new HibernateProductDao());
		manager.add(product);
		manager2.update(product);

	}

}
//interfaceler kendisini implement eden classlar�n referans�n� tutabilir.

	//ProductDao jdbcProductDao = new JdbcProductDao();
	//ProductDao hibernateProductDao = new HibernateProductDao();