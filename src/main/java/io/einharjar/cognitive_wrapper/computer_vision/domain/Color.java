package io.einharjar.cognitive_wrapper.computer_vision.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by omar on 2017-08-30.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Color {
    @JsonProperty("dominantColorForeground")
    private String dominantColorForeground;
    @JsonProperty("dominantColorBackground")
    private String dominantColorBackground;
    @JsonProperty("dominantColors")
    private List<String> dominantColors;
    @JsonProperty("accentColor")
    private String accentColor;
    @JsonProperty("isBWImg")
    private Boolean isBWImg;
}
