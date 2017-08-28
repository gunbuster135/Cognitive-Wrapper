package io.einharjar.cognitive_wrapper.utils;


import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ObjectHelper {

    private ObjectHelper() {

    }

    public static void checkNull(String message, Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkString(String s, String message) {
        if (StringUtils.isBlank(s)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void checkString(String s) {
        if (StringUtils.isBlank(s)) {
            throw new IllegalArgumentException();
        }
    }

    public static byte[] readImage(String path) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(path));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        baos.flush();
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

}
