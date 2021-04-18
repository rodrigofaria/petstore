package br.com.rodrigoluisfaria.petstore.controller.dto;

import lombok.Data;

@Data
public class Order {

    private String uuid;
    private String petUUID;
    private Integer quantity;
    private String shipDate;
    private String status; //TODO: change to ENUM
    private boolean complete;

}
