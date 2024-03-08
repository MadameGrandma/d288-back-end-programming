package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import com.example.demo.entities.StatusType.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;


    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
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

        // Populate order with cartItems
        Set<CartItem> cartItems = purchase.getCartItem();
        cartItems.forEach(item -> cart.add(item));

        // Populate cart with cartItems
        cart.setCartItem(purchase.getCartItem());


        //Populate customer with cart
        Customer customer = purchase.getCustomer();
        customer.add(cart);

        // Set cart to ordered
        cart.setStatus(StatusType.ordered);

        //Save to the database
        customerRepository.save(customer);
        cartRepository.save(cart);

        //Return response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        //Generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
