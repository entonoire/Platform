package com.example.plateform.interactions

import com.example.plateform.AppController
import javafx.scene.Node
import javafx.scene.effect.Glow
import javafx.scene.input.MouseButton

class PlatformInteraction(node: Node) {

    // add last size thing, to have the same size (if alt pressed on create) than the last edited platform when you create another platform

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

        node.setOnMouseClicked { event ->
            /*
                remove platform
             */
            if (event.button == MouseButton.SECONDARY) {
                AppController.pane.children.remove(node)

            }

            /*
                select platform
             */
            if (event.button == MouseButton.PRIMARY) {
                if (AppController.selectedObject == node) {
                    // remove effect
                    node.effect = null
                    AppController.selectedObject = null

                } else {
                    // add effect
                    node.effect = Glow(0.5)

                    if (AppController.selectedObject != null) AppController.selectedObject!!.effect = null
                    AppController.selectedObject = node

                }

            }

        }

        /*
            set platform size
         */
        node.setOnScroll { event ->
            if (event.isControlDown) {
                if (event.deltaY > 0.0) {
                    node.scaleX += 0.6
                    node.scaleY += 0.6

                } else if (node.scaleX > 1) {
                    node.scaleX -= 0.6
                    node.scaleY -= 0.6

                }

            } else {
                if (event.deltaY > 0.0) {
                    node.scaleX += 0.1
                    node.scaleY += 0.1

                } else if (node.scaleX > 0.3) {
                    node.scaleX -= 0.1
                    node.scaleY -= 0.1

                }

            }

        }

    }



}