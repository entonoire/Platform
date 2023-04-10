package fr.vallfeur.plateform

/*
    make the same class that Point but with Double
 */
class Point(x: Double, y: Double) {

    var x: Double
    var y: Double
    var data: String

    init {
        this.x = x
        this.y = y
        this.data = "$x     -     $y"
    }

}