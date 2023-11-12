package lab2.entity;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.format.DateTimeFormatter;
import java.time.*;

public abstract class Document {
    protected String name;
    protected String extension;
    protected File file;

    public Document(String path) {
        this.file = new File(path);
        this.name = file.getName();
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex > 0) {
            this.extension = name.substring(dotIndex + 1).toLowerCase();
        } else {
            this.extension = "";
        }
    }

    public String getName() {
        return name;
    }

    public String getPrettyExtension() {
        return extension.toUpperCase();
    }

    public abstract void printInfo();

    public void printGeneralInfo(){
        System.out.println("File: " + name);
        System.out.println("Extension: " + getPrettyExtension());
        try{
            BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            System.out.println("Created: " + formatDateTime(attrs.creationTime()));
            System.out.println("Last Modified: " + formatDateTime(attrs.lastModifiedTime()));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    protected String formatDateTime(FileTime fileTime) {
        Instant instant = fileTime.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(zdt);
    }
}