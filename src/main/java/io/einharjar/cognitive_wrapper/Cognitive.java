package io.einharjar.cognitive_wrapper;

import io.einharjar.cognitive_wrapper.computer_vision.VisionClient;
import okhttp3.OkHttpClient;

import java.net.MalformedURLException;

import static io.einharjar.cognitive_wrapper.utils.ObjectHelper.checkNull;

public class Cognitive {
    private OkHttpClient httpClient;
    private VisionClient visionClient;
    private ApiSettings apiSettings;

    public Cognitive(ApiSettings apiSettings) throws MalformedURLException {
        checkNull(apiSettings, "API Settings cannot be null!");
        this.httpClient = new OkHttpClient();
        this.apiSettings = apiSettings;
        this.visionClient = new VisionClient(this.apiSettings, this.httpClient);
    }

    public Cognitive(ApiSettings apiSettings, OkHttpClient okHttpClient) throws MalformedURLException {
        checkNull(apiSettings, "API Settings cannot be null!");
        checkNull(okHttpClient, "okHttpClient cannot be null!");
        this.httpClient = okHttpClient;
        this.apiSettings = apiSettings;
        this.visionClient = new VisionClient(this.apiSettings, this.httpClient);
    }

    public VisionClient getVisionClient() {
        return this.visionClient;
    }

    public ApiSettings getApiSettings() {
        return apiSettings;
    }

    public void setApiSettings(ApiSettings apiSettings) {
        this.apiSettings = apiSettings;
    }
}
