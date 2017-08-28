package io.einharjar.cognitive_wrapper.computer_vision.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Object for request params to Vision Analyze endpoint
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class VisionAnalyzeRequest {
    private static final String DEFAULT_LANGUAGE = "en";
    private List<VisualFeature> visualFeatures;
    private List<String> details;
    private String language;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class URLBody {
        @JsonProperty("url")
        private String url;
    }
}
