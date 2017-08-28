package io.einharjar.cognitive_wrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.einharjar.cognitive_wrapper.computer_vision.VisionClient;
import okhttp3.OkHttpClient;

import java.net.MalformedURLException;

public class Cognitive {

    private OkHttpClient httpClient;
    private ObjectMapper objectMapper = new ObjectMapper();
    private VisionClient visionClient;
    private String apiKey;
    private String location;
    private String version;

    public Cognitive(String location, String apiKey, String version) throws MalformedURLException {
        this.httpClient = new OkHttpClient();
        this.apiKey = apiKey;
        this.location = location;
        this.version = version;
        this.visionClient = new VisionClient(this.location, this.version, this.apiKey,this.httpClient);
    }

    public Cognitive(String location, String apiKey) throws MalformedURLException {
        this.httpClient = new OkHttpClient();
        this.apiKey = apiKey;
        this.location = location;
        this.version = Version.VERSION_1;
        this.visionClient = new VisionClient(this.location, this.version, this.apiKey, this.httpClient);
    }


    public VisionClient getVisionClient(){
        return this.visionClient;
    }

}
