package fi.solita.demo.testdata;

import fi.solita.demo.command.AddParticipant;
import fi.solita.demo.dto.User;

import static fi.solita.demo.testdata.RandomTestDataGenerator.randomString;

public class ParticipantBuilder {

    public static User randomUser() {
           return new User(randomString(10, "abcjdefg12345"),
                   randomString(5, "aeioul"));
    }

    public static AddParticipant.Builder randomParticipant() {
        return new AddParticipant.Builder()
                        .username(randomString(10))
                        .name(randomString(5));
    }

}
