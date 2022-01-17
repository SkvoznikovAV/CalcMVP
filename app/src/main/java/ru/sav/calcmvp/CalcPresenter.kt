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
            if ((calc.currentInput.last()=='+')||(calc.currentInput.last()=='-')||(calc.currentInput.last()=='*')||(calc.currentInput.last()=='/')) {
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

    private fun getNumber1FromInput(input: String): Double{
        val number1:Double
        if ((input.last()=='+')||(input.last()=='-')||(input.last()=='*')||(input.last()=='/')) {
            number1=calc.number1
        } else {
            number1=input.toDouble()
        }

        return number1
    }

    private fun forceCalculate(input: String): Boolean {
        if (calc.operation == Operations.NOTHING) return false

        val strings = input.split(calc.getStringOperation())
        if (strings[1].isEmpty()) return false

        return calculate(input)
    }
    
    private fun operationPressed(input: String, operation: Operations){
        if (forceCalculate(input)) {
            calc.operation = operation
        } else {
            calc.number1=getNumber1FromInput(input)
            calc.operation = operation
        }

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
        if (strings[1].isEmpty()) return false

        calc.number2=strings[1].toDouble()
        calc.number1=calc.calculate()
        calc.operation=Operations.NOTHING
        calc.number2=0.0

        return true
    }

    fun equalPressed(){
        if (calculate(calc.currentInput)){
            showCurrentInput()
        }
    }

}