package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric : Boolean = false
    var btnDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    private fun removeZeroAfterDot(result: String) :String {
        var value = result
        if (result.contains(".0"))
            value = result.substring(0, result.length -2)
        return  value
    }

    fun onRemove(view: View) {
        var result = tvInput.text.toString()
            tvInput.setText(result.substring(0, result.length - 1))

    }
    fun onEqual(view: View){
        if(lastNumeric){//kiem tra neu ki tu cuoi cung la so thi moi thuc hien phep tinh
            var tvValue = tvInput.text.toString() // bien luu gia tri cua van ban
            var prefix = "1"
            try {

                if(tvValue.contains("−")){
                    val splitValue = tvValue.split("−") // tach van ban qua ki tu "−" thanh hai gia tri khac nhau
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // 99  − 1
                    tvInput.text = removeZeroAfterDot("${one.toDouble() - two.toDouble()}")
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+") // tach van ban qua ki tu "−" thanh hai gia tri khac nhau
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // 99  − 1
                    tvInput.text = removeZeroAfterDot("${one.toDouble() + two.toDouble()}")
                }else if(tvValue.contains("×")){
                    val splitValue = tvValue.split("×") // tach van ban qua ki tu "−" thanh hai gia tri khac nhau
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // 99  − 1
                    tvInput.text = removeZeroAfterDot("${one.toDouble() * two.toDouble()}")
                }else if(tvValue.contains("÷")){
                    val splitValue = tvValue.split("÷") // tach van ban qua ki tu "−" thanh hai gia tri khac nhau
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    // 99  − 1
                    tvInput.text = removeZeroAfterDot("${one.toDouble() / two.toDouble()}")
                }else if(tvValue.contains("%")){
                    val splitValue = tvValue.split("%") // tach van ban qua ki tu "−" thanh hai gia tri khac nhau
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if(two.isEmpty()){
                        two = "1"
                        tvInput.text ="${one.toDouble() / 100 * two.toDouble()}"
                    }
                    tvInput.text = "${one.toDouble() / 100 * two.toDouble()}"

                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onDigit(view: View){
        tvInput.append((view as Button).text)
        lastNumeric = true
    }
    fun onClear(view: View){
        tvInput.text = ""
        lastNumeric = false
        btnDot = false
    }
    fun onDecimalPoint(view: View){
        if (lastNumeric && !btnDot){//btnDot  = false, !btnDot = true, in ra "." roi dat thanhf true, va tu sau no se always !btnDot = false
            tvInput.append(".")
            lastNumeric = false
            btnDot = true
        }
    }
    fun onOperator(view: View){
        if(lastNumeric && !IsOperatorAdded(tvInput.text.toString()))
        {
            tvInput.append((view as Button).text)
            lastNumeric = false
            btnDot = false
        }
    }
    fun onOperatorPercent(view: View){
        if(lastNumeric && !IsOperatorAdded(tvInput.text.toString()))
        {
            tvInput.append((view as Button).text)
            btnDot = false
        }
    }

    private  fun IsOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("−")){
            false
        } else {
            value.contains("×") || value.contains("+") || value.contains("÷") ||value.contains("−")

        }
    }
}