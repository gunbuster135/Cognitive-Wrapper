package io.einharjar.cognitive_wrapper.computer_vision.domain;

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
public class Tag {
    @JsonProperty("name")
    private String name;
    @JsonProperty("confidence")
    private Double confidence;
    @JsonProperty("hint")
    private String hint;
}
