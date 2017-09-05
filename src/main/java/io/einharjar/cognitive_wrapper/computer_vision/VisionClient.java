package io.einharjar.cognitive_wrapper.computer_vision;

import io.einharjar.cognitive_wrapper.ApiSettings;
import io.einharjar.cognitive_wrapper.computer_vision.error.ApiException;
import io.einharjar.cognitive_wrapper.computer_vision.error.ResponseError;
import io.einharjar.cognitive_wrapper.computer_vision.request.VisionAnalyzeRequest;
import io.einharjar.cognitive_wrapper.computer_vision.response.DescribeImageResponse;
import io.einharjar.cognitive_wrapper.computer_vision.response.VisionAnalyzeResponse;
import io.einharjar.cognitive_wrapper.utils.ImageHelper;
import io.einharjar.cognitive_wrapper.utils.Mapper;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static io.einharjar.cognitive_wrapper.computer_vision.VisionRequests.*;
import static io.einharjar.cognitive_wrapper.utils.ObjectHelper.*;

public class VisionClient {
    private OkHttpClient client;
    private ApiSettings apiSettings;

    public VisionClient(ApiSettings apiSettings, OkHttpClient client) throws MalformedURLException {
        checkNull(apiSettings, "Api Settings cannot be null!");
        this.apiSettings = apiSettings;
        this.client = client;
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, BufferedImage image) throws IOException, ApiException {
        return imageAnalyze(request, ImageHelper.readImage(image));
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, File image) throws IOException, ApiException {
        return imageAnalyze(request, ImageHelper.readImage(image));
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, byte[] image) throws ApiException, IOException {
        Response response = client.newCall(analyzeRequest(request, image, apiSettings)).execute();
        return handleResponse(response, VisionAnalyzeResponse.class);
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, URL url) throws IOException, ApiException {
        return imageAnalyze(request, url.toString());
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, String url) throws IOException, ApiException {
        Response response = client.newCall(analyzeRequest(request, url, apiSettings)).execute();
        return handleResponse(response, VisionAnalyzeResponse.class);
    }

    public DescribeImageResponse describeImage(int maxCandidates, BufferedImage image) throws IOException, ApiException {
        return describeImage(maxCandidates, ImageHelper.readImage(image));
    }


    public DescribeImageResponse describeImage(int maxCandidates, File image) throws IOException, ApiException {
        return describeImage(maxCandidates, ImageHelper.readImage(image));
    }

    public DescribeImageResponse describeImage(int maxCandidates, byte[] image) throws ApiException, IOException {
        Response response = client.newCall(describeRequest(maxCandidates, image, apiSettings)).execute();
        return handleResponse(response, DescribeImageResponse.class);
    }

    public DescribeImageResponse describeImage(int maxCandidates, URL url) throws IOException, ApiException {
        return describeImage(maxCandidates, url.toString());
    }

    public DescribeImageResponse describeImage(int maxCandidates, String url) throws IOException, ApiException {
        Response response = client.newCall(describeRequest(maxCandidates, url, apiSettings)).execute();
        return handleResponse(response, DescribeImageResponse.class);
    }

    //Generic response handler
    private <T> T handleResponse(Response response, Class<T> responseClass) throws ApiException, IOException {
        if (response.isSuccessful() && response.body() != null) {
            return Mapper.getInstance().read(response.body().string(), responseClass);
        } else if (response.code() == 400 || response.code() == 415 || response.code() == 500) {
            throw new ApiException(Mapper.getInstance().read(response.body().string(), ResponseError.class));
        } else {
            return null;
        }
    }
}
