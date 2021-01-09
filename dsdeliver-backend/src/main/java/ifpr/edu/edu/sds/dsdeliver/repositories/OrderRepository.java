package ifpr.edu.edu.sds.dsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifpr.edu.edu.sds.dsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
