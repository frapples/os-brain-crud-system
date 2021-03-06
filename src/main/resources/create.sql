CREATE TABLE school_class (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL,
start_year INTEGER NOT NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL
);

CREATE TABLE category (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(30) NOT NULL
);


CREATE TABLE `user`(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name CHAR(8) NOT NULL,
student_id CHAR(20) NOT NULL UNIQUE,
class_id INTEGER NOT NULL,
email VARCHAR(30),
phone VARCHAR(20),
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL,
FOREIGN KEY(class_id) REFERENCES school_class(id)
);


CREATE TABLE user_login_log(
id INTEGER PRIMARY KEY AUTO_INCREMENT,
user_id INTEGER NOT NULL,
login_time TIMESTAMP NOT NULL,
gps_point TINYTEXT NOT NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL,
FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE `admin` (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name CHAR(8) NOT NULL,
email VARCHAR(30) NOT NULL,
password_hash CHAR(64) NOT NULL,
phone VARCHAR(20),
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL
);

CREATE TABLE exercise_book (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name TINYTEXT NOT NULL,
score DECIMAL(5, 1) NOT NULL,
comment TINYTEXT NOT NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL
);

CREATE TABLE question (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
content TEXT NOT NULL,
answer_content TEXT NOT NULL,
choise_option TEXT,
type CHAR(15) NOT NULL,
score DECIMAL(5, 1) NOT NULL,
category_id INTEGER NOT NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL
);

CREATE TABLE exercise_book_question_relation (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
question_id INTEGER NOT NULL,
exercise_book_id INTEGER NOT NULL,
order_key INTEGER NOT NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL,
FOREIGN KEY(question_id) REFERENCES question(id),
FOREIGN KEY(exercise_book_id) REFERENCES exercise_book(id),
UNIQUE(question_id, exercise_book_id)
);

CREATE TABLE task (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
exercise_book_id INTEGER NOT NULL,
start_time TIMESTAMP NULL,
end_time TIMESTAMP NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL,
FOREIGN KEY(exercise_book_id) REFERENCES exercise_book(id)
);

CREATE TABLE exercise_record (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
user_id INTEGER NOT NULL,
start_time TIMESTAMP NULL,
end_time TIMESTAMP NULL,
task_id INTEGER NOT NULL,
score DECIMAL(5, 1) NULL,
ip VARCHAR(32) NOT NULL,
gps VARCHAR(32) NOT NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL,
FOREIGN KEY(user_id) REFERENCES user(id),
FOREIGN KEY(task_id) REFERENCES task(id)
);

CREATE TABLE exercise_record_reply (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
exercise_record_id INTEGER NOT NULL,
question_id INTEGER NOT NULL,
answer_content TEXT NOT NULL,
score_percent FLOAT NULL,
created_time TIMESTAMP NULL,
updated_time TIMESTAMP NULL,
FOREIGN KEY(exercise_record_id) REFERENCES exercise_record(id),
FOREIGN KEY(question_id) REFERENCES question(id),
UNIQUE(exercise_record_id, question_id)
);
