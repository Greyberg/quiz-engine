package db

import models._
import slick.ast.BaseTypedType
import slick.jdbc.JdbcType
import slick.jdbc.{ MySQLProfile => profile }

object Tables extends Tables

trait Tables {

  import profile.api._

  implicit lazy val questionIdJdbcType: JdbcType[QuestionId] with BaseTypedType[QuestionId] =
    MappedColumnType.base[QuestionId, Long](_.id, QuestionId(_))

  implicit lazy val questionTextJdbcType: JdbcType[QuestionText] with BaseTypedType[QuestionText] =
    MappedColumnType.base[QuestionText, String](_.string, QuestionText)

  implicit lazy val answerTextJdbcType: JdbcType[AnswerText] with BaseTypedType[AnswerText] =
    MappedColumnType.base[AnswerText, String](_.string, AnswerText)

  implicit lazy val correctAnswerJdbcType: JdbcType[CorrectAnswer] with BaseTypedType[CorrectAnswer] =
    MappedColumnType.base[CorrectAnswer, Int](_.value, CorrectAnswer)

  implicit lazy val userIdJdbcType: JdbcType[UserId] with BaseTypedType[UserId] =
    MappedColumnType.base[UserId, String](_.id, UserId(_))

  implicit lazy val quizIdIdJdbcType: JdbcType[QuizId] with BaseTypedType[QuizId] =
    MappedColumnType.base[QuizId, Long](_.id, QuizId(_))

  implicit lazy val userQuestionAnswerIdJdbcType: JdbcType[UserAnswer] with BaseTypedType[UserAnswer] =
    MappedColumnType.base[UserAnswer, Int](_.value, UserAnswer(_))

  implicit lazy val scoreIdJdbcType: JdbcType[Score] with BaseTypedType[Score] =
    MappedColumnType.base[Score, Int](_.value, Score(_))

  //TODO but not yet
//  private class QuestionTable(tag: Tag) extends Table[QuestionRow](tag, "questions") {
//
//    //Primary key & auto incremented
//    def id = column[QuestionId]("id", O.PrimaryKey, O.AutoInc)
//    def text = column[QuestionText]("text")
//    def answer1 = column[AnswerText]("answer1")
//    def answer2 = column[AnswerText]("answer2")
//    def answer3 = column[AnswerText]("answer3")
//    def answer4 = column[AnswerText]("answer4")
//    //A number from one to four
//    def correctAnswer = column[CorrectAnswer]("correctAnswer")
//
//    def * = (id, text, answer1, answer2, answer3, answer4, correctAnswer) <> ((QuestionRow.apply _).tupled, QuestionRow.unapply)
//  }
//
//  lazy val questions = lifted.TableQuery[QuestionTable]
//
//  case class QuestionRow(
//                          questionId: QuestionId,
//                          questionText: QuestionText,
//                          answer1: AnswerText,
//                          answer2: AnswerText,
//                          answer3: AnswerText,
//                          answer4: AnswerText,
//                          correctAnswer: CorrectAnswer
//                        )


  class UserQuestionAnswersTable(tag: Tag) extends Table[UserQuestionAnswerRow](tag, "user_question_answers") {

    def user = column[UserId]("user_id") //Make combo primary key
    def questionId = column[QuestionId]("question_id") //Make combo primary key
    def answer: Rep[UserAnswer] = column[UserAnswer]("user_chosen_answer")

    def * = (user, questionId, answer) <> ((UserQuestionAnswerRow.apply _).tupled, UserQuestionAnswerRow.unapply)
  }

  lazy val userQuestionAnswers = TableQuery[UserQuestionAnswersTable]

  case class UserQuestionAnswerRow(
                                    user: UserId,
                                    questionId: QuestionId,
                                    answer: UserAnswer
                                  )



  class UserCompletedQuizzesTable(tag: Tag) extends Table[CompletedQuizRow](tag, "user_completed_quizzes") {

    def user = column[UserId]("user_id") //Make combo primary key
    def quizId = column[QuizId]("quiz_id") //Make combo primary key
    def score: Rep[Score] = column[Score]("score")

    def * = (user, quizId, score) <> ((CompletedQuizRow.apply _).tupled, CompletedQuizRow.unapply)
  }

  lazy val userCompletedQuizzes = TableQuery[UserCompletedQuizzesTable]

  case class CompletedQuizRow(
                                    user: UserId,
                                    quizId: QuizId,
                                    score: Score
                                  )


}
