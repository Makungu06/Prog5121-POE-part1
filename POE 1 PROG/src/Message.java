import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    private static int idCounter = 1;
    private String messageID;
    private String messageContent;
    private String sender;
    private String recipient;
    private String timestamp;
    private String hash;

    public Message(String messageContent, String sender, String recipient) {
        this.messageID = generateMessageID();
        this.messageContent = messageContent;
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = generateTimestamp();
        this.hash = generateHash(messageContent);
    }

    private String generateMessageID() {
        return String.format("MSG%03d", idCounter++);
    }

    private String generateTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String generateHash(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(content.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return "HASH_ERROR";
        }
    }

    public String getMessageID() {
        return messageID;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
        this.hash = generateHash(messageContent);
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageID='" + messageID + '\'' +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", content='" + messageContent + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }
}