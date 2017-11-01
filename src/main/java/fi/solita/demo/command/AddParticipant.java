package fi.solita.demo.command;

public final class AddParticipant {
    public final long roomId;
    public final String name;
    public final String username;

    private AddParticipant(Builder builder) {
        this.roomId = builder.roomId;
        this.name = builder.name;
        this.username = builder.username;
    }

    public static class Builder {
        private long roomId;
        private String name;
        private String username;

        public Builder roomId(long roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public AddParticipant build() {
            return new AddParticipant(this);
        }
    }
}
