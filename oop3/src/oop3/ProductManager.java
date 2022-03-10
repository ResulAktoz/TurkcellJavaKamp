package oop3;

public class ProductManager {
	
	private ProductDao productDao;
	public ProductManager (ProductDao productDao) { //bana bir tane ProdcutDao ver demek
		this.productDao = productDao;
	}
	
	public void add(Product product) {
		this.productDao.add(product);
		//i� kural� kodlar�,�r�n ismi tekrar edemez,�r�n fiyat� 0dan k���k olamaz
		
		/*JdbcProductDao jdbcProductDao = new JdbcProductDao(); //ba��ml�l�k anlam�na geliyor.
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
		
/* pair �al��mas�: yeni bir proje olu�turulacak
 * 
 * Kredi vermek isteyen bankan�n s�re�lerinin temelini kodlayacak
 * 
 * Kredi=>id,ad�,min kredi tutar�,maks kredi tutar�;
 * 
 * Veri taban�na, Kredi kaydetme ,kredi silme, kredi g�ncelleme ortam�n� sim�le ediniz
 * 
 * Jdbc ve hibernate implementasyonlar�n� sim�le ediniz.
 * 
 * krediye ba�vuru ortam�n� sim�le ediniz. (parametre kredi istenen miktar).
 * 
 * ba�vurulan kredi tutar�, ilgili kredinin min maks de�erleri s�n�rlar�nda olmal�.
 * 
 * kredi ba�vurular�n da loglama da yapmak istiyoruz. �u ki�i �u kadar krediye ba�vurdu gibi.Loglar ayn� anda bir veritaban�na veya bir dosyaya yaz�labilir. 
 * 
 * 
 */




