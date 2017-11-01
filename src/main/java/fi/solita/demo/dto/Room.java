package fi.solita.demo.dto;

public final class Room {
    public final long roomId;
    public final String name;

    private Room(Builder builder) {
        this.roomId = builder.roomId;
        this.name = builder.name;
    }


    public static class Builder {
        public long roomId;
        public String name;

        public Builder roomId(long roomId) {
            this.roomId = roomId;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
