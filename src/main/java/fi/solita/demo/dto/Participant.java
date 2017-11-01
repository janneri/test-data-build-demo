package fi.solita.demo.dto;

public final class Participant {
    public final long participantId;
    public final long roomId;
    public final String name;
    public final String username;

    public Participant(long participantId, long roomId, String name, String username) {
        this.participantId = participantId;
        this.roomId = roomId;
        this.name = name;
        this.username = username;
    }
}
