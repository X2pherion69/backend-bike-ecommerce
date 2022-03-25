package web.bike.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import web.bike.ecommerce.entity.Customer;

@CrossOrigin("http://localhost:4200")
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
