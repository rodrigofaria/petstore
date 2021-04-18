package br.com.rodrigoluisfaria.petstore.controller.dto;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class UserDto {

    @NotNull
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    @NotNull
    private String password;
    private String phone;
    private Integer userStatus;

    public UserEntity toUserEntity() {
        return new UserEntity(UUID.randomUUID().toString(),
            username, firstName, lastName, email,
            password, phone, userStatus
        );
    }
}
