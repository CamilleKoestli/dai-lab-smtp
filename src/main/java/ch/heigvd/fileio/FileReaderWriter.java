package ch.heigvd.fileio;

import ch.heigvd.smtp.Message;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

    /*
    //TODO correct de le mettre ici? ou dans MEssageManager
    public List<Message> readEmailContentFile(File file, Charset encoding, String emailSeparator) {
        List<Message> messages = new ArrayList<>();
        String END_OF_LINE = "\n";
        String subject = null;
        StringBuilder body = new StringBuilder();

        try(BufferedReader is = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), encoding))){

            String currentLine;
            while((currentLine = is.readLine()) != null){
                if (currentLine.startsWith("Subject:")) {
                    // 9 étant la case du string à laquelle commence le message
                    subject = currentLine.substring(9);
                } else if (currentLine.equals("")) {
                    continue;
                } else if(!currentLine.equals(emailSeparator)) {
                    body.append(currentLine).append("\n");
                }
                else {
                    res.add(new Message(subject, body.toString()));
                    // Clear du StringBuilder
                    body.setLength(0);
                }
            }
            is.close();
            return content.toString();
        } catch (IOException e){
            System.out.println("Exception: " + e);
        }
        return null;
    }*/

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
