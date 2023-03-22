package com.example.plateform.interactions

import com.example.plateform.AppController
import javafx.scene.Node
import javafx.scene.effect.Effect
import javafx.scene.effect.Glow
import javafx.scene.input.MouseButton
import kotlin.random.Random

class PlatformInteraction(node: Node) {

    /*
        register a new platform
     */
    init {
        /*
            move platform
         */
        node.setOnMouseDragged { event ->
            if (event.button == MouseButton.PRIMARY) {
                node.translateX = event.sceneX - 359.5
                node.translateY = event.sceneY - 242.5

            }

        }

        /*
            remove platform
         */
        node.setOnMouseClicked { event ->
            if (event.button == MouseButton.SECONDARY) {
                AppController.pane.children.remove(node)

            }

        }

    }



}