package io.einharjar.cognitive_wrapper.computer_vision.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by omar on 2017-08-30.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @JsonProperty("name")
    private String name;
    @JsonProperty("score")
    private Double score;
    @JsonProperty("detail")
    public Map<String, String> detail;
}
