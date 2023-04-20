package uz.sh.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.JsonRpcClientException;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 4/20/23 9:22 AM
 **/
@Configuration
public class BaseConfiguration {

    @Bean
    public AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
        AutoJsonRpcServiceImplExporter exp = new AutoJsonRpcServiceImplExporter();
        exp.setContentType(MediaType.APPLICATION_JSON_VALUE);
        exp.setErrorResolver(((throwable, method, list) -> {
            JsonRpcClientException error;
            try {
                error = (JsonRpcClientException) throwable;
            } catch (Throwable throwable1) {
                error = new JsonRpcClientException(-32603, "Internal error", null);
            }
            return new ErrorResolver.JsonError(error.getCode(), error.getMessage(), error.getData());
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
