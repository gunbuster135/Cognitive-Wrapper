package io.einharjar.cognitive_wrapper.computer_vision.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.einharjar.cognitive_wrapper.computer_vision.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VisionAnalyzeResponse {
    @JsonProperty("categories")
    private List<Category> categories;
    @JsonProperty("adult")
    private Adult adult;
    @JsonProperty("tags")
    private List<Tag> tags;
    @JsonProperty("description")
    private Description description;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("faces")
    private List<Face> faces;
    @JsonProperty("color")
    private Color color;
    @JsonProperty("imageType")
    private ImageType imageType;

}
