package ru.sav.calcmvp

class CalcPresenter(private val calcView: CalcView, private var calc: Calc) {

    fun numberPressed(number: Int){
        if (calc.currentInput=="0") calc.currentInput = ""

        calc.currentInput += number.toString()
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
        if (calc.currentInput.isNotEmpty()) {
            val lastChar=calc.currentInput.last()
            if ((lastChar=='+')||(lastChar=='-')||(lastChar=='*')||(lastChar=='/')) {
                calc.operation=Operations.NOTHING
            } else{
                calc.currentInput = calc.currentInput.substring(0,calc.currentInput.length-1)
            }

            if (calc.currentInput.isEmpty()) calc.currentInput = "0"
            showCurrentInput()
        }
    }

    fun dotPressed(){
        var checkString=calc.currentInput
        if (calc.operation !== Operations.NOTHING) {
            checkString = checkString.split(calc.getStringOperation())[1]
        }

        if (!checkString.contains(".")){
            calc.currentInput= "${calc.currentInput}."
            showCurrentInput()
        }
    }

    private fun operationPressed(input: String, operation: Operations){
        calculate(input)
        calc.operation = operation
        showCurrentInput()
    }

    fun plusPressed(){
        operationPressed(calc.currentInput,Operations.PLUS)
    }

    fun minusPressed(){
        operationPressed(calc.currentInput,Operations.MINUS)
    }

    fun divisionPressed(){
        operationPressed(calc.currentInput,Operations.DIVISION)
    }

    fun multiplyPressed(){
        operationPressed(calc.currentInput,Operations.MULTIPLY)
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