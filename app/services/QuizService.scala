package services

import db.QuizDao
import db.Tables.UserQuestionAnswerRow
import javax.inject.Inject
import models.{Quiz, QuizId, Score, UserId, UserQuizAnswers}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class QuizService @Inject()(quizDao: QuizDao) {

  def fetchMyQuizScore(userId: UserId, quizId: QuizId): Future[Option[Int]] = {
    quizDao.fetchUserQuizScore(userId, quizId).map(_.map(_.value))
  }


  def fetchMyQuizAnswers(userId: String): Future[List[UserQuizAnswers]] = {
    //TODO
    //call the dao to fetch the quizzes that have been completed by hte user from the completed quizzes table
    //cal the dao to fetch the answers that the user gave for each quiz

    Future.successful(List.empty)
  }


  def fetchQuizzes(userId: String): Future[List[Quiz]] = {
    //TODO
    //call the dao to fetch the quiz ids that a user has already completed from completed quizzes table
    //call the dao to fetch all the quizzes that have been defined in the quizzes table, filtering out (in the dao) the ones already completed

    //placeholder
    Future.successful(List.empty)
  }

  def insertUserQuizAnswers(userId: String, body: UserQuizAnswers): Future[Unit] = {

    val rows = body.userQuestionAnswers.map(answer =>
      UserQuestionAnswerRow(UserId(userId), answer.questionId, answer.userAnswer)
    )
    for {
      _ <- quizDao.insertCompletedQuiz(userId, body.id, body.score)
       _ <- quizDao.insertUserQuizAnswers(rows)
    } yield ()

  }

}
