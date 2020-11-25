# quiz-engine

## Run the service

1) `docker-compose up -d postgres`
2) `docker-compose run flyway`
3) `sbt run`

## Create the db role

connect to postgres and run
`CREATE ROLE quizzes WITH LOGIN PASSWORD ‘quizzes’;`
connect to quizzes with 'psql quizzes' and run 
`GRANT ALL PRIVILEGES ON TABLE user_completed_quizzes TO quizzes;`

## Hit it

One functionally complete POST endpoint to submit a set of quiz answers /users/:userId/quiz/:quizId/answers/

A POST to

url:
`http://localhost:9000/users/bob@email.com/quiz/12/answers/`

headers:
`Content-Type: application/json`

body:
```{
       "id": {"id": 12},
       "title": {"value": "QuizTitle"},
       "userQuestionAnswers": [],
       "score": {"value": 21}
}
```