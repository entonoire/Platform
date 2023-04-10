package fr.vallfeur.plateform

import fr.vallfeur.plateform.interactions.PlatformInteraction
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.system.exitProcess

class App : Application() {

    companion object {
        lateinit var root: VBox
        var copiedObject: MutableList<Node> = ArrayList()
        var mousePosition = Point(0.0, 0.0)

    }

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(AppController::class.java.getResource("app-view.fxml"))

        val scene = Scene(fxmlLoader.load(), 720.0, 480.0)
        root = fxmlLoader.getRoot()

        stage.title = "Platform"
        stage.scene = scene
        stage.show()


        /*
            get mouse position
         */
        scene.setOnMouseMoved { event ->
            mousePosition = Point(event.sceneX, event.sceneY)

            if (PlatformInteraction.toDrop != null) {
                PlatformInteraction.moveTo(PlatformInteraction.toDrop!!, mousePosition)

            }

        }


        scene.setOnKeyPressed { event ->
            if (event.isControlDown) {

                when(event.code) {
                    KeyCode.C -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            copiedObject.addAll(AppController.selectedObject)

                        }

                    }

                    KeyCode.V -> {
                        if (copiedObject.isNotEmpty()) {
                            if (copiedObject.size == 1) {
                                AppController.addPlatformFrom(copiedObject[0], mousePosition)

                            } else {
                                copiedObject.forEach { AppController.addPlatformFrom(it, mousePosition) }

                            }

                        }

                    }

                    KeyCode.D -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            if (AppController.selectedObject.size == 1) {
                                PlatformInteraction.toDrop = AppController.addPlatformFrom(AppController.selectedObject[0], mousePosition)

                            }

                        }

                    }

                    /* --- Arrows --- */

                    KeyCode.UP -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateY -= 5 }

                        }

                    }

                    KeyCode.RIGHT -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateX += 5 }

                        }

                    }

                    KeyCode.DOWN -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateY += 5 }

                        }

                    }

                    KeyCode.LEFT -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateX -= 5 }

                        }

                    }

                    /* -------------- */

                    else -> {}
                }

            } else {
                when(event.code) {
                    KeyCode.DELETE -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            val obj = AppController.selectedObject

                            obj.forEach { AppController.pane.children.remove(it) }

                            try {
                                PlatformInteraction.select(PlatformInteraction.platforms[PlatformInteraction.platforms.indexOf(obj[0]) - 1])

                            } catch (_: IndexOutOfBoundsException) {
                                try {
                                    PlatformInteraction.select(PlatformInteraction.platforms[PlatformInteraction.platforms.indexOf(obj[0]) + 1])

                                } catch (_: IndexOutOfBoundsException) {}

                            }

                            PlatformInteraction.platforms.removeAll(obj)

                        }
                    }

                    KeyCode.TAB -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            if (AppController.selectedObject.size == 1) {
                                val first = AppController.selectedObject[0]

                                AppController.selectedObject.clear()
                                AppController.selectedObject.add(first)

                            }

                            try {
                                PlatformInteraction.select(PlatformInteraction.platforms[PlatformInteraction.platforms.indexOf(AppController.selectedObject[0]) + 1])

                            } catch (_: IndexOutOfBoundsException) {
                                PlatformInteraction.select(PlatformInteraction.platforms[0])

                            }

                        }

                    }

                    KeyCode.ESCAPE -> if (AppController.selectedObject.isNotEmpty()) PlatformInteraction.deSelectAll(AppController.selectedObject)

                    /* --- Arrows --- */

                    KeyCode.UP -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateY -= 1 }

                        }

                    }

                    KeyCode.RIGHT -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateX += 1 }

                        }

                    }

                    KeyCode.DOWN -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateY += 1 }

                        }

                    }

                    KeyCode.LEFT -> {
                        if (AppController.selectedObject.isNotEmpty()) {
                            AppController.selectedObject.forEach { it.translateX -=  1 }

                        }

                    }

                    /* -------------- */

                    else -> {}

                }

            }

        }


        println("[Platform] Started")
    }

    override fun stop() {
        exitProcess(0)

    }

}

fun main() {
    Application.launch(App::class.java)

}
