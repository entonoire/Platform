package fr.vallfeur.plateform.interactions

import fr.vallfeur.plateform.AppController
import javafx.scene.Node
import javafx.scene.input.MouseButton

class BackgroundInteraction(node: Node) {

    init {
        /*
            create platform
         */
        node.setOnMouseClicked { event ->
            if (event.button == MouseButton.PRIMARY) {
                PlatformInteraction.select(AppController.addPlatform(event.sceneX, event.sceneY))

            }

        }

    }

}