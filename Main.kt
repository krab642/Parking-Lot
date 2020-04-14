
package parking
import java.util.*


fun main(args: Array<String>) {

    var newParking1 = createParking(0)
    //val scanner = Scanner(System.`in`)
    while (true) {
        val scanner = Scanner(System.`in`)
        val a = scanner.next()
        when {
            a == "park" && newParking1.carColors.isNotEmpty() -> {
                park(newParking1, scanner.next(), scanner.next())
            }
            a == "leave" && newParking1.carColors.isNotEmpty() -> {
                leave(scanner.nextInt(), newParking1)
            }
            a == "exit" -> return
            a == "status" && newParking1.carColors.isNotEmpty() -> {
                status(newParking1)
            }
            a == "create" -> {
                newParking1 = createParking(scanner.nextInt())
                println("Created a parking lot with ${newParking1.freePlaces.size} spots.")
            }
            a == "reg_by_color" && newParking1.carColors.isNotEmpty() -> {
                newParking1.reg_by_color(scanner.next())
            }
            a == "spot_by_color" && newParking1.carColors.isNotEmpty() -> {
                newParking1.spot_by_color(scanner.next())
            }
            a == "spot_by_reg" && newParking1.carColors.isNotEmpty() -> {
                newParking1.spot_by_reg(scanner.next())
            }
            else -> {
                println("Sorry, parking lot is not created.")
            }
        }
    }
}


data class parking(var n: Int = 0) {
    val freePlaces = BooleanArray(n) {true}
    val carNumbers: Array<String> = Array(n) {"empty"}
    val carColors = Array(n) {"empty"}



    fun reg_by_color(color: String) {
        var NumberString: String = ""
        if (carColors.filter { it.toLowerCase() == color.toLowerCase() }.size == 0) {
            println("No cars with color $color were found.")
        } else {
            for (i in carColors.indices) {
                if (carColors[i].toLowerCase() == color.toLowerCase()) {
                    NumberString += carNumbers[i].toString() + ", "
                }
            }
            println(NumberString.substring(0, NumberString.length - 2))
        }
    }

    fun spot_by_color(color: String) {
        var NumberString: String = ""
        if (carColors.filter { it.toLowerCase() == color.toLowerCase() }.size == 0) {
            println("No cars with color $color were found.")
        } else {
            for (i in carColors.indices) {
                if (carColors[i].toLowerCase() == color.toLowerCase()) {
                    NumberString += (i+1).toString() + ", "
                }
            }
            println(NumberString.substring(0, NumberString.length - 2))
        }
    }

    fun spot_by_reg(number: String) {
        var RegByNumberString = ""
        if (carNumbers.filter { it.toLowerCase() == number.toLowerCase() }.size == 0) {
            println("No cars with registration number $number were found.")
        } else {
            for (i in carNumbers.indices) {
                if (carNumbers[i].toLowerCase() == number.toLowerCase()) {
                    RegByNumberString += (i+1).toString() + ", "
                }
            }
            println(RegByNumberString.substring(0, RegByNumberString.length - 2))
        }
    }
}

fun createParking(n:Int): parking {
    val newParking = parking(n)
    return newParking
}

fun leave(i: Int, newParking: parking) {
    newParking.carColors[i - 1] = "empty"
    newParking.carNumbers[i - 1] = "empty"
    newParking.freePlaces[i - 1] = true
    println("Spot $i is free.")
}

fun park(newParking: parking, carNumber: String, carColor: String) {
    for (i in newParking.freePlaces.indices) {
        if (newParking.freePlaces[i] == true) {
            newParking.carNumbers[i] = carNumber
            newParking.carColors[i] = carColor
            newParking.freePlaces[i] = false
            println("${newParking.carColors[i]} car parked on the spot ${i+1}.")
            break
        } else if (!newParking.freePlaces.contains(true)) {
            println("Sorry, parking lot is full.")
            break
        }
    }
}

fun status(newParking: parking) {
    if (newParking.freePlaces.filter { i -> i == false }.size == 0) {
        println("Parking lot is empty.")
    } else {
        for (i in newParking.freePlaces.indices) {
            if (!newParking.freePlaces[i]) {
                println("${i + 1} ${newParking.carNumbers[i]} ${newParking.carColors[i]}")
            }
        }
    }


}







