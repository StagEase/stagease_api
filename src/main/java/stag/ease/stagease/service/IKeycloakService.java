package stag.ease.stagease.service;

import org.keycloak.representations.idm.UserRepresentation;
import stag.ease.stagease.dto.login.UserDTO;
import org.keycloak.representations.AccessTokenResponse;

import java.util.List;

public interface IKeycloakService {

    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUsername(String username);
    String createUser(UserDTO userDTO);

    AccessTokenResponse login(UserDTO userDTO) throws Exception;

    void updateUser(String userId, UserDTO userDTO);
    void deleteUser(String userId);


}
