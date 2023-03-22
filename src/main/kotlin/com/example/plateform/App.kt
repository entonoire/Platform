package com.example.plateform

import com.example.plateform.interactions.PlatformInteraction
import javafx.application.Application
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlin.system.exitProcess

class App : Application() {

    companion object {
        lateinit var root: VBox
    }

    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(AppController::class.java.getResource("app-view.fxml"))

        val scene = Scene(fxmlLoader.load(), 720.0, 480.0)
        root = fxmlLoader.getRoot()

        stage.title = "Platform"
        stage.scene = scene
        stage.show()

        println("[Platform] Started")
    }

    override fun stop() {
        exitProcess(0)

    }

}

fun main() {
    Application.launch(App::class.java)

}
