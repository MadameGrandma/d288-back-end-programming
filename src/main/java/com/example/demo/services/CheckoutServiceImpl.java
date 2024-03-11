package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CartRepository cartRepository;

    public CheckoutServiceImpl(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // Retrieve the cart info
        Cart cart = purchase.getCart();

        // Generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        // Populate cart with cartItems
        Set<CartItem> cartItem = purchase.getCartItem();
        cartItem.forEach(item -> cart.add(item));

        // Populate cart with customer
        cart.setCustomer(purchase.getCustomer());


        //Populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // Set cart to ordered
        cart.setStatus(StatusType.ordered);

        //Save to the database
        //customerRepository.save(customer);
        cartRepository.save(cart);

        //Return response
        //FIX ME: Implement something to capture empty carts and display "Cart is empty" message
        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {

        //Generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
