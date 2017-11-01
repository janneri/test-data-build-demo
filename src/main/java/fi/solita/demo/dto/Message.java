package fi.solita.demo.dto;

public final class Message {
    public final long messageId;
    public final long roomId;
    public final long sender;
    public final long recipient;
    public final String message;

    public Message(long messageId, long roomId, long sender, long recipient, String message) {
        this.messageId = messageId;
        this.roomId = roomId;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }
}
