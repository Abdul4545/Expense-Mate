package com.firstapp.ca4project

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import kotlin.math.floor


class CalculatorFragment : Fragment() {

    private lateinit var expression: TextView
    private lateinit var result: TextView
    private lateinit var clear: Button
    private lateinit var backSpace: Button
    private lateinit var percent: Button
    private lateinit var divide: Button

    private lateinit var multiply: Button
    private lateinit var add: Button
    private lateinit var subtract: Button
    private lateinit var equal: Button

    private lateinit var dot: Button
    private lateinit var zero: Button
    private lateinit var doubleZero: Button
    private lateinit var one: Button

    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button


    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        // Find your views using the root view of the fragment layout
        expression = view.findViewById(R.id.input)
        result = view.findViewById(R.id.result)
        clear = view.findViewById(R.id.clear)

        backSpace = view.findViewById(R.id.backSpace)
        percent = view.findViewById(R.id.percent)
        divide = view.findViewById(R.id.divide)

        multiply = view.findViewById(R.id.multiply)
        add = view.findViewById(R.id.add)
        subtract = view.findViewById(R.id.subtract)

        equal = view.findViewById(R.id.equal)
        dot = view.findViewById(R.id.dot)
        zero = view.findViewById(R.id.zero)

        doubleZero = view.findViewById(R.id.doubleZero)
        one = view.findViewById(R.id.one)
        two = view.findViewById(R.id.two)

        three = view.findViewById(R.id.three)
        four = view.findViewById(R.id.four)
        five = view.findViewById(R.id.five)

        six = view.findViewById(R.id.six)
        seven = view.findViewById(R.id.seven)
        eight = view.findViewById(R.id.eight)
        nine = view.findViewById(R.id.nine)

        expression.movementMethod = ScrollingMovementMethod()
        expression.isActivated = true
        expression.isPressed = true

        var str:String

        clear.setOnClickListener{
            expressionText("0")
            expression.textSize = 60F
            result.textSize = 30F
            resultText()
        }

        backSpace.setOnClickListener {
            if(expression.text.toString().isNotEmpty()) {
                val lastIndex = expression.text.toString().lastIndex
                str = expression.text.toString().substring(0, lastIndex)
                expressionText(str)
                resultText()
            }
        }


        percent.setOnClickListener{
            if(expression.text.toString().endsWith("%") || expression.text.toString().endsWith("/") || expression.text.toString().endsWith("*") || expression.text.toString().endsWith("+") || expression.text.toString().endsWith("-") || expression.text.toString().endsWith(".")) {
                str = expression.text.toString()
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "%"
                expressionText(str)
            }
        }

        divide.setOnClickListener {
            if(expression.text.toString().endsWith("%") || expression.text.toString().endsWith("/") || expression.text.toString().endsWith("*") || expression.text.toString().endsWith("+") || expression.text.toString().endsWith("-") || expression.text.toString().endsWith(".")) {
                str = expression.text.toString()
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "/"
                expressionText(str)
            }
        }

        multiply.setOnClickListener{
            if(expression.text.toString().endsWith("%") || expression.text.toString().endsWith("/") || expression.text.toString().endsWith("*") || expression.text.toString().endsWith("+") || expression.text.toString().endsWith("-") || expression.text.toString().endsWith(".")) {
                str = expression.text.toString()
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "*"
                expressionText(str)
            }
        }


        add.setOnClickListener{
            if(expression.text.toString().endsWith("%") || expression.text.toString().endsWith("/") || expression.text.toString().endsWith("*") || expression.text.toString().endsWith("+") || expression.text.toString().endsWith("-") || expression.text.toString().endsWith(".")) {
                str = expression.text.toString()
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "+"
                expressionText(str)
            }
        }

        subtract.setOnClickListener {
            if(expression.text.toString().endsWith("%") || expression.text.toString().endsWith("/") || expression.text.toString().endsWith("*") || expression.text.toString().endsWith("+") || expression.text.toString().endsWith("-") || expression.text.toString().endsWith(".")) {
                str = expression.text.toString()
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "-"
                expressionText(str)
            }
        }

        equal.setOnClickListener{
            expression.textSize = 30F
            result.textSize = 60F
            expression.text = result.text
        }

        dot.setOnClickListener {
            if(expression.text.toString().endsWith("%") || expression.text.toString().endsWith("/") || expression.text.toString().endsWith("*") || expression.text.toString().endsWith("+") || expression.text.toString().endsWith("-") || expression.text.toString().endsWith(".")) {
                str = expression.text.toString()
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "."
                expressionText(str)
            }
        }


        zero.setOnClickListener{
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "0"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "0"
                expressionText(str)
                resultText()
            }
        }

        doubleZero.setOnClickListener {
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "00"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "00"
                expressionText(str)
                resultText()
            }
        }

        one.setOnClickListener{
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "1"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "1"
                expressionText(str)
                resultText()
            }
        }

        two.setOnClickListener {
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "2"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "2"
                expressionText(str)
                resultText()
            }
        }

        three.setOnClickListener{
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "3"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "3"
                expressionText(str)
                resultText()
            }
        }

        four.setOnClickListener {
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "4"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "4"
                expressionText(str)
                resultText()
            }
        }

        five.setOnClickListener{
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "5"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "5"
                expressionText(str)
                resultText()
            }
        }

        six.setOnClickListener {
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "6"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "6"
                expressionText(str)
                resultText()
            }
        }

        seven.setOnClickListener{
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "7"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "7"
                expressionText(str)
                resultText()
            }
        }

        eight.setOnClickListener {
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "8"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "8"
                expressionText(str)
                resultText()
            }

        }

        nine.setOnClickListener{
            if(expression.text.toString().startsWith("0")) {
                str = expression.text.toString().replace("0", "") + "9"
                expressionText(str)
            }

            else {
                str = expression.text.toString() + "9"
                expressionText(str)
                resultText()
            }
        }

        return view
    }

    private fun expressionText(str: String) {
        expression.text = str
    }

    private fun resultText() {
        val exp = expression.text.toString()
        val engine: ScriptEngine = ScriptEngineManager().getEngineByName("rhino")

        try {
            val res = engine.eval(exp)
            val resultString = when (res) {
                is Double -> {
                    val formattedResult = if (res % 1 == 0.0) {
                        res.toInt().toString()
                    } else {
                        String.format("%.2f", res)
                    }
                    result.text = formattedResult
                }
                else -> res.toString() 
            }
        } catch (e: Exception) {
            expression.text = expression.text.toString()
            result.text = expression.text.toString()
        }
    }

}