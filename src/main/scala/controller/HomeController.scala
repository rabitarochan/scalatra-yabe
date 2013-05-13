package controller

import org.scalatra._
import javax.servlet.http.HttpServletRequest
import collection.mutable

class HomeController extends ScalatraServlet {

  get("/") {
    "Hello scalatra-yabe!"
  }

}
