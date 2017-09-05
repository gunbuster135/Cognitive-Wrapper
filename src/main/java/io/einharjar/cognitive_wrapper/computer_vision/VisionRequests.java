package io.einharjar.cognitive_wrapper.computer_vision;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.einharjar.cognitive_wrapper.ApiSettings;
import io.einharjar.cognitive_wrapper.computer_vision.request.UrlRequest;
import io.einharjar.cognitive_wrapper.computer_vision.request.VisionAnalyzeRequest;
import io.einharjar.cognitive_wrapper.utils.Mapper;
import io.einharjar.cognitive_wrapper.utils.ObjectHelper;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

import static io.einharjar.cognitive_wrapper.utils.ObjectHelper.*;


public class VisionRequests {
    //For URL
    public static Request analyzeRequest(VisionAnalyzeRequest visionAnalyzeRequest, String url, ApiSettings apiSettings) throws JsonProcessingException, MalformedURLException {
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createAnalyzeEndpointUrl(visionAnalyzeRequest, apiSettings))
                .post(createUrlbody(url))
                .build();
    }

    //Without url
    public static Request analyzeRequest(VisionAnalyzeRequest visionAnalyzeRequest, byte[] image, ApiSettings apiSettings) throws MalformedURLException {
        RequestBody body = new MultipartBody.Builder()
                .addPart(
                        RequestBody.create(MediaType.parse("multipart/form-data"), image)
                )
                .build();
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createAnalyzeEndpointUrl(visionAnalyzeRequest, apiSettings))
                .post(body)
                .build();
    }

    public static Request describeRequest(int maxCandidates, byte[] image, ApiSettings apiSettings) throws MalformedURLException {
        RequestBody body = createImageBody(image);
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createDescribeEndpointUrl(maxCandidates, apiSettings))
                .post(body)
                .build();
    }

    public static Request describeRequest(int maxCandidates, String url, ApiSettings apiSettings) throws JsonProcessingException, MalformedURLException {
        return new Request.Builder()
                .addHeader("Content-Type", "multipart/form-data")
                .addHeader("Ocp-Apim-Subscription-Key", apiSettings.getApiKey())
                .url(createDescribeEndpointUrl(maxCandidates, apiSettings))
                .post(createUrlbody(url))
                .build();
    }


    private static String createBaseUrl(ApiSettings apiSettings) {
        return apiSettings.getLocation() + "/vision/" + apiSettings.getVersion();
    }

    private static HttpUrl createAnalyzeEndpointUrl(VisionAnalyzeRequest visionAnalyzeRequest, ApiSettings apiSettings) throws MalformedURLException {
        HttpUrl httpUrl = HttpUrl.get(new URL(createBaseUrl(apiSettings) + "/analyze"));
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

    private static HttpUrl createDescribeEndpointUrl(int maxCandidates, ApiSettings apiSettings) throws MalformedURLException {
        HttpUrl httpUrl = HttpUrl.get(new URL(createBaseUrl(apiSettings) + "/describe"));
        if (httpUrl == null) {
            throw new IllegalArgumentException("Invalid URL");
        }
        HttpUrl.Builder builder = httpUrl.newBuilder();
        if (maxCandidates > 1) {
            builder.addQueryParameter("maxCandidates", String.valueOf(maxCandidates));
        }
        return builder.build();
    }

    private static RequestBody createImageBody(byte[] image) {
        return new MultipartBody.Builder()
                .addPart(
                        RequestBody.create(MediaType.parse("multipart/form-data"), image)
                )
                .build();
    }

    private static RequestBody createUrlbody(String url) throws JsonProcessingException {
        return RequestBody.create(MediaType.parse("application/json"), Mapper.getInstance().write(new UrlRequest(url)));
    }
}
