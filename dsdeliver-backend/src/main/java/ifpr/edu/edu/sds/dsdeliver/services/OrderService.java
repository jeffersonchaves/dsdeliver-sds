package ifpr.edu.edu.sds.dsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifpr.edu.edu.sds.dsdeliver.DTO.OrderDTO;
import ifpr.edu.edu.sds.dsdeliver.DTO.ProductDTO;
import ifpr.edu.edu.sds.dsdeliver.entities.Order;
import ifpr.edu.edu.sds.dsdeliver.entities.OrderStatus;
import ifpr.edu.edu.sds.dsdeliver.entities.Product;
import ifpr.edu.edu.sds.dsdeliver.repositories.OrderRepository;
import ifpr.edu.edu.sds.dsdeliver.repositories.ProductRepository;


@Service
public class OrderService {
	
	@Autowired
	OrderRepository repository;

	@Autowired
	ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> orders = repository.findOrderWithProducts();
		
		return orders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
		
	}
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {

		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), Instant.now(), OrderStatus.PENDING);
		
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		
		order = repository.save(order);
		
		return new OrderDTO(order);
		
	}
	
	@Transactional
	public OrderDTO setDelivered(Long id) {

		Order order = repository.getOne(id);
		order.setStatus(OrderStatus.DELIVERED);
		
		order = repository.save(order);
		
		return new OrderDTO(order);
		
	}
	
}
