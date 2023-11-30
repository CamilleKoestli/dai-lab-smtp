package java.ch.heigvd.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import java.ch.heigvd.dai.lab.smtp.MessageCreator;

public class MessageManager {

    private final File file;
    private final FileReaderWriter fileReaderWriter;
    private final EncodingSelector encodingSelector;

    public MessageManager(String file) {
        this.file = new File(file);
        this.fileReaderWriter = new FileReaderWriter();
        this.encodingSelector = new EncodingSelector();
    }

    public MessageCreator createRandomMessage() {
        // Utilisez la classe FileReaderWriter pour lire le contenu du fichier
        Charset encoding = encodingSelector.getEncoding(file);
        String messageContent = fileReaderWriter.readFile(file, encoding);

        // Supposons que MessageCreator ait un constructeur qui prend le contenu du message et crée un objet Message
        return new MessageCreator("", messageContent);
    }
}
