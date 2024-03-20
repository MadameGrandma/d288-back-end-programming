package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private Long id;


    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="package_price")
    private BigDecimal package_price;

    @Column(name="party_size", nullable = false)
    private int party_size;

    //@Enumerated(value = EnumType.STRING)
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusType status;

    @Column(name="create_date")
    @CreationTimestamp
    private Date create_date;

    @Column(name="last_update")
    @UpdateTimestamp
    private Date last_update;

    // Other side of Set<Cart> collection in Customer entity. customer_id is FK
    @ManyToOne
    @JoinColumn(name="customer_id", nullable = false)
    private Customer customer;

    // Maps to "cart" variable in CartItem entity. cart_id is FK
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "cart")
    private Set<CartItem> cartItem = new HashSet<>();
    //private Set<CartItem> cartItem;

    public Cart() {
    }


    public void add(CartItem item) {
        if (item != null) {
            if (cartItem == null) {
                cartItem = new HashSet<>();
            }

            cartItem.add(item);
            item.setCart(this);
        }
    }
}

