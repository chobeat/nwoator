package controllers

import play.api._
import play.api.mvc._
import securesocial.core._
import service.WithProvider
import service.NWOUser

object Application extends Controller with securesocial.core.SecureSocial {

  def index = SecuredAction(WithProvider("oauth2")) {
    implicit request=> request.user match{
      case u:NWOUser=>Ok(views.html.index(""+u.serial))
    case _=>Ok(views.html.index("fail	"))
  }}
  
  def showCard = SecuredAction(WithProvider("oauth2")) {
    implicit request=> request.user match{
      case u:NWOUser=>Ok(views.html.showCard("",u.firstName))
    case _=>Ok(views.html.index("fail	"))
  }}
  

}