package io.einharjar.cognitive_wrapper.computer_vision.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by omar on 2017-08-30.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UrlRequest {
    @JsonProperty("url")
    private String url;
}
