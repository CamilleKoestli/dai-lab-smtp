package java.ch.heigvd.dai.lab.fileio;

import java.io.*;
import java.nio.charset.Charset;

public class FileReaderWriter {

    /**
     * Read the content of a file with a given encoding.
     * @param file the file to read.
     * @param encoding the encoding to use
     * @return the content of the file as a String, or null if an error occurred.
     */
    public String readFile(File file, Charset encoding) {
        // TODO: Implement the method body here.
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to close the streams and readers at the end.

        String END_OF_LINE = "\n";
        StringBuilder content = new StringBuilder();

        try(BufferedReader is = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), encoding))){

            String currentLine;
            while((currentLine = is.readLine()) != null){
                content.append(currentLine).append(END_OF_LINE);
            }
            is.close();
            return content.toString();
        } catch (IOException e){
            System.out.println("Exception: " + e);
        }
        return null;
    }
    /**
     * Write the content to a file with a given encoding.
     * @param file the file to write to
     * @param content the content to write
     * @param encoding the encoding to use
     * @return true if the file was written successfully, false otherwise
     */
    public boolean writeFile(File file, String content, Charset encoding) {
        // TODO: Implement the method body here.
        // Use the ...Stream and ...Reader classes from the java.io package.
        // Make sure to flush the data and close the streams and readers at the end.
        try(BufferedWriter os = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), encoding))){
            os.write(content);
            os.flush();
            os.close();
            return true;
        } catch (IOException e){
            System.out.println("Exception: " + e);
        }
        return false;
    }
}
