package ch.heigvd.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;
import java.util.SortedMap;

public class EncodingSelector {

    /**
     * Get the encoding of a file based on its extension.
     * The following extensions are recognized:
     *   - .utf8:    UTF-8
     *   - .txt:     US-ASCII
     *   - .utf16be: UTF-16BE
     *   - .utf16le: UTF-16LE
     *
     * @param file the file to get the encoding from
     * @return the encoding of the file, or null if the extension is not recognized
     */
    public Charset getEncoding(File file) {
        // TODO: implement the method body here
        String fileName = file.getName().toLowerCase();

        // Map where file extensions (as keys) are associated with their corresponding character encodings.
        SortedMap<String, Charset> extensionCharsetMap = new TreeMap<>();

        extensionCharsetMap.put("utf8", StandardCharsets.UTF_8);
        extensionCharsetMap.put("txt", StandardCharsets.US_ASCII);
        extensionCharsetMap.put("utf16be", StandardCharsets.UTF_16BE);
        extensionCharsetMap.put("utf16le", StandardCharsets.UTF_16LE);

        for(String extension : extensionCharsetMap.keySet()){
            if(fileName.endsWith(extension)){
                return extensionCharsetMap.get(extension);
            }
        }
        return null;
    }
}
