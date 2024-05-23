package stag.ease.stagease.dto.login;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Value
@RequiredArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Set<String> roles;
}
