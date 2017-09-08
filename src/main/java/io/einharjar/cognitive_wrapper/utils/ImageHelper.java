package io.einharjar.cognitive_wrapper.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by omar on 2017-08-30.
 */
public final class ImageHelper {

    public static byte[] readImage(String path) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(path));
        ObjectHelper.checkNull(bufferedImage, "File is not a valid image");
        return readImage(bufferedImage);
    }

    public static byte[] readImage(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        ObjectHelper.checkNull(bufferedImage, "File is not a valid image!");
        return readImage(bufferedImage);
    }

    public static byte[] readImage(BufferedImage bufferedImage) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "jpg", baos);
            baos.flush();
            return baos.toByteArray();
        }
    }
}
