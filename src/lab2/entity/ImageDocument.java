package lab2.entity;

import lab2.entity.Document;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import javax.imageio.ImageIO;

public class ImageDocument extends Document {
    public ImageDocument(String path) {
        super(path);
    }

    @Override
    public void printInfo() {
        super.printGeneralInfo();
        try {
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                System.out.println("Image Size: " + width + "x" + height);
            } else {
                System.out.println("Image Size: Cannot determine (not a supported image format)");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
