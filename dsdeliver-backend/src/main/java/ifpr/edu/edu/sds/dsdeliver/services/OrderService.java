package ifpr.edu.edu.sds.dsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ifpr.edu.edu.sds.dsdeliver.DTO.OrderDTO;
import ifpr.edu.edu.sds.dsdeliver.entities.Order;
import ifpr.edu.edu.sds.dsdeliver.repositories.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
	OrderRepository repository;

	@Transactional(readOnly = true)
	public List<OrderDTO> findAll(){
		
		List<Order> orders = repository.findOrderWithProducts();
		
		return orders.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
		
	}
	
}
