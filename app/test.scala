package imageEditing
import java.awt.{ Color, Font }
import java.awt.geom._
import javax.imageio.ImageIO
import java.io.File
import java.awt.GraphicsEnvironment
import play.Play
import service.NWOUser

case class Source(text: String, x: Int, y: Int, l: Int, font: String = "Courier")

object CardGenerator {
  def makeCard(user: NWOUser) = {
    val basepath=Play.application().configuration().getString("cardDir");
    val canvas = ImageIO.read(new File(basepath + "card.png"));
    // get Graphics2D for the image
    val g = canvas.createGraphics()
    val e = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()

    val sources = List(Source(user.firstName, 91, 152, 150), Source(user.lastName, 91, 172, 150), Source(user.work, 91, 192, 150), Source("location", 91, 211, 150),
      Source(""+user.serial, 91, 230, 150), Source(user.firstName+" "+user.lastName, 265, 230, 125, "Comic Sans"))
    val defaultFont = "Courier"
    // enable anti-aliased rendering (prettier lines and circles)
    // Comment it out to see what this does!
    g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
      java.awt.RenderingHints.VALUE_ANTIALIAS_ON)

    // draw two filled circles

    g.setColor(new Color(255, 255, 255)) // a darker green
    g.setFont(new Font("Courier", Font.PLAIN, 12))
    val defaultFontSize = 20
    for (v <- sources) {

      g.setFont(new Font(v.font, Font.PLAIN, defaultFontSize))
      def FM = g.getFontMetrics()
      def f = (v.l / FM.stringWidth(v.text).toFloat * defaultFontSize).toInt
      g.setFont(new Font(v.font, Font.PLAIN, math.min(f, defaultFontSize)))
      println(g.getFont())
      g.drawString(v.text, v.x, v.y)
    }
    // done with drawing
    g.dispose()

    // write image to a file
    javax.imageio.ImageIO.write(canvas, "png", new java.io.File(basepath + "prova.png"))
  }
}