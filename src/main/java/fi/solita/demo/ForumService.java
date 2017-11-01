package fi.solita.demo;

import fi.solita.demo.command.AddParticipant;
import fi.solita.demo.command.SendMessage;
import fi.solita.demo.dto.Message;
import fi.solita.demo.dto.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ForumService {

    private static final Logger LOG = LoggerFactory.getLogger(ForumService.class);

    @Autowired
    public ForumDao forumDao;


    @Transactional
    public List<Message> getMessages(long roomId) {
        return forumDao.getMessages(roomId);
    }

    @Transactional
    public long sendMessage(SendMessage message) {
        LOG.info("Participant {} is sending a message in room {}", message.sender, message.roomId);
        return forumDao.insertMessage(message);
    }

    @Transactional
    public long joinRoomWithName(AddParticipant addParticipant) {
        LOG.info("Participant {} is joining room {} with name {}",
                addParticipant.username, addParticipant.roomId, addParticipant.name);
        return forumDao.insertParticipant(addParticipant);
    }

    @Transactional
    public List<Participant> getParticipants(long roomId) {
        return forumDao.getParticipants(roomId);
    }

    @Transactional
    public List<Message> findDangerousMessages() {
        return forumDao.findDangerousMessages();
    }

}
