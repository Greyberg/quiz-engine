# quiz-engine

Many things to do

One functionally complete POST endpoint to submit a set of quiz answers /users/:userId/quiz/:quizId/answers/

Many attemtps made at dockerizing but decided to remove

SQL to run against a db can be found under db/setup.sql

The service can be run with `sbt run` starting on port 9000

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
Will result in a failure to connect to a db. I think this is the only issue.