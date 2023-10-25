package lab2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.regex.*;

class ProgramDocument extends Document {
    public ProgramDocument(String path) {
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
            System.out.println("Line Count: " + lineCount);

            int classCount = 0;
            int methodCount = 0;
            String content = new String(Files.readAllBytes(file.toPath()));
            Matcher classMatcher = Pattern.compile("\\bclass\\s+\\w+").matcher(content);
            while (classMatcher.find()) {
                classCount++;
            }

            Matcher methodMatcher = Pattern.compile("\\b(public|protected|private|static|final)\\b.*\\b(\\w+)\\s*\\([^\\)]*\\)\\s*\\{").matcher(content);
            while (methodMatcher.find()) {
                methodCount++;
            }

            System.out.println("Class Count: " + classCount);
            System.out.println("Method Count: " + methodCount);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
