CREATE TABLE User (
  username varchar(200) PRIMARY KEY,
  fullname varchar(200)
);

CREATE TABLE Room (
  room_id integer auto_increment PRIMARY KEY,
  name varchar(200)
);

CREATE TABLE Participant (
  participant_id integer auto_increment PRIMARY KEY,
  room_id integer NOT NULL,
  name varchar(200),
  username varchar(200),
  FOREIGN KEY(Room_id) REFERENCES Room(room_id),
  FOREIGN KEY(username) REFERENCES User(username)
);

CREATE TABLE Message (
  message_id integer auto_increment PRIMARY KEY,
  room_id integer NOT NULL,
  sender integer NOT NULL,
  recipient integer NOT NULL,
  message TEXT NOT NULL,
  FOREIGN KEY(Room_id) REFERENCES Room(room_id),
  FOREIGN KEY(sender) REFERENCES Participant(participant_id),
  FOREIGN KEY(recipient) REFERENCES Participant(participant_id)
);