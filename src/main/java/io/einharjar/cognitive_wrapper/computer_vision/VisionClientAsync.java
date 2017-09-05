package io.einharjar.cognitive_wrapper.computer_vision;

import io.einharjar.cognitive_wrapper.ApiSettings;
import io.einharjar.cognitive_wrapper.async.ResponseCallback;
import io.einharjar.cognitive_wrapper.computer_vision.error.ApiException;
import io.einharjar.cognitive_wrapper.computer_vision.response.DescribeImageResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import java.io.IOException;
import java.net.MalformedURLException;
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
}
