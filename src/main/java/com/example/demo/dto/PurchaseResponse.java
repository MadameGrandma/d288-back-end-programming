package com.example.demo.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class PurchaseResponse {

    @NonNull
    private final String orderTrackingNumber;

}
