package uz.sh.helper;

import lombok.Data;

/**
 * @author Shoxruh Bekpulatov
 * Time : 21/04/23
 */

@Data
public class JsonRpcReqDTO {
    private String jsonrpc;
    private String method;
    private String id;
    private Object params;
}
