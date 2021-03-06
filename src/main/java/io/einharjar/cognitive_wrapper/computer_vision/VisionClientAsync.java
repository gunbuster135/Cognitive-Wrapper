package io.einharjar.cognitive_wrapper.computer_vision;

import io.einharjar.cognitive_wrapper.ApiSettings;
import io.einharjar.cognitive_wrapper.async.ResponseCallback;
import io.einharjar.cognitive_wrapper.computer_vision.error.ApiException;
import io.einharjar.cognitive_wrapper.computer_vision.response.DescribeImageResponse;
import io.einharjar.cognitive_wrapper.utils.ImageHelper;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static io.einharjar.cognitive_wrapper.computer_vision.VisionClient.handleResponse;
import static io.einharjar.cognitive_wrapper.computer_vision.VisionRequests.describeRequest;
import static io.einharjar.cognitive_wrapper.utils.ObjectHelper.checkNull;

public class VisionClientAsync {
    private OkHttpClient client;
    private ApiSettings apiSettings;

    public VisionClientAsync(ApiSettings apiSettings, OkHttpClient client) throws MalformedURLException {
        checkNull(apiSettings, "Api Settings cannot be null!");
        this.apiSettings = apiSettings;
        this.client = client;
    }

    public void describeImage(int maxCandidates, BufferedImage image, final ResponseCallback<DescribeImageResponse> cb) throws IOException {
        describeImageAsync(maxCandidates, ImageHelper.readImage(image), cb);
    }


    public void describeImage(int maxCandidates, File image, final ResponseCallback<DescribeImageResponse> cb) throws IOException {
        describeImageAsync(maxCandidates, ImageHelper.readImage(image), cb);
    }

    public void describeImageAsync(int maxCandidates, URL url, final ResponseCallback<DescribeImageResponse> cb) throws IOException {
        describeImageAsync(maxCandidates, url.toString(), cb);
    }

    public void describeImageAsync(int maxCandidates, String url, final ResponseCallback<DescribeImageResponse> cb) throws IOException {
        client.newCall(describeRequest(maxCandidates, url, apiSettings)).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                cb.onFailure(e);
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    cb.onSuccess(handleResponse(response, DescribeImageResponse.class));
                } catch (ApiException e) {
                    cb.onFailure(e.getError());
                }
            }
        });
    }

    public void describeImageAsync(int maxCandidates, byte[] image, final ResponseCallback<DescribeImageResponse> cb) throws IOException {
        client.newCall(describeRequest(maxCandidates, image, apiSettings)).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                cb.onFailure(e);
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    cb.onSuccess(handleResponse(response, DescribeImageResponse.class));
                } catch (ApiException e) {
                    cb.onFailure(e.getError());
                }
            }
        });
    }
}
