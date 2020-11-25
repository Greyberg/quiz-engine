CREATE TABLE user_question_answers (
  user_id VARCHAR NOT NULL,
  question_id BIGINT NOT NULL,
  user_chosen_answer INT NOT NULL
);

CREATE TABLE user_completed_quizzes (
  user_id VARCHAR NOT NULL,
  quiz_id INT NOT NULL,
  score INT NOT NULL
);