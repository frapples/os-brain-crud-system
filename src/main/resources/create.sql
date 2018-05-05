CREATE TABLE `user`(
id INTEGER PRIMARY KEY,
name CHAR(8) NOT NULL,
student_id CHAR(20) NOT NULL UNIQUE,
class_id INTEGER NOT NULL,
email VARCHAR(30),
phone VARCHAR(20),
FOREIGN KEY(class_id) REFERENCES school_class(id)
);

CREATE TABLE school_class (
id INTEGER PRIMARY KEY,
name VARCHAR(30) NOT NULL,
start_year INTEGER NOT NULL
);

CREATE TABLE user_login_log(
id INTEGER PRIMARY KEY,
user_id INTEGER NOT NULL,
login_time TIMESTAMP NOT NULL,
gps_point TINYTEXT NOT NULL,
FOREIGN KEY(user_id) REFERENCES user(student_id)
);

CREATE TABLE `admin` (
id INTEGER PRIMARY KEY,
name CHAR(8) NOT NULL,
email VARCHAR(30) NOT NULL,
password_hash CHAR(64) NOT NULL,
phone VARCHAR(20)
);

CREATE TABLE exercise_book (
id INTEGER PRIMARY KEY,
name TINYTEXT NOT NULL
);

CREATE TABLE exercise_book_question_relation (
id INTEGER PRIMARY KEY,
question_id INTEGER NOT NULL,
exercise_book_id INTEGER NOT NULL,
order_key INTEGER NOT NULL,
FOREIGN KEY(question_id) REFERENCES question(id),
FOREIGN KEY(exercise_book_id) REFERENCES exercise_book(id),
UNIQUE(question_id, exercise_book_id)
);

CREATE TABLE question (
id INTEGER PRIMARY KEY,
content TEXT NOT NULL,
answer_content TEXT NOT NULL,
type CHAR(10) NOT NULL
);


CREATE TABLE exercise_record (
id INTEGER PRIMARY KEY,
student_id INTEGER NOT NULL,
start_time TIMESTAMP NOT NULL,
end_time TIMESTAMP,
FOREIGN KEY(student_id) REFERENCES user(student_id)
);

CREATE TABLE exercise_record_reply (
id INTEGER PRIMARY KEY,
exercise_record_id INTEGER NOT NULL,
question_id INTEGER NOT NULL,
answer_content TEXT NOT NULL,
created_time TIMESTAMP,
FOREIGN KEY(exercise_record_id) REFERENCES exercise_record(id),
FOREIGN KEY(exercise_record_id) REFERENCES exercise_record(id),
UNIQUE(exercise_record_id, question_id)
);
