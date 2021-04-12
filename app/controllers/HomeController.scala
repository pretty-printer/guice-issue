package controllers

import javax.inject._

import akka.actor.typed.ActorRef
import actors.{ConfiguredActor, HelloActor}
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(
  val controllerComponents: ControllerComponents,
  val helloActor: ActorRef[HelloActor.SayHello],
  val configuredActor: ActorRef[ConfiguredActor.GetConfig]
) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
