package br.com.rodrigoluisfaria.petstore.controller.entity;

import br.com.rodrigoluisfaria.petstore.controller.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class UserEntity {

    private String uuid;
    @EqualsAndHashCode.Include
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    public UserDto toUserDto() {
        return new UserDto(
                username, firstName, lastName,
                email, password, phone, userStatus
        );
    }
}
