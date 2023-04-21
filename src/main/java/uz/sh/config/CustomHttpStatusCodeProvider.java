package uz.sh.config;

import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.HttpStatusCodeProvider;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/3/22 5:03 PM
 **/

/**
 * Class that indicates and sets HttpResponse status
 * code to response according to the JsonRPC protocol code
 *
 */
public class CustomHttpStatusCodeProvider implements HttpStatusCodeProvider {
    final Map<Integer, ErrorResolver.JsonError> httpStatus2JsonError = new HashMap<>();



    CustomHttpStatusCodeProvider() {
        this.httpStatus2JsonError.put(500, ErrorResolver.JsonError.INTERNAL_ERROR);
        this.httpStatus2JsonError.put(404, ErrorResolver.JsonError.METHOD_NOT_FOUND);
        this.httpStatus2JsonError.put(400, ErrorResolver.JsonError.PARSE_ERROR);
    }

    private boolean isErrorCode(int resultCode) {
        for (ErrorResolver.JsonError error : Arrays.asList(
                ErrorResolver.JsonError.INTERNAL_ERROR,
                ErrorResolver.JsonError.METHOD_NOT_FOUND,
                ErrorResolver.JsonError.ERROR_NOT_HANDLED,
                ErrorResolver.JsonError.BULK_ERROR
        )) {
            if (error.code == resultCode)
                return true;
        }
        return -32000 >= resultCode && resultCode >= -32099;
    }

    @Override
    public int getHttpStatusCode(int resultCode) {
        if (resultCode == 0) return 200;
        else if (resultCode == -32400 || resultCode == 400) return 400;
        else if (resultCode == -32404 || resultCode == 404) return 404;
        else if (resultCode == -32401 || resultCode == 401) return 401;
        else if (resultCode == 504) return 504;
        else if (resultCode == 405) return 405;
        else if (resultCode == -32500 || resultCode == 500 || this.isErrorCode(resultCode)) return 500;
        else if (resultCode != ErrorResolver.JsonError.INVALID_REQUEST.code && resultCode != ErrorResolver.JsonError.PARSE_ERROR.code)
            return resultCode == ErrorResolver.JsonError.METHOD_NOT_FOUND.code ? 404 : 200;
        else return 400;
    }

    @Override
    public Integer getJsonRpcCode(int i) {
        return this.httpStatus2JsonError.containsKey(i) ? this.httpStatus2JsonError.get(i).code : null;
    }
}
