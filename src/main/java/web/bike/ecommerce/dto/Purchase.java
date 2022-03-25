package web.bike.ecommerce.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import web.bike.ecommerce.entity.Address;
import web.bike.ecommerce.entity.Customer;
import web.bike.ecommerce.entity.Order;
import web.bike.ecommerce.entity.OrderItem;

import java.util.Set;

@Data
@Getter
@Setter
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
