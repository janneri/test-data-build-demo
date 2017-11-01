package fi.solita.demo;

import fi.solita.demo.dto.User;
import org.dalesbred.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private Database db;

    public void mergeUser(User user) {
        db.update("merge into user key(username) values (?, ?)", user.username, user.fullname);
    }

}
