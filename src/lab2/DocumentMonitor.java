package lab2;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

class DocumentMonitor {
    private FileSnapshot snapshot = new FileSnapshot();
    private List<Document> documents = new ArrayList<>();
    private String folderPath;

    public DocumentMonitor(String folderPath) {
        this.folderPath = folderPath;
        loadDocuments(folderPath);
        snapshot.loadSnapshotFromFile();
    }

    private void loadDocuments(String folderPath) {
        documents.clear();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    if (!file.exists())
                        continue;

                    String path = file.getAbsolutePath();
                    String extension = "";
                    int dotIndex = file.getName().lastIndexOf('.');
                    if (dotIndex > 0) {
                        extension = file.getName().substring(dotIndex + 1).toLowerCase();
                    }
                    Document document;
                    switch (extension) {
                        case "txt":
                            document = new TextDocument(path);
                            break;
                        case "png":
                        case "jpg":
                            document = new ImageDocument(path);
                            break;
                        case "py":
                        case "java":
                            document = new ProgramDocument(path);
                            break;
                        default:
                            document = new Document(path) {
                                @Override
                                public void printInfo() {
                                    System.out.println("File: " + name);
                                    System.out.println("Extension: " + getPrettyExtension());
                                    try {
                                        BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                                        System.out.println("Created: " + formatDateTime(attrs.creationTime()));
                                        System.out.println("Last Modified: " + formatDateTime(attrs.lastModifiedTime()));
                                    } catch (IOException e) {
                                        System.out.println("Error reading file: " + e.getMessage());
                                    }
                                }
                            };
                            break;
                    }
                    documents.add(document);
                }
            }
        }
    }

    public void commit() {
        loadDocuments(folderPath);
        snapshot.takeSnapshot(documents);
        System.out.println("Created Snapshot at: " + LocalDateTime.now());
    }

    public void printFileInfo(String fileName) {
        loadDocuments(folderPath);
        for (Document doc : documents) {
            if (doc.getName().equals(fileName)) {
                doc.printInfo();
                return;
            }
        }
        System.out.println("File not found: " + fileName);
    }

    private Map<String, Instant> loadCurrentFileState() {
        Map<String, Instant> currentFileState = new HashMap<>();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    try {
                        BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                        currentFileState.put(file.getName(), attrs.lastModifiedTime().toInstant());
                    } catch (IOException e) {
                        System.out.println("Error reading file attributes: " + e.getMessage());
                    }
                }
            }
        }
        return currentFileState;
    }

    public void printStatus() {
        if (snapshot.isEmpty()) {
            System.out.println("No snapshot found. Please make your first commit.");
        } else {
            Map<String, Instant> currentFileState = loadCurrentFileState();

            for (Map.Entry<String, Instant> entry : currentFileState.entrySet()) {
                String fileName = entry.getKey();
                Instant lastModifiedTime = entry.getValue();

                if (snapshot.containsFile(fileName)) {
                    Instant snapshotTime = snapshot.getFileTimestamp(fileName);
                    if (lastModifiedTime.equals(snapshotTime)) {
                        System.out.println(fileName + " No Change");
                    } else {
                        System.out.println(fileName + " Changed");
                    }
                } else {
                    System.out.println(fileName + " Not in snapshot");
                }
            }

            Set<String> filesInLastSnapshot = snapshot.getFilesInLastSnapshot();
            for (String fileName : filesInLastSnapshot) {
                if (!currentFileState.containsKey(fileName)) {
                    System.out.println(fileName + " Deleted");
                }
            }
        }
    }



}