package io.einharjar.cognitive_wrapper.computer_vision.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
public class ImageType {
    @JsonProperty("clipArtType")
    private ClipartType clipArtType;
    @JsonProperty("lineDrawingType")
    private LineDrawingType lineDrawingType;


    public enum ClipartType {
        NonClipart,
        Ambigious,
        NormalClipart,
        GoodClipart;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }

    public enum LineDrawingType {
        NonLineDrawing,
        LineDrawing;

        @JsonValue
        public int toValue() {
            return ordinal();
        }
    }
}
