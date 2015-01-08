package controllers

import play.api._
import play.api.mvc._
import securesocial.core._
import service.WithProvider
import service.NWOUser
import imageEditing.CardGenerator

object Application extends Controller with securesocial.core.SecureSocial {

  def index = Action{
    implicit request=>
      Ok(views.html.index(""))
    }
  def loadCard = Action { implicit request =>
  Ok("Ajax Call!")
}
  def editData = SecuredAction(){
    implicit request=>{
      Ok(views.html.editdata(request.user.asInstanceOf[NWOUser]))
    }
  }
  
  def showCard = SecuredAction() {
    implicit request=> request.user match{
      case u:NWOUser=>Ok(views.html.showCard(CardGenerator.makeCard(u),u.firstName))
    case _=>Ok(views.html.index("fail	"))
  }}
  

}