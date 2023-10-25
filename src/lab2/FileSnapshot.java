package lab2;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.util.*;

class FileSnapshot {
    private Map<String, Instant> fileTimestamps = new HashMap<>();

    public void takeSnapshot(List<Document> documents) {
        fileTimestamps.clear();
        for (Document doc : documents) {
            try {
                BasicFileAttributes attrs = Files.readAttributes(doc.file.toPath(), BasicFileAttributes.class);
                fileTimestamps.put(doc.getName(), attrs.lastModifiedTime().toInstant());
            } catch (IOException e) {
                System.out.println("Error reading file attributes: " + e.getMessage());
            }
        }
        saveSnapshotToFile();
    }


    private void saveSnapshotToFile() {
        try {
            List<String> lines = new ArrayList<>();
            for (Map.Entry<String, Instant> entry : fileTimestamps.entrySet()) {
                lines.add(entry.getKey() + "," + entry.getValue().toString());
            }
            Files.write(Paths.get("snapshot.txt"), lines);
        } catch (IOException e) {
            System.out.println("Error saving snapshot to file: " + e.getMessage());
        }
    }

    public Set<String> getFileNames() {
        return new HashSet<>(fileTimestamps.keySet());
    }

    public void printFileStatus(Document doc) {
        String fileName = doc.getName();
        Instant lastModifiedTime = doc.getLastModifiedTime();

        if (fileTimestamps.containsKey(fileName)) {
            Instant snapshotTime = fileTimestamps.get(fileName);
            if (lastModifiedTime.equals(snapshotTime)) {
                System.out.println(fileName + " No Change");
            } else {
                System.out.println(fileName + " Changed");
            }
        }
    }

    public void loadSnapshotFromFile() {
        Path filePath = Paths.get("snapshot.txt");
        if (Files.exists(filePath)) {
            try {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String fileName = parts[0];
                        Instant timestamp = Instant.parse(parts[1]);
                        fileTimestamps.put(fileName, timestamp);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error loading snapshot from file: " + e.getMessage());
            }
        }
    }

    public void printStatus(List<Document> documents) {
        for (Document doc : documents) {
            if (fileTimestamps.containsKey(doc.getName())) {
                if (doc.hasChangedSince(fileTimestamps.get(doc.getName()))) {
                    System.out.println(doc.getName() + " Changed");
                } else {
                    System.out.println(doc.getName() + " No Change");
                }
            } else {
                System.out.println(doc.getName() + " Not in snapshot");
            }
        }
    }
    public boolean isEmpty() {
        return fileTimestamps.isEmpty();
    }

    public boolean containsFile(String fileName) {
        return fileTimestamps.containsKey(fileName);
    }
}