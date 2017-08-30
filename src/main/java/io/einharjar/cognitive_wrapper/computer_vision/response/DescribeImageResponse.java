package io.einharjar.cognitive_wrapper.computer_vision.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.einharjar.cognitive_wrapper.computer_vision.domain.Description;
import io.einharjar.cognitive_wrapper.computer_vision.domain.Metadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DescribeImageResponse {
    @JsonProperty("description")
    private Description description;
    @JsonProperty("requestId")
    private String requestId;
    @JsonProperty("metadata")
    private Metadata metadata;
}
