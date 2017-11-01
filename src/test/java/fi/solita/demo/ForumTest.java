package fi.solita.demo;


import fi.solita.demo.command.AddParticipant;
import fi.solita.demo.command.SendMessage;
import fi.solita.demo.dto.Message;
import fi.solita.demo.dto.User;
import fi.solita.demo.testdata.RoomBuilder;
import fi.solita.demo.testdata.TestdataDao;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static fi.solita.demo.testdata.ParticipantBuilder.randomParticipant;
import static fi.solita.demo.testdata.RandomTestDataGenerator.randomString;
import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ForumTest {

    @Autowired
    private ForumService forumService;

    @Autowired
    private TestdataDao testdataDao;

    @After
    public void cleanDatabase() {
        testdataDao.cleanDatabase();
    }


    @Test
    public void admin_can_list_dangerous_messages_for_moderation() {
        String dangerousMessage = "fuck this shit !!";
        RoomBuilder roomBuilder = new RoomBuilder()
                .withParticipants(3)
                .withMessages(4)
                .withMessage(dangerousMessage);
        testdataDao.insertRoom(roomBuilder);


        List<Message> dangerousMessages = forumService.findDangerousMessages();


        assertEquals( 1, dangerousMessages.size());
        assertEquals(dangerousMessage, dangerousMessages.get(0).message);
    }


    @Test
    public void can_send_a_message_from_participant_to_another() {
        RoomBuilder roomBuilder = new RoomBuilder().withParticipants(2);
        testdataDao.insertRoom(roomBuilder);
        long senderParticipantId = roomBuilder.participantIds.get(0);
        long receiverParticipantId = roomBuilder.participantIds.get(1);
        String message = randomString(100);


        forumService.sendMessage(
                new SendMessage(roomBuilder.room.roomId, senderParticipantId, receiverParticipantId, message));


        final List<Message> messages = forumService.getMessages(roomBuilder.room.roomId);
        assertEquals(1, messages.size());
        assertEquals(message, messages.get(0).message);
        assertEquals(senderParticipantId, messages.get(0).sender);
        assertEquals(receiverParticipantId, messages.get(0).recipient);
    }


    @Test
    public void user_can_join_rooms_with_room_specific_names() {
        RoomBuilder room1 = new RoomBuilder();
        RoomBuilder room2 = new RoomBuilder();
        testdataDao.insertRoom(room1);
        testdataDao.insertRoom(room2);
        User user = testdataDao.insertUser();
        AddParticipant.Builder participant1 = randomParticipant().username(user.username);
        AddParticipant.Builder participant2 = randomParticipant().username(user.username);


        forumService.joinRoomWithName(participant1.roomId(room1.room.roomId).build());
        forumService.joinRoomWithName(participant2.roomId(room2.room.roomId).build());


        assertEquals(1, forumService.getParticipants(room1.room.roomId).size());
        assertEquals(1, forumService.getParticipants(room2.room.roomId).size());
        assertEquals(participant2.build().name, forumService.getParticipants(room2.room.roomId).get(0).name);
    }

}