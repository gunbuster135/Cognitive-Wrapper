package io.einharjar.cognitive_wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.einharjar.cognitive_wrapper.computer_vision.ApiSettings;
import io.einharjar.cognitive_wrapper.computer_vision.VisionClient;
import okhttp3.OkHttpClient;

import java.net.MalformedURLException;

public class Cognitive {
    private OkHttpClient httpClient;
    private VisionClient visionClient;
    private ApiSettings apiSettings;

    public Cognitive(ApiSettings apiSettings) throws MalformedURLException {
        this.httpClient = new OkHttpClient();
        this.apiSettings = apiSettings;
        this.visionClient = new VisionClient(apiSettings, this.httpClient);
    }

    public VisionClient getVisionClient(){
        return this.visionClient;
    }

}
