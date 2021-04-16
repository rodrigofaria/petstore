package br.com.rodrigoluisfaria.petstore.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class User {

    private String uuid;
    @NotNull
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @NotNull
    private String password;
    private String phone;
    private Integer userStatus;

    public User() {
        this.uuid = UUID.randomUUID().toString();
    }
}
