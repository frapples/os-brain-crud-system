CREATE TABLE `user`(
id INT PRIMARY KEY,
name CHAR(8) NOT NULL,
student_id CHAR(20) NOT NULL UNIQUE,
class_id INT NOT NULL,
email VARCHAR(30),
phone VARCHAR(20),
FOREIGN KEY(class_id) REFERENCES school_class(id)
);

CREATE TABLE school_class (
id INT PRIMARY KEY,
name VARCHAR(30) NOT NULL,
start_year INT NOT NULL
);

CREATE TABLE user_login_log(
id INT PRIMARY KEY,
user_id INT NOT NULL,
login_time TIMESTAMP NOT NULL,
gps_point TINYTEXT NOT NULL,
FOREIGN KEY(user_id) REFERENCES user(student_id)
);

CREATE TABLE `admin` (
id INT PRIMARY KEY,
name CHAR(8) NOT NULL,
email VARCHAR(30) NOT NULL,
password_hash CHAR(64) NOT NULL,
phone VARCHAR(20)
);

CREATE TABLE exercise_book (
id INT PRIMARY KEY,
name TINYTEXT NOT NULL
);

CREATE TABLE exercise_book_question_relation (
id INT PRIMARY KEY,
question_id INT NOT NULL,
exercise_book_id INT NOT NULL,
order_key INT NOT NULL,
FOREIGN KEY(question_id) REFERENCES question(id),
FOREIGN KEY(exercise_book_id) REFERENCES exercise_book(id),
UNIQUE(question_id, exercise_book_id)
);

CREATE TABLE question (
id INT PRIMARY KEY,
content TEXT NOT NULL,
answer_content TEXT NOT NULL,
type CHAR(10) NOT NULL
);


CREATE TABLE exercise_record (
id INT PRIMARY KEY,
student_id INT NOT NULL,
start_time TIMESTAMP NOT NULL,
end_time TIMESTAMP,
FOREIGN KEY(student_id) REFERENCES user(student_id)
);

CREATE TABLE exercise_record_reply (
id INT PRIMARY KEY,
exercise_record_id INT NOT NULL,
question_id INT NOT NULL,
answer_content TEXT NOT NULL,
created_time TIMESTAMP,
FOREIGN KEY(exercise_record_id) REFERENCES exercise_record(id),
FOREIGN KEY(exercise_record_id) REFERENCES exercise_record(id),
UNIQUE(exercise_record_id, question_id)
);
