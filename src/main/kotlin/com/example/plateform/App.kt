package com.example.plateform

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
        var copiedObject: Node? = null
        var mousePosition = com.example.plateform.Point(0.0, 0.0)

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
            mousePosition = com.example.plateform.Point(event.sceneX, event.sceneY)

        }


        scene.setOnKeyPressed { event ->
            if (event.isControlDown) {
                if (event.code == KeyCode.C) {
                    if (AppController.selectedObject != null) {
                        copiedObject = AppController.selectedObject

                    }

                }

                if (event.code == KeyCode.V) {
                    if (copiedObject != null) {
                        AppController.addPlatformFrom(copiedObject!!, mousePosition)

                    }

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
