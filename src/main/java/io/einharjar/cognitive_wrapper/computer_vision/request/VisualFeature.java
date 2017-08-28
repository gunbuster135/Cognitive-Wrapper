package io.einharjar.cognitive_wrapper.computer_vision.request;

/**
 * Created by omar on 2017-08-18.
 */
public enum VisualFeature {
    CATEGORIES("Categories"),
    TAGS("Tags"),
    DESCRIPTION("Description"),
    FACES("Faces"),
    IMAGE_TYPE("ImageType"),
    COLOR("Color"),
    ADULT("Adult");
    private String value;

    VisualFeature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString(){
        return value;
    }
}
