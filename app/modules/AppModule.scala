package modules

import actors.HelloActor
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

object AppModule extends AbstractModule with AkkaGuiceSupport {
  override def configure() = {
    bindTypedActor(HelloActor.create(), "hello-actor")  // uses "create" method

  }
}