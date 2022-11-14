package mds.project.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import static mds.project.FilePaths.DASH_DIRECTORY;

public class VideoLibrary extends ArrayList<String> {
    private String searchDirectory = DASH_DIRECTORY;
    private String keepSuffix = "mpd";

    public VideoLibrary() throws IOException {
        super();

        for (File file : discoverFiles(Path.of(searchDirectory), keepSuffix)) {
            this.add(file.getParentFile().getName());
        }
    }



    private static ArrayList<File> discoverFiles(Path directory, String keepSuffix) throws IOException {
        ArrayList<File> files = new ArrayList<>();

        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                String filePath = String.valueOf(file);
                if (filePath.endsWith(keepSuffix)) files.add(new File(filePath));
                return super.visitFile(file, attrs);
            }
        });
        return files;
    }
}
