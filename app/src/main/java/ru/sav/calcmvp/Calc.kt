package ru.sav.calcmvp

import java.io.Serializable

class Calc: Serializable {
    var currentInput: String = "0"
        private set
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

    fun calculateAndRefreshCurrentInput(): Boolean{
        if (operation == Operations.NOTHING) return false

        val strings = currentInput.split(getStringOperation())

        val number1: Double
        val number2: Double
        if (strings.size>2){
            if (strings[1].isEmpty()) return false
            if (strings[2].isEmpty()) return false
            number1=("-"+strings[1]).toDouble()
            number2=strings[2].toDouble()
        } else {
            if (strings[0].isEmpty()) return false
            if (strings[1].isEmpty()) return false
            number1=strings[0].toDouble()
            number2=strings[1].toDouble()
        }

        currentInput = doubleToString(calculate(number1,number2))
        operation = Operations.NOTHING

        return true
    }

    fun clear(){
        operation = Operations.NOTHING
        currentInput = "0"
    }

    fun delFromCurrentInput(): Boolean {
        return if (currentInput.isNotEmpty()) {
            val lastChar = currentInput.last()
            if ((lastChar == '+') || (lastChar == '-') || (lastChar == '*') || (lastChar == '/')) {
                operation = Operations.NOTHING
            } else {
                currentInput = currentInput.substring(0, currentInput.length - 1)
            }

            if (currentInput.isEmpty()) currentInput = "0"
            true
        } else false
    }

    fun addNumberToCurrentInput(number: Int){
        if (currentInput=="0") currentInput = ""

        currentInput += number.toString()
    }

    private fun getStringOperation():String {
        return when (operation){
            Operations.PLUS -> "+"
            Operations.MINUS -> "-"
            Operations.DIVISION -> "/"
            Operations.MULTIPLY -> "*"
            else -> ""
        }
    }

    fun addDotToCurrentInput(): Boolean{
        var checkString=currentInput
        if (operation !== Operations.NOTHING) {
            checkString = checkString.split(getStringOperation())[1]
        }

        if (!checkString.contains(".")){
            currentInput= "${currentInput}."
            return true
        }

        return false
    }

    companion object {
        const val KEY_CLASS = "CALC"

    }
}
