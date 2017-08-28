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
    private String BASE_URL;
    private HttpUrl ANALYZE_ENDPOINT;
    private String apiKey;

    public VisionRequests(String location, String version, String apiKey) throws MalformedURLException {
        ObjectHelper.checkString(location, "Location  cannot be null or empty");
        ObjectHelper.checkString(version, "Version cannot be null or empty");
        ObjectHelper.checkString(apiKey, "API key cannot be null or empty");
        BASE_URL = location + "/vision/" + version;
        this.apiKey = apiKey;
        this.ANALYZE_ENDPOINT = HttpUrl.get(new URL(BASE_URL + "/analyze"));
    }

    //For URL
    public Request analyzeRequest(VisionAnalyzeRequest visionAnalyzeRequest, String url) throws JsonProcessingException {
        VisionAnalyzeRequest.URLBody urlObj = new VisionAnalyzeRequest.URLBody(url);

        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiKey)
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
                .addHeader("Ocp-Apim-Subscription-Key", apiKey)
                .url(createHttpUrlAnalyze(visionAnalyzeRequest))
                .post(body)
                .build();
    }

    private HttpUrl createHttpUrlAnalyze(VisionAnalyzeRequest visionAnalyzeRequest) {
        HttpUrl.Builder builder = ANALYZE_ENDPOINT.newBuilder();
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
        System.out.println(builder.build()); // remove
        return builder.build();
    }
}
