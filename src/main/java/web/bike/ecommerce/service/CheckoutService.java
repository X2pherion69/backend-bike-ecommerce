package web.bike.ecommerce.service;

import web.bike.ecommerce.dto.Purchase;
import web.bike.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
