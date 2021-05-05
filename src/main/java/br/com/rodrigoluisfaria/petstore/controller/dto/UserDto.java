package br.com.rodrigoluisfaria.petstore.controller.dto;

import br.com.rodrigoluisfaria.petstore.controller.entity.UserEntity;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class UserDto {

    @NotNull
    String username;
    String firstName;
    String lastName;
    String email;
    @NotNull
    String password;
    String phone;
    Integer userStatus;

    public UserEntity toUserEntity() {
        return new UserEntity(UUID.randomUUID().toString(),
            username, firstName, lastName, email,
            password, phone, userStatus
        );
    }
}
