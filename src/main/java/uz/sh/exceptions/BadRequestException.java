package uz.sh.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends JsonRpcClientException {

    /**
     * Creates the exception.
     *
     * @param code    the code from the server
     * @param message the message from the server
     * @param data    the data from the server
     */
    public BadRequestException(int code, String message, JsonNode data) {
        super(code, message, data);
    }
}
