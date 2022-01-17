package ru.sav.calcmvp
//test
class Calc() {
    var number1: Double = 0.0
        set(value) {
            field = value
            changeCurrentInput()
        }
    var number2: Double = 0.0
    var operation: Operations = Operations.NOTHING
        set(value) {
            field = value
            changeCurrentInput()
        }
    var currentInput: String = "0"

    fun calculate(): Double = when (operation) {
        Operations.PLUS -> number1 + number2
        Operations.MINUS -> number1 - number2
        Operations.DIVISION -> number1 / number2
        Operations.MULTIPLY -> number1 * number2
        else -> 0.0
    }

    private fun getNumber1String(): String {
        val strings = number1.toString().split(".")
        if (strings[1].toDouble()==0.0) {
            return strings[0]
        } else {
            return number1.toString()
        }
    }

    fun clear(){
        number1 = 0.0
        number2 = 0.0
        operation = Operations.NOTHING
        currentInput = "0"
    }

    fun getStringOperation():String {
        return when (operation){
            Operations.PLUS -> "+"
            Operations.MINUS -> "-"
            Operations.DIVISION -> "/"
            Operations.MULTIPLY -> "*"
            else -> ""
        }
    }

    private fun changeCurrentInput(){
        currentInput=getNumber1String()+getStringOperation()
    }
}