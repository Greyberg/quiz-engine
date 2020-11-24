package db


import db.Tables._
import javax.inject.Inject
import models._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class QuizDao @Inject() (
                          val dbConfigProvider: DatabaseConfigProvider
                        )(
  implicit ec: ExecutionContext
) extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  def insertUserQuizAnswers(rows: Seq[UserQuestionAnswerRow]): Future[Unit] = {

    val action = Tables.userQuestionAnswers ++= rows

    db.run(action).map(_ => ()) //TODO - catch failures or repeats and return None
  }

  def insertCompletedQuiz(userId: String, quizId: QuizId, score: Score): Future[Unit] = {

    val row = CompletedQuizRow(UserId(userId), quizId, score)

    val action = Tables.userCompletedQuizzes += row

    db.run(action).map(_ => ()) //TODO catch failures or repeats
  }


  def fetchUserQuizScore(userId: UserId, quizId: QuizId): Future[Option[Score]] = {

    val query = Tables.userCompletedQuizzes.filter(_.user === userId).map(_.score)

    db.run(query.result.headOption)
  }
}
