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
public class Adult {
    @JsonProperty("isAdultContent")
    private Boolean isAdultContent;
    @JsonProperty("isRacyContent")
    private Boolean isRacyContent;
    @JsonProperty("adultScore")
    private Double adultScore;
    @JsonProperty("racyScore")
    private Double racyScore;
}
