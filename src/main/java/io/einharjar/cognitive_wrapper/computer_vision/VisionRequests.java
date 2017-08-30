package io.einharjar.cognitive_wrapper.computer_vision;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.einharjar.cognitive_wrapper.computer_vision.request.VisionAnalyzeRequest;
import io.einharjar.cognitive_wrapper.utils.Mapper;
import io.einharjar.cognitive_wrapper.utils.ObjectHelper;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;


public class VisionRequests {
    private ApiSettings apiSettings;

    public VisionRequests(ApiSettings apiSettings) throws MalformedURLException {
        ObjectHelper.checkNull(apiSettings, "API Settings cannot be null!");
        this.apiSettings = apiSettings;
    }

    //For URL
    public Request analyzeRequest(VisionAnalyzeRequest visionAnalyzeRequest, String url) throws JsonProcessingException {
        VisionAnalyzeRequest.URLBody urlObj = new VisionAnalyzeRequest.URLBody(url);

        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createHttpUrlAnalyze(visionAnalyzeRequest))
                .post(RequestBody.create(MediaType.parse("application/json"), Mapper.getInstance().write(urlObj)))
                .build();

    }

    //Without url
    public Request analyzeRequest(VisionAnalyzeRequest visionAnalyzeRequest, byte[] image) {
        RequestBody body = new MultipartBody.Builder()
                .addPart(
                        RequestBody.create(MediaType.parse("multipart/form-data"), image)
                )
                .build();
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createHttpUrlAnalyze(visionAnalyzeRequest))
                .post(body)
                .build();
    }

    private HttpUrl createHttpUrlAnalyze(VisionAnalyzeRequest visionAnalyzeRequest) {
        HttpUrl.Builder builder = createAnalyzeEndpointUrl().newBuilder();
        if (visionAnalyzeRequest != null) {
            if (visionAnalyzeRequest.getVisualFeatures() != null) {
                builder.addQueryParameter(
                        "visualFeatures", StringUtils.join(visionAnalyzeRequest.getVisualFeatures(), ',')
                );
            }
            if (visionAnalyzeRequest.getDetails() != null) {
                builder.addQueryParameter(
                        "details", StringUtils.join(visionAnalyzeRequest.getDetails(), ',')
                );
            }

            if (visionAnalyzeRequest.getLanguage() != null) {
                builder.addQueryParameter(
                        "language", visionAnalyzeRequest.getLanguage()
                );
            }
        }
        return builder.build();
    }

    private String createBaseUrl() {
        return apiSettings.getLocation() + "/vision/" + apiSettings.getVersion();
    }

    private HttpUrl createAnalyzeEndpointUrl() {
        try {
            return HttpUrl.get(new URL(createBaseUrl() + "/analyze"));
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Malformed url produced with following API Settings = " + apiSettings.toString());
        }
    }
}
