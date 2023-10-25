package lab2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

class TextDocument extends Document {
    public TextDocument(String path) {
        super(path);
    }

    @Override
    public void printInfo() {
        System.out.println("File: " + name);
        System.out.println("Extension: " + getPrettyExtension());
        try {
            BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            System.out.println("Created: " + formatDateTime(attrs.creationTime()));
            System.out.println("Last Modified: " + formatDateTime(attrs.lastModifiedTime()));

            long lineCount = Files.lines(file.toPath()).count();
            String content = Files.readString(file.toPath());
            long wordCount = 0;
            if (!content.isEmpty()) {
                wordCount = content.split("\\s+").length;
            }
            long charCount = content.length();

            System.out.println("Line Count: " + lineCount);
            System.out.println("Word Count: " + wordCount);
            System.out.println("Character Count: " + charCount);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
