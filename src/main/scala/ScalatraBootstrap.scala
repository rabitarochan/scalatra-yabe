import org.scalatra._
import javax.servlet.ServletContext

import controller._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    context.mount(new HomeController, "/*")
  }
}