package io.einharjar.cognitive_wrapper.computer_vision.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Category {
        @JsonProperty("name")
        private String name;
        @JsonProperty("score")
        private Double score;
        @JsonProperty("detail")
        public Map<String, String> detail;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Adult {
        @JsonProperty("isAdultContent")
        private Boolean isAdultContent;
        @JsonProperty("isRacyContent")
        private Boolean isRacyContent;
        @JsonProperty("adultScore")
        private Double adultScore;
        @JsonProperty("racyScore")
        private Double racyScore;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Tag {
        @JsonProperty("name")
        private String name;
        @JsonProperty("confidence")
        private Double confidence;
        @JsonProperty("hint")
        private String hint;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Description {
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Metadata {
        @JsonProperty("width")
        private Integer width;
        @JsonProperty("height")
        private Integer height;
        @JsonProperty("format")
        private String format;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Face {
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class Color {
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

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private static class ImageType {
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

}
