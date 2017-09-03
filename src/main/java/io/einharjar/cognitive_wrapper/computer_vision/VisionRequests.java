package io.einharjar.cognitive_wrapper.computer_vision;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.einharjar.cognitive_wrapper.computer_vision.request.UrlRequest;
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
    public Request analyzeRequest(VisionAnalyzeRequest visionAnalyzeRequest, String url) throws JsonProcessingException, MalformedURLException {
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createAnalyzeEndpointUrl(visionAnalyzeRequest))
                .post(createUrlbody(url))
                .build();
    }

    //Without url
    public Request analyzeRequest(VisionAnalyzeRequest visionAnalyzeRequest, byte[] image) throws MalformedURLException {
        RequestBody body = new MultipartBody.Builder()
                .addPart(
                        RequestBody.create(MediaType.parse("multipart/form-data"), image)
                )
                .build();
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createAnalyzeEndpointUrl(visionAnalyzeRequest))
                .post(body)
                .build();
    }

    public Request describeRequest(int maxCandidates, byte[] image) throws MalformedURLException {
        RequestBody body = createImageBody(image);
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createDescribeEndpointUrl(maxCandidates))
                .post(body)
                .build();
    }

    public Request describeRequest(int maxCandidates, String url) throws JsonProcessingException, MalformedURLException {
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createDescribeEndpointUrl(maxCandidates))
                .post(createUrlbody(url))
                .build();
    }


    private String createBaseUrl() {
        return apiSettings.getLocation() + "/vision/" + apiSettings.getVersion();
    }

    private HttpUrl createAnalyzeEndpointUrl(VisionAnalyzeRequest visionAnalyzeRequest) throws MalformedURLException {
        HttpUrl httpUrl = HttpUrl.get(new URL(createBaseUrl() + "/analyze"));
        if (httpUrl == null) {
            throw new IllegalArgumentException("Invalid URL");
        }
        HttpUrl.Builder builder = httpUrl.newBuilder();
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

    private HttpUrl createDescribeEndpointUrl(int maxCandidates) throws MalformedURLException {
        HttpUrl httpUrl = HttpUrl.get(new URL(createBaseUrl() + "/describe"));
        if (httpUrl == null) {
            throw new IllegalArgumentException("Invalid URL");
        }
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if (maxCandidates > 1) {
            builder.addQueryParameter("maxCandidates", String.valueOf(maxCandidates));
        }
        return builder.build();
    }

    private RequestBody createImageBody(byte[] image) {
        return new MultipartBody.Builder()
                .addPart(
                        RequestBody.create(MediaType.parse("multipart/form-data"), image)
                )
                .build();
    }

    private RequestBody createUrlbody(String url) throws JsonProcessingException {
        return RequestBody.create(MediaType.parse("application/json"), Mapper.getInstance().write(new UrlRequest(url)));
    }
}
