package com.example.plateform.interactions

import com.example.plateform.AppController
import javafx.scene.Node
import javafx.scene.input.MouseButton

class BackgroundInteraction(node: Node) {

    init {
        /*
            create platform
         */
        node.setOnMouseClicked { event ->
            if (event.button == MouseButton.PRIMARY) {
                AppController.addPlatform(event.sceneX, event.sceneY)

            }

        }

    }

}