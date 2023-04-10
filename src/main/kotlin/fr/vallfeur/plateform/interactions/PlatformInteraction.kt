package fr.vallfeur.plateform.interactions

import fr.vallfeur.plateform.App
import fr.vallfeur.plateform.AppController
import fr.vallfeur.plateform.Point
import javafx.scene.Node
import javafx.scene.effect.Glow
import javafx.scene.input.MouseButton

class PlatformInteraction(node: Node) {

    companion object {
        val platforms: MutableList<Node> = ArrayList()
        var toDrop: Node? = null

        fun select(platform: Node) {
            if (AppController.selectedObject.isNotEmpty()) {
                AppController.selectedObject.forEach { it.effect = null }

            }

            platform.effect = Glow(0.5)

            AppController.selectedObject.clear()
            AppController.selectedObject.add(platform)

        }

        fun selectAll(platforms: MutableList<Node>) { platforms.forEach { it.effect = Glow(0.5) } }

        fun deSelectAll(platforms: MutableList<Node>) {
            platforms.forEach { it.effect = null }
            AppController.selectedObject.removeAll(platforms)

        }

        fun deselect(platform: Node) {
            platform.effect = null
            AppController.selectedObject.remove(platform)

        }

        fun moveTo(platform: Node, position: Point) {
            platform.translateX = position.x - 359.5
            platform.translateY = position.y - 242.5

        }

    }

    /*
        register a new platform
     */
    init {
        /*
            move platform
         */
        node.setOnMouseDragged { event ->
            if (event.button == MouseButton.PRIMARY) {
                moveTo(node, Point(event.sceneX, event.sceneY))

            }

        }

        node.setOnMouseClicked { event ->
            /*
                select platform
             */
            if (event.button == MouseButton.PRIMARY) {
                if (event.isShiftDown) {
                    if (AppController.selectedObject.contains(node)) {
                        AppController.selectedObject.remove(node)
                        deselect(node)

                    } else {
                        AppController.selectedObject.add(node)
                        selectAll(AppController.selectedObject)

                    }


                } else {
                    if (AppController.selectedObject.contains(node)) {
                        if (toDrop != null) toDrop = null
                        else deSelectAll(AppController.selectedObject)

                    } else {
                        select(node)

                    }

                }

            } else if (toDrop != null) {
                select(toDrop!!)
                toDrop = null

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