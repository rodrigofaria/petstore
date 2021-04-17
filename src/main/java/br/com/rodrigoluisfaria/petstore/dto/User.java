package br.com.rodrigoluisfaria.petstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    private String uuid;
    @NotNull
    @EqualsAndHashCode.Include
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
