package io.einharjar.cognitive_wrapper.async;

import io.einharjar.cognitive_wrapper.computer_vision.error.ResponseError;

import java.io.IOException;

/**
 * Created by omar on 2017-09-06.
 */
public interface ResponseCallback<T> {
    void onSuccess(T response);
    void onFailure(ResponseError responseError);
    void onFailure(IOException e);
}
