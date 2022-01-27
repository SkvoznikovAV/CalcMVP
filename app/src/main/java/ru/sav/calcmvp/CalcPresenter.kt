package ru.sav.calcmvp

class CalcPresenter(private val calcView: CalcView, private var calc: Calc) {

    fun updateCalc(c: Calc){
        calc = c
    }

    fun refreshView(){
        showCurrentInput()
    }

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
        calc.calculateAndRefreshCurrentInput()
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

    fun equalPressed(){
        if (calc.calculateAndRefreshCurrentInput()){
            showCurrentInput()
        }
    }
}