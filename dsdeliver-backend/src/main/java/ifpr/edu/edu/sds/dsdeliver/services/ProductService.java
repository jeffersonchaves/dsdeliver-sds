package ifpr.edu.edu.sds.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifpr.edu.edu.sds.dsdeliver.DTO.ProductDTO;
import ifpr.edu.edu.sds.dsdeliver.entities.Product;
import ifpr.edu.edu.sds.dsdeliver.repositories.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll(){
		
		List<Product> products = repository.findAllByOrderByNameAsc();
		
		return products.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		
	}
	
}
