package uz.sh.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

/**
 * Author: Shoxruh Bekpulatov
 * Time: 11/3/22 5:03 PM
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ErrorMessage extends RuntimeException {

    @JsonProperty("code")
    private Integer code;

    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "data")
    private Objects data;

    public ErrorMessage(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }
}