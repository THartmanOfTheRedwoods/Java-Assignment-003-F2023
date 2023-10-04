import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.Directory;
import com.drew.metadata.Tag;
import com.drew.imaging.ImageMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Path;
import com.drew.metadata.Metadata;

// PUT YOUR IMPORTS HERE

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
        } catch (FileNotFoundException fnfe) {
            System.out.println("That file does not exist.");
        } catch (IOException ioe) {
            System.out.println("Problem reading from file stream.");
        } catch (ImageProcessingException ipe) {
            System.out.println("Failed to process the image meta-data");
        }
    }

    public static void main(String[] args) throws IOException {
        // Put your code to request a file path,

        Scanner wat = new Scanner(System.in);
        // read in a string from System.in,
        //input a Sout line
        System.out.println("Type 'images/OllieTheOtter.jpg' :)");
        String idk = wat.nextLine();
        // convert that string into A Path type using Paths class,

        //Paths.get(idk,null);
     Path who = Paths.get(idk);

      // File why = new File(who);
        // File ugh = (File)Files.createFile(who);
        // and call the getHiddenSecrets method to get the file's meta-data
        getHiddenSecrets(who.toFile());
        // HERE
    }
}
/**
 *
 * 40° 46' 37.46" N
 *  -124° 8' 41.55" W
 */
