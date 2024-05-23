package stag.ease.stagease.util;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;

public class KeycloakProvider {

    public static final String SERVER_URL = "http://192.168.56.113:8080/";
    public static final String REALM_NAME = "spring-boot-realm-dev";
    public static final String REALM_MASTER = "master";
    public static final String ADMIN_CLI = "admin-cli";
    public static final String USER_CONSOLE = "admin";
    public static final String PASSWORD_CONSOLE = "admin";
    public static final String CLIENT_SECRET = "l9pqUigu4YA60iKfMREgfQlEoBmfpsV1";

    public static RealmResource getRealmResource(){

        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(SERVER_URL)
                .realm(REALM_MASTER)
                .clientId(ADMIN_CLI)
                .username(USER_CONSOLE)
                .password(PASSWORD_CONSOLE)
                .clientSecret(CLIENT_SECRET)
                .resteasyClient(new ResteasyClientBuilderImpl()
                        .connectionPoolSize(20)
                        .build())
                .build();

        return keycloak.realm(REALM_NAME);
    }

    public static UsersResource getUserResource() {
        RealmResource realmResource = getRealmResource();
        return realmResource.users();
    }
}
