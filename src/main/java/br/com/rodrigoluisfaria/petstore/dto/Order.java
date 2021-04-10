package br.com.rodrigoluisfaria.petstore.dto;

import lombok.Data;

@Data
public class Order {

    private Integer id;
    private Integer petId;
    private Integer quantity;
    private String shipDate;
    private String status; //TODO: change to ENUM
    private boolean complete;

}
