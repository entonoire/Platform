package fr.vallfeur.plateform

import fr.vallfeur.plateform.interactions.BackgroundInteraction
import fr.vallfeur.plateform.interactions.PlatformInteraction
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Node
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import java.net.URL
import java.util.*

class AppController : Initializable {

    @FXML
    private lateinit var background: ImageView
    @FXML
    private lateinit var stackPane: StackPane

    @FXML
    override fun initialize(location: URL?, resources: ResourceBundle?) {
        println("[Platform] Initialized")

        background.fitWidth = 850.0
        background.fitHeight = 850.0
        BackgroundInteraction(background)

        pane = stackPane

    }

    companion object {
        var selectedObject: MutableList<Node> = ArrayList()
        lateinit var pane: StackPane

        fun addPlatform(x: Double, y: Double): Node {
            val platform = ImageView(Image("https://i.pinimg.com/originals/b8/46/99/b846991fec1f185eb40b90aba0aa72ac.jpg"))
            platform.fitWidth = 85.0
            platform.fitHeight = 85.0
            PlatformInteraction.moveTo(platform, Point(x, y))

            PlatformInteraction(platform)

            pane.children.add(platform)
            PlatformInteraction.platforms.add(platform)

            return platform
        }

        fun addPlatformFrom(node: Node, loc: Point): Node? {
            if (node is ImageView) {
                val platform = ImageView(Image("https://i.pinimg.com/originals/b8/46/99/b846991fec1f185eb40b90aba0aa72ac.jpg"))
                platform.fitWidth = 85.0 * node.scaleX
                platform.fitHeight = 85.0 * node.scaleY
                PlatformInteraction.moveTo(platform, Point(loc.x, loc.y))

                PlatformInteraction(platform)
                pane.children.add(platform)
                PlatformInteraction.platforms.add(platform)

                PlatformInteraction.select(platform)

                return platform
            } else {
                println("[Platform] addPlatformFrom, the requested node is not an ImageView")

                return null
            }

        }

    }

}

