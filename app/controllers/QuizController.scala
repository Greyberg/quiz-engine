package controllers

import javax.inject.Inject
import models._
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, InjectedController}
import services.{AuthService, QuizService}

import scala.concurrent.ExecutionContext

class QuizController @Inject()(quizService: QuizService,
                               authService: AuthService
                              )(implicit ec: ExecutionContext)
  extends InjectedController {

  def addQuizAnswers(userId: String, quizId: Long): Action[UserQuizAnswers] = Action(parse.json[UserQuizAnswers]).async { request =>
    quizService.insertUserQuizAnswers(userId, request.body).map(_ => Created(Json.toJson("CREATED")))
  }

  def fetchQuizzes(userId: String): Action[AnyContent] = Action.async {
    quizService.fetchQuizzes(userId).map {
      case Nil => NotFound
      case quizzes => Ok(Json.toJson(quizzes))
    }
  }

  def fetchMyQuizAnswers(userId: String): Action[AnyContent] = Action.async {
    quizService.fetchMyQuizAnswers(userId).map {
      case Nil => NotFound
      case quizAnswers => Ok(Json.toJson(quizAnswers))
    }
  }

  def fetchMyQuizScore(userId: String, quizId: Long): Action[AnyContent] = Action.async {
    quizService.fetchMyQuizScore(UserId(userId), QuizId(quizId)).map(score  => Ok(Json.toJson(score: Option[Int])))
  }

}
