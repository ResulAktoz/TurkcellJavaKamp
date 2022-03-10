package oop3;

public class HibernateProductDao implements ProductDao {

	@Override
	public void add(Product product) {
		
		System.out.println("hibernate ile eklendi " +product.getName());
		
	}

	@Override
	public void update(Product product) {
		
		System.out.println("hibernate ile güncellendi " +product.getName());
	}

	@Override
	public void delete(Product product) {
		
		System.out.println("hibernate ile çýkarýldý " +product.getName());		
	}
	

}
