package ru.sav.calcmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import ru.sav.calcmvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CalcView {
    private lateinit var presenter: CalcPresenter
    private lateinit var vb: ActivityMainBinding
    private var calc = Calc()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        presenter = CalcPresenter(this, calc)

        setButtonsClickListeners()
    }

    private fun setButtonsClickListeners() {
        vb.apply {
            btn0.setOnClickListener { presenter.numberPressed(0) }
            btn1.setOnClickListener { presenter.numberPressed(1) }
            btn2.setOnClickListener { presenter.numberPressed(2) }
            btn3.setOnClickListener { presenter.numberPressed(3) }
            btn4.setOnClickListener { presenter.numberPressed(4) }
            btn5.setOnClickListener { presenter.numberPressed(5) }
            btn6.setOnClickListener { presenter.numberPressed(6) }
            btn7.setOnClickListener { presenter.numberPressed(7) }
            btn8.setOnClickListener { presenter.numberPressed(8) }
            btn9.setOnClickListener { presenter.numberPressed(9) }
            btnC.setOnClickListener { presenter.cPressed() }
            btnDel.setOnClickListener { presenter.delPressed() }
            btnDot.setOnClickListener { presenter.dotPressed() }
            btnPlus.setOnClickListener { presenter.plusPressed() }
            btnMinus.setOnClickListener { presenter.minusPressed() }
            btnDiv.setOnClickListener { presenter.divisionPressed() }
            btnMult.setOnClickListener { presenter.multiplyPressed() }
            btnEqual.setOnClickListener { presenter.equalPressed() }
        }
    }

    override fun showCurrentInput(currentInput: String) {
        vb.tvInput.text = currentInput
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(Calc.KEY_CLASS, calc)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        calc = savedInstanceState.getSerializable(Calc.KEY_CLASS) as Calc
        presenter.updateCalc(calc)
        presenter.refreshView()
    }
}