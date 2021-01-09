package ifpr.edu.edu.sds.dsdeliver.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ifpr.edu.edu.sds.dsdeliver.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findAllByOrderByNameAsc();
	
}