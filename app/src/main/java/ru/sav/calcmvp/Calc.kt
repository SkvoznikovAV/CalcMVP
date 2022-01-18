package ru.sav.calcmvp

class Calc() {
    var currentInput: String = "0"
    var operation: Operations = Operations.NOTHING
        set(value) {
            var number1String = currentInput
            if (operation !== Operations.NOTHING) {
                val lastChar=currentInput.last()
                if ((lastChar=='+')||(lastChar=='-')||(lastChar=='*')||(lastChar=='/')) {
                    number1String = currentInput.substring(0, currentInput.length - 1)
                }
            }

            field = value
            currentInput = number1String+getStringOperation()
        }

    private fun calculate(number1: Double, number2: Double): Double = when (operation) {
        Operations.PLUS -> number1 + number2
        Operations.MINUS -> number1 - number2
        Operations.DIVISION -> number1 / number2
        Operations.MULTIPLY -> number1 * number2
        else -> 0.0
    }

    private fun doubleToString(number: Double):String{
        val strings = number.toString().split(".")
        return if (strings[1].toDouble()==0.0) {
            strings[0]
        } else {
            number.toString()
        }
    }

    fun calculateAndRefreshCurrentInput(number1: Double, number2: Double){
        currentInput = doubleToString(calculate(number1,number2))
        operation = Operations.NOTHING
    }

    fun clear(){
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
}