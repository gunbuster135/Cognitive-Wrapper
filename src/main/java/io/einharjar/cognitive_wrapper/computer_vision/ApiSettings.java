package io.einharjar.cognitive_wrapper.computer_vision;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ApiSettings {
    private @NonNull String location;
    private String version;
    private @NonNull String apiKey;
}
