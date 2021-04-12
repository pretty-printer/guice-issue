package actors

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.google.inject.Provides
import play.api.Configuration
import play.api.libs.concurrent.ActorModule

object ConfiguredActor extends ActorModule {
  type Message = GetConfig

  final case class GetConfig(replyTo: ActorRef[String])

  @Provides
  def create(configuration: Configuration): Behavior[GetConfig] = {
    Behaviors.setup { _ =>
      val config = configuration.get[String]("my.config")
      Behaviors.receiveMessage[GetConfig] {
        case GetConfig(replyTo) =>
          replyTo ! config
          Behaviors.same
      }
    }
  }
}