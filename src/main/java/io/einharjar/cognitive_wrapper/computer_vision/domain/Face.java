package io.einharjar.cognitive_wrapper.computer_vision.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.einharjar.cognitive_wrapper.computer_vision.response.VisionAnalyzeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by omar on 2017-08-30.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Face {
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("faceRectangle")
    private FaceRectangle faceRectangle;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class FaceRectangle {
        @JsonProperty("left")
        private Integer left;
        @JsonProperty("right")
        private Integer right;
        @JsonProperty("width")
        private Integer width;
        @JsonProperty("height")
        private Integer height;

    }
}
