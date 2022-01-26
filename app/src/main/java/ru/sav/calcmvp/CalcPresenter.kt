package ru.sav.calcmvp

class CalcPresenter(private val calcView: CalcView, private var calc: Calc) {

    fun numberPressed(number: Int){
        calc.addNumberToCurrentInput(number)
        showCurrentInput()
    }

    private fun showCurrentInput(){
        calcView.showCurrentInput(calc.currentInput)
    }

    fun cPressed(){
        calc.clear()
        showCurrentInput()
    }

    fun delPressed(){
        if (calc.delFromCurrentInput()) {
            showCurrentInput()
        }
    }

    fun dotPressed(){
        if (calc.addDotToCurrentInput()){
            showCurrentInput()
        }
    }

    private fun operationPressed(operation: Operations){
        calculate(calc.currentInput)
        calc.operation = operation
        showCurrentInput()
    }

    fun plusPressed(){
        operationPressed(Operations.PLUS)
    }

    fun minusPressed(){
        operationPressed(Operations.MINUS)
    }

    fun divisionPressed(){
        operationPressed(Operations.DIVISION)
    }

    fun multiplyPressed(){
        operationPressed(Operations.MULTIPLY)
    }

    private fun calculate(input: String): Boolean {
        if (calc.operation == Operations.NOTHING) return false

        val strings = input.split(calc.getStringOperation())

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

        calc.calculateAndRefreshCurrentInput(number1,number2)
        return true
    }

    fun equalPressed(){
        if (calculate(calc.currentInput)){
            showCurrentInput()
        }
    }

}