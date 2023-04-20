package uz.sh.exceptions;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.JsonRpcClientException;

/**
 * @author Botirov Najmiddin, Tue 12:02 PM. 8/2/2022
 */
public class ValidatorException extends JsonRpcClientException {
    /**
     * Creates the exception.
     *
     * @param code    the code from the server
     * @param message the message from the server
     * @param data    the data from the server
     */
    public ValidatorException(int code, String message, JsonNode data) {
        super(code, message, data);
    }
}
