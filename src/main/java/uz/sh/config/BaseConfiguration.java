package uz.sh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:22 AM
 **/
@Configuration
public class BaseConfiguration {


    /**
     * handles exceptions which are extent from {@link JsonRpcClientException}  in application
     * and defines http response code according to exception code
     *
     * @return {@link AutoJsonRpcServiceImplExporter}
     */
    @Bean
    public AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
        AutoJsonRpcServiceImplExporter exp = new AutoJsonRpcServiceImplExporter();
        exp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        exp.setErrorResolver(((throwable, method, list) -> {
            try {
                JsonRpcClientException error = (JsonRpcClientException) throwable;
                return new ErrorResolver.JsonError(error.getCode(), error.getMessage(), error.getData());
            } catch (Exception e) {
                try {
                    RuntimeException error = (RuntimeException) throwable;
                    return new ErrorResolver.JsonError(500, error.getMessage(), JsonNodeFactory.instance.textNode(ExceptionUtils.getRootCauseMessage(error)));
                } catch (Exception ex) {
                    JsonRpcClientException error = new JsonRpcClientException(-32603, "Internal error", JsonNodeFactory.instance.textNode(ExceptionUtils.getRootCauseMessage(ex)));
                    return new ErrorResolver.JsonError(error.getCode(), error.getMessage(), error.getData());
                }
            }
        }));
        exp.setHttpStatusCodeProvider(new CustomHttpStatusCodeProvider());
        return exp;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }
}
