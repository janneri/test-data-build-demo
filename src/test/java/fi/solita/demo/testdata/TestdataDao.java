package fi.solita.demo.testdata;

import fi.solita.demo.ForumDao;
import fi.solita.demo.UserDao;
import fi.solita.demo.dto.User;
import org.dalesbred.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

@Repository
public class TestdataDao {

    @Autowired
    private ForumDao forumDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Database database;

    // the rooms created by this dao
    private List<Long> roomIds = new ArrayList<>(); // todo RoomId


    @Transactional
    public void cleanDatabase() {
        final String rooms = roomIds.stream().map(String::valueOf).collect(joining(","));
        database.update(String.format("delete from message where room_id in (%s)", rooms));
        database.update(String.format("delete from participant where room_id in (%s)", rooms));
        database.update(String.format("delete from room where room_id in (%s)", rooms));
    }

    @Transactional
    public User insertUser() {
        User user = ParticipantBuilder.randomUser();
        userDao.mergeUser(user);
        return user;
    }

    @Transactional
    public void insertRoom(RoomBuilder roomBuilder) {
        long roomId = forumDao.insertRoom(roomBuilder.room.build());
        roomBuilder.room.roomId(roomId);
        roomIds.add(roomId);

        roomBuilder.users.forEach(user -> userDao.mergeUser(user));

        roomBuilder.participants.forEach(participant ->
                roomBuilder.participantIds.add(
                    forumDao.insertParticipant(participant.roomId(roomId).build())));

        roomBuilder.messages.forEach(message -> {
            forumDao.insertMessage(message
                    .roomId(roomId)
                    .sender(roomBuilder.participantIds.get(0))
                    .recipient(roomBuilder.participantIds.get(1))
                    .build());
        });
    }

}
