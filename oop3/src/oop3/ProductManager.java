package oop3;

public class ProductManager {
	
	private ProductDao productDao;
	public ProductManager (ProductDao productDao) { //bana bir tane ProdcutDao ver demek
		this.productDao = productDao;
	}
	
	public void add(Product product) {
		this.productDao.add(product);
		//iþ kuralý kodlarý,ürün ismi tekrar edemez,ürün fiyatý 0dan küçük olamaz
		
		/*JdbcProductDao jdbcProductDao = new JdbcProductDao(); //baðýmlýlýk anlamýna geliyor.
		jdbcProductDao.add(product);*/
	}
		
			
		
		public void update(Product product) {
			this.productDao.update(product);
			/*JdbcProductDao jdbcProductDao = new JdbcProductDao();
			jdbcProductDao.update(product);*/
		}
		public void delete(Product product) {
			this.productDao.delete(product);
			/*JdbcProductDao jdbcProductDao = new JdbcProductDao();
			jdbcProductDao.delete(product);*/
		}
}
		
/* pair çalýþmasý: yeni bir proje oluþturulacak
 * 
 * Kredi vermek isteyen bankanýn süreçlerinin temelini kodlayacak
 * 
 * Kredi=>id,adý,min kredi tutarý,maks kredi tutarý;
 * 
 * Veri tabanýna, Kredi kaydetme ,kredi silme, kredi güncelleme ortamýný simüle ediniz
 * 
 * Jdbc ve hibernate implementasyonlarýný simüle ediniz.
 * 
 * krediye baþvuru ortamýný simüle ediniz. (parametre kredi istenen miktar).
 * 
 * baþvurulan kredi tutarý, ilgili kredinin min maks deðerleri sýnýrlarýnda olmalý.
 * 
 * kredi baþvurularýn da loglama da yapmak istiyoruz. Þu kiþi þu kadar krediye baþvurdu gibi.Loglar ayný anda bir veritabanýna veya bir dosyaya yazýlabilir. 
 * 
 * 
 */




