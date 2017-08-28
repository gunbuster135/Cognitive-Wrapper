package io.einharjar.cognitive_wrapper.computer_vision.error;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiException extends Exception {
    private ResponseError error;
    public ApiException(ResponseError responseError){
        super();
        this.error = responseError;
    }
}
