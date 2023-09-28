import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.imaging.ImageMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.Path;

public class HiddenSecrets {
    public static class PathToFile {
        public static void main(String[] args) {
            Path path = Path.of("C:/Users/olivi/OneDrive/Documents/Documents/Object oriented programming/OllieTheOtter.jpg/");

            File file = path.toFile();

            System.out.println("File: " + file.getAbsolutePath());
        }
    }

    public static void getHiddenSecrets(File file) {
        try {
            Metadata metadata = ImageMetadataReader.readMetadata(
                    new FileInputStream(file)
            );
            for (Directory directory : metadata.getDirectories()) {
                for (Tag tag : directory.getTags()) {
                    System.out.format("[%s] - %s = %s%n",
                            directory.getName(), tag.getTagName(), tag.getDescription());
                }
                if (directory.hasErrors()) {
                    for (String error : directory.getErrors()) {
                        System.err.format("ERROR: %s%n", error);
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter a file path: ");

        String filePathString = scanner.nextLine();

        Path path = Paths.get(filePathString);

        File file = path.toFile();
        if (file.exists()) {
            getHiddenSecrets(file);
        } else {
            System.out.println("File does not exist: " + path);
        }
    }
}
