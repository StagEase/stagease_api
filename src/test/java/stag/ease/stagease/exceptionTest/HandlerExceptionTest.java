package stag.ease.stagease.exceptionTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import stag.ease.stagease.config.HandlerExceptionClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HandlerExceptionTest {
    private HandlerExceptionClass handlerException;

    @BeforeEach
    public void setup() {
        handlerException = new HandlerExceptionClass();
    }

    @Test
    void testHandleGenericException() {
        Exception exception = new Exception("Exception example");

        ResponseEntity<Object> response = handlerException.handleGenericException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Recurso não encontrado", response.getBody());
    }
}
