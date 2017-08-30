package io.einharjar.cognitive_wrapper.computer_vision.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.einharjar.cognitive_wrapper.computer_vision.response.VisionAnalyzeResponse;
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
public class Description {
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("captions")
    private List<Caption> captions;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Caption {
        @JsonProperty("text")
        private String text;
        @JsonProperty("confidence")
        private Double confidence;
    }
}
