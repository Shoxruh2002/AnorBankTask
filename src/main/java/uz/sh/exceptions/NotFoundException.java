package uz.sh.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.googlecode.jsonrpc4j.JsonRpcClientException;

/**
 * NotFoundException will be thrown when
 * the requested resource is not found
 */
public class NotFoundException extends JsonRpcClientException {
    /**
     * Creates the exception.
     *
     * @param code    the code from the server
     * @param message the message from the server
     * @param data    the data from the server
     */
    public NotFoundException(int code, String message, JsonNode data) {
        super(code, message, data);
    }

    public NotFoundException(int code, String message) {
        super(code, message, JsonNodeFactory.instance.nullNode());
    }

    public NotFoundException(String message) {
        super(404, message, JsonNodeFactory.instance.nullNode());
    }
}
