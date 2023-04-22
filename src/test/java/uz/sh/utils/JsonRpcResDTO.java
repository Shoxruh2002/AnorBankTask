package uz.sh.utils;

import lombok.Data;

/**
 * @author Shoxruh Bekpulatov
 * Time : 21/04/23
 */

@Data
public class JsonRpcResDTO {
    private String jsonrpc;
    private String id;
    private Object result;
    private Object error;
}
