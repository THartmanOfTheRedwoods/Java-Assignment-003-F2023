# Java-Assignment-003

### Input, Packages, Imports, and a static Method

## PART 1 - Build an External Package (JAR short for Java Archive)

A Jar is really just a Zip file with some added Metadata (i.e. data about data). They are organized much the same way file systems are organized, think folders nested in folders with importable Java classes being the files in those folders. So if you have a folder hierarchy like **edu/redwoods/cis12** and you had several Java files in the cis12 subfolder named **Class1** and **Class2** you could Zip it up into a **.jar** file, import it into your IntelliJ project, and then import those classes like so:

```
import edu.redwoods.cis12.Class1;
import edu.redwoods.cis12.Class1;
```

This is a slight over-simplification because you would also have to include a **package** directive at the top of your Java classes, and you should add additional package metadata, but the comparison is pretty close to reality.


1. Watch the below linked video, and follow along on how we build a **Java Archive** artifact (**jar**) from a GitHub project.
    * [Jar file build from GitHub Project](https://redwoods.us-west-2.instructuremedia.com/embed/517c63e3-9624-4880-a46f-7eea3efe338a)
1. If you didn't already, attempt to build the artifact/jar file while watching the video, try to build **the jar file** yourself now!
    * [Metadata Extractor Code on GitHub](https://github.com/drewnoakes/metadata-extractor)
1. Watch the below linked video, and follow along to import an external **jar** file into your project!
    * [Import External Jar into your Project](https://redwoods.us-west-2.instructuremedia.com/embed/92f5cfd8-59f3-48b8-bbfe-a16c664625ac)
1. If your artifact build/creation step was successful, see if you can import your build of the metadata jar file into Java-Assignment-003 after you start PART 2 below!

## PART 2 - Implementation

Just like you do every week now!!!

* **Fork** my assignment repo
* Use IntelliJ to **clone** your fork locally
* Use IntelliJ to create a local **feature branch**
* Complete the following assignment:

Your assignment is to use Built-In java classes to:

1. Import the following java Built-In classes from the **java.nio** and **java.util** packages respectively.
    * ```
      java.nio.file.Paths;
      java.util.Scanner;
      java.nio.file.Path;
      ```
1. Read a **jpg file path** from the user.
     * HINT 1: Create a **Scanner** object instance and use it to read a string.
     * HINT 2: Use **Paths** class to get a path from your input!
         * Google java.nio.file.Paths for help OR [Look for examples HERE](https://www.geeksforgeeks.org/java-nio-file-paths-class-in-java/)
1. From your path object call a **method** that converts the file path to a **File** type object (**Cause that's what the provided getHiddenSecret method declares as its parameters**).
1. Pass the file object as an argument to the given **getHiddenSecrets** method.
1. Run the program and type in the path for our sample image, and record the GPS coordinates in the output.
  ![Ollie the Otter Image](images/OllieTheOtter.jpg)
    * HINT: the path can be relative to the project directory, maybe use the string in the example above :-)
1. Look up the latitude and longitude coordinates in any online map you can find via Google.
1. Screenshot the map and add it into the **images** folder of this project.
1. Last add image markdown below this line to load your map image (Hint: Example image Markdown is just a couple lines above this).

## PART 3 - Code Scanning and Interpretation

* Look at the getHiddenSecrets method and identify the following parts by editing this README.md and providing your answers:
    * What is the **access modifer** (e.g. public, private, protected)?
        * The access modifier is `public`.
    * Is it a **Class method** or an **object Instance method**, how do you know?
      * `getHiddenSecrets` is a Class Method. This is hinted by the method's camelCase naming convention, as well as 
        the fact that the `getHiddenSecrets` method is `static`.
    * What is its **return data-type**?
      * The `getHiddenSecrets` Class method has a `void` return data-type.
    * Does it require any **arguments** to call it, and if so, how many **parameters** and of what **data-type**?
      * The `getHiddenSecrets` method requires a `File` type parameter to be passed
* Scan line by line through the code and try to determine how it works?
    * What is familiar to you?
      * Ok, so from top to bottom: I am not particularly familiar with any of the imported packaged except 
      `java.util.Scanner`, but am familiar with `import`. I feel good with Lines `14` through `56` being the 
      `HiddenSecrets` class, `15` through `38` being the `getHiddenSecrets` Class method, and `40` through `55` 
      being the `main` method. I understand that `public` on lines `14`, `15`, and `40` allows external classes to access the 
      `HiddenSecrets` class, `getHiddenSecrets` method, and `main` method, respectively. 
      * With respect to the `getHiddenSecrets` method:
        * Line `17`: A `Metadata` type object is declared and named `metadata`. A `File` type object named `file` is 
        passed through the `FileInputStream` constructor. In the backend, an object named `FileDescripter` is created 
        to represent the connection to the file in the directory. The constructed FileInputStream object is scanned for
        metadata--the file is read and a `long` type object named `streamLength` is created to represent the size of the
        file, then an instance of a `BufferedInputStream` object is created and named `bufferedInputStream` to represent
        the file's data, and then a `FileType` object named `fileType` is created and assigned to the file type 
        determined by passing the `bufferedInputStream` object through the `detectFileType` method contained within the 
        `FileTypeDetector` class file. The resulting `bufferedInputStream`, `streamLength`, and `fileType` objects are 
        then passed through the `readMetadata` method contained within the imported `ImageMetadataReader` class file; 
        part of the .jar created earlier in the assignment. The returned metadata is then assigned to the `metadata` 
        variable.
        * Lines `20` to `23`: Here we have a `for-each` loop. The `metadata` object is passed through the 
        `getDirectories` method contained within the `Directory` class file. In the `getDirectories` Method Signature,
        `Iterable<Directory>` specifies the `return` type as an iterable collection of objects of type `Directory`. Each
        `Directory` object in the returned `Collection` is iteratively assigned to a `Directory` object named 
        `directory` where it then enters a nested `for-each` loop. In the nested loop, each `directory` object is 
        passed through the `getTags` method contained in the `Directory` class file. Just like the `getDirectories` 
        method, the `getTags` method returns a collection of tags. Unlike the `getDirectories` method, the `getTags`
        method returns an unmodifiable view of the tags within each `directory` object so as to not intentionally or
        accidentally modify the metadata. Each metadata tag in the directory object is iteritivey assigned the `Tag` 
        object named `tag`, and then printed. After the collection of `tag` objects are exhausted, the next `Directory` 
        object in the returned directory collection is assigned directory, and the nested loop repeats to find a print 
        each metadata tag.
        * Lines `25` to `27`: A `String` object is declared and named `error`. The `directory` object is passed through 
        the `hasErrors` method contained within the `Directory` class file. The `hasErrors` method returns a `boolean` 
        type `true` if the currently assigned `directory` object contains errors. If errors are present in the 
        `directory` object, a nested `for-each` loop is entered. The `directory` object is passed through the 
        `getErrors` method. The Method Signature of the `getErrors` method specifies the return type as an iterable list 
        of `String` objects. Similar to the `getTags` method, the `getErrors` method returns an unmodifiable view of the
        list of `String` objects. Each `String` object in the returned iterated error list is assigned to the `String` 
        variable `error`, and then past through the error output stream `System.err.format()`.
    * What is not familiar to you?
      * I'm not familiar with the `try` or `catch` tokens. I'm not sure what either of those do. Although, I can tell
      by context of the `System.out.println` methods on lines `32`, `34`, and `36` that they "catch" a `boolean` value. 
      In this case, A `FileNotFoundException` type is declared and named `fnfe`, an `IOException` is declared and named 
      `ioe`, and an `ImageProcessingException` type is declared and named `ipe`. A truth value of `true` in any of these
      `boolean` type objects would result in its corresponding error message to be printed.
      
    * Do the **for** loops make sense, and if so, tell me what you think they do?
      * I think they do. I sure did put a good lot of syllables together from reading the dependant class files making
      sense of it.

## PART 4 - Turn in

### Same as the last 2 Weeks!

* When completed, use your IDE to **Commit** and **Push** your **feature branch** back to your GitHub Account's **fork** of Java-Assignment-003.
* From your GitHub account's feature branch, issue a New **Pull request** from your **feature branch** to the instructor's fork
* Save the *Pull Request URL* to submit to your Canvas assignment.
