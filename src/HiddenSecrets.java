import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.imaging.ImageMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

// PUT YOUR IMPORTS HERE

import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Scanner;

public class HiddenSecrets {
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
            System.out.println("\nLet's let this be our little secret, okay?");
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) {
        // Put your code to request a file path,
        // read in a string from System.in,
        // convert that string into A Path type using Paths class,
        // and call the getHiddenSecrets method to get the file's meta-data

        Scanner scan = new Scanner(System.in); // Create new Scanner named Scan

        System.out.print("Please enter the name and extension of your desired image: ");
        String scanInput = scan.nextLine(); // Request input and store in new String scanInput
        System.out.println("Attempting to parse metadata on " + scanInput);

        String concatInput = "./images/" + scanInput; // Add and concatenate images directory to simplify image request
        Path inputPath = Paths.get(concatInput); // Translate input string into a parsable path named inputPath
        getHiddenSecrets(inputPath.toFile()); // Call getHiddenSecrets method on concatPath pointing to desired filename
    }
}
