package fi.solita.demo.testdata;

import fi.solita.demo.command.AddParticipant;
import fi.solita.demo.command.SendMessage;
import fi.solita.demo.dto.Room;
import fi.solita.demo.dto.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static fi.solita.demo.testdata.RandomTestDataGenerator.randomString;

public class RoomBuilder {

    public Room.Builder room = new Room.Builder().name("A room");
    public List<User> users = new ArrayList<>();
    public List<AddParticipant.Builder> participants = new ArrayList<>();
    public List<Long> participantIds = new ArrayList<>();
    public List<SendMessage.Builder> messages = new ArrayList<>();


    public RoomBuilder withParticipants(int count) {
        IntStream.range(0, count).forEach(i -> users.add(ParticipantBuilder.randomUser()));

        IntStream.range(0, count).forEach(
                i -> this.participants.add(ParticipantBuilder.randomParticipant().username(users.get(i).username))
        );
        return this;
    }


    public RoomBuilder withMessages(int count) {
        IntStream.range(0, count).forEach(i -> messages.add(
                new SendMessage.Builder()
                    .message(randomString(10))
        ));
        return this;
    }

    public RoomBuilder withMessage(String message) {
        this.messages.add(new SendMessage.Builder().message(message));
        return this;
    }


}
