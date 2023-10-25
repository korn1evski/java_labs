package lab2;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;
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
        snapshot.takeSnapshot(documents);
        System.out.println("Created Snapshot at: " + LocalDateTime.now());
    }

    public void printFileInfo(String fileName) {
        for (Document doc : documents) {
            if (doc.getName().equals(fileName)) {
                doc.printInfo();
                return;
            }
        }
        System.out.println("File not found: " + fileName);
    }

    public void printStatus() {
        // Refresh the list of documents
        loadDocuments(folderPath);

        if (snapshot.isEmpty()) {
            System.out.println("No snapshot found. Please make your first commit.");
        } else {
            Set<String> existingFiles = snapshot.getFileNames();

            // First, print the status of existing files
            for (Document doc : documents) {
                if (existingFiles.contains(doc.getName())) {
                    snapshot.printFileStatus(doc);
                }
            }

            // Then, report new files
            for (Document doc : documents) {
                if (!existingFiles.contains(doc.getName())) {
                    System.out.println(doc.getName() + " Not in snapshot");
                }
            }
        }
    }

}