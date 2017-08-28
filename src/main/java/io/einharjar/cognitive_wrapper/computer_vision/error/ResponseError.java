package io.einharjar.cognitive_wrapper.computer_vision.error;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseError {
    @JsonProperty("code")
    private String code;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("message")
    private String message;

}
