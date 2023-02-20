package edu.uniandes.miso.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseService {
    private String message;
    private Boolean success;
    private Object result;

    public ResponseService(String message, Boolean success, Object result) {
        this.message = message;
        this.success = success;
        this.result = result;
    }
}
