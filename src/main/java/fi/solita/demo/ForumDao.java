package fi.solita.demo;

import fi.solita.demo.command.AddParticipant;
import fi.solita.demo.command.SendMessage;
import fi.solita.demo.dto.Message;
import fi.solita.demo.dto.Participant;
import fi.solita.demo.dto.Room;
import org.dalesbred.Database;
import org.dalesbred.result.ResultSetProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ForumDao {

    @Autowired
    private Database db;


    public List<Message> getMessages(long roomId) {
        return db.findAll(Message.class,
                "select message_id, room_id, sender, recipient, message from message where room_id = ?", roomId);
    }

    public List<Message> findDangerousMessages() {
        return db.findAll(Message.class,
                "select message_id, room_id, sender, recipient, message from message " +
                        "where message like '%fuck%' or message like '%shit%'");
    }

    public List<Participant> getParticipants(long roomId) {
        return db.findAll(Participant.class,
                "select participant_id, room_id, name, username from participant where room_id = ?", roomId);
    }

    public long insertRoom(Room room) {
        return insert(db, "room_id","insert into room(name) values (?)", room.name);
    }

    public long insertParticipant(AddParticipant participant) {
        return insert(db, "participant_id",
                "insert into participant(room_id, name, username) values (?, ?, ?)",
                participant.roomId, participant.name, participant.username);
    }

    public long insertMessage(SendMessage message) {
        return insert(db, "message_id",
                "insert into message(room_id, sender, recipient, message) values (?, ?, ?, ?)",
                message.roomId, message.sender, message.recipient, message.message);
    }


    public long insert(Database db, String idColumn, String sql, Object... args) {
        ResultSetProcessor<Integer> foo = resultSet -> {
            resultSet.next();
            return resultSet.getInt(1);
        };
        return db.updateAndProcessGeneratedKeys(foo, Arrays.asList(idColumn), sql, args);
    }


}
