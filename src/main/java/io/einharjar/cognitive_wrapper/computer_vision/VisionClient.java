package io.einharjar.cognitive_wrapper.computer_vision;

import io.einharjar.cognitive_wrapper.computer_vision.error.ApiException;
import io.einharjar.cognitive_wrapper.computer_vision.error.ResponseError;
import io.einharjar.cognitive_wrapper.computer_vision.request.VisionAnalyzeRequest;
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

@SuppressWarnings("Duplicates")
public class VisionClient {
    private VisionRequests visionRequests;
    private OkHttpClient client;

    public VisionClient(ApiSettings apiSettings, OkHttpClient client) throws MalformedURLException {
        this.client = client;
        visionRequests = new VisionRequests(apiSettings);
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, BufferedImage image) throws IOException, ApiException {
        return imageAnalyze(request, ImageHelper.readImage(image));
    }


    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, File image) throws IOException, ApiException {
        return imageAnalyze(request, ImageHelper.readImage(image));
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, byte[] image) throws ApiException, IOException {
        Response response = client.newCall(visionRequests.analyzeRequest(request, image)).execute();
        if (response.isSuccessful() && response.body() != null) {
            return Mapper.getInstance().read(response.body().string(), VisionAnalyzeResponse.class);
        } else if (response.code() == 400 || response.code() == 415 || response.code() == 500) {
            throw new ApiException(Mapper.getInstance().read(response.body().string(), ResponseError.class));
        }
        return new VisionAnalyzeResponse();
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, URL url) throws IOException, ApiException {
        return imageAnalyze(request, url.toString());
    }

    public VisionAnalyzeResponse imageAnalyze(VisionAnalyzeRequest request, String url) throws IOException, ApiException {
        Response response = client.newCall(visionRequests.analyzeRequest(request, url)).execute();
        if (response.isSuccessful() && response.body() != null) {
            return Mapper.getInstance().read(response.body().string(), VisionAnalyzeResponse.class);
        } else if (response.code() == 400 || response.code() == 415 || response.code() == 500) {
            throw new ApiException(Mapper.getInstance().read(response.body().string(), ResponseError.class));
        }
        return new VisionAnalyzeResponse();
    }
}
