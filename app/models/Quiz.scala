package models

import play.api.libs.json.{Json, OFormat}

case class UserQuizAnswers(id: QuizId, title: QuizTitle, userQuestionAnswers: List[UserQuestionAnswer], score: Score)

object UserQuizAnswers {
  implicit val format: OFormat[UserQuizAnswers] = Json.format[UserQuizAnswers]
}

case class QuizId(id: Long)

object QuizId {
  implicit val format: OFormat[QuizId] = Json.format[QuizId]
}

case class QuizTitle(value: String)

object QuizTitle {
  implicit val format: OFormat[QuizTitle] = Json.format[QuizTitle]
}

case class UserQuestionAnswer(questionId: QuestionId, userAnswer: UserAnswer)

object UserQuestionAnswer {
  implicit val format: OFormat[UserQuestionAnswer] = Json.format[UserQuestionAnswer]
}

//So this is a user choosing eg. answer 4 out of the questions one to 4
case class UserAnswer(value: Int)

object UserAnswer {
  implicit val format: OFormat[UserAnswer] = Json.format[UserAnswer]
}

case class QuestionId(id: Long)

object QuestionId {
  implicit val format: OFormat[QuestionId] = Json.format[QuestionId]
}

case class AnswerId(id: Int)

object AnswerId {
  implicit val format: OFormat[AnswerId] = Json.format[AnswerId]
}

case class Question(questionId: QuestionId, questionText: QuestionText, answer1: Answer, answer2: Answer, answer3: Answer, answer4: Answer)

case class QuestionText(string: String)

case class Answer(answerId: AnswerId, answerText: AnswerText, isCorrect: Boolean)

case class AnswerText(string: String)

case class CorrectAnswer(value: Int)

case class UserId(id: String) //TODO - change this to be an int. Placeholder for unique internal id but use email address for now

case class Score(value: Int)

object Score {
  implicit val format: OFormat[Score] = Json.format[Score]
}

//TODO - placeholder
case class Quiz(placeholder: String)

object Quiz {
  implicit val format: OFormat[Quiz] = Json.format[Quiz]
}
