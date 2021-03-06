package br.com.rodrigoluisfaria.petstore.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class Pet {

    private String uuid;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

}
