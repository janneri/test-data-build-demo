package fi.solita.demo.command;

public final class SendMessage {
    public final long roomId;
    public final long sender;
    public final long recipient;
    public final String message;

    public SendMessage(long roomId, long sender, long recipient, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.recipient = recipient;
        this.message = message;
    }

    private SendMessage(Builder builder) {
        this.roomId = builder.roomId;
        this.sender = builder.sender;
        this.recipient = builder.recipient;
        this.message = builder.message;
    }


    public static class Builder {
        private long roomId;
        private long sender;
        private long recipient;
        private String message;

        public Builder roomId(long roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder sender(long sender) {
            this.sender = sender;
            return this;
        }

        public Builder recipient(long recipient) {
            this.recipient = recipient;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public SendMessage build() {
            return new SendMessage(this);
        }
    }
}
