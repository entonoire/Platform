package com.example.plateform

import com.example.plateform.interactions.BackgroundInteraction
import com.example.plateform.interactions.PlatformInteraction
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
        var selectedObject: Node? = null
        lateinit var pane: StackPane

        fun addPlatform(x: Double, y: Double) {
            val platform = ImageView(Image("https://i.pinimg.com/originals/b8/46/99/b846991fec1f185eb40b90aba0aa72ac.jpg"))
            platform.fitWidth = 85.0
            platform.fitHeight = 85.0
            platform.translateX = x - 359.5
            platform.translateY = y - 242.5

            PlatformInteraction(platform)

            pane.children.add(platform)

        }

    }

}

