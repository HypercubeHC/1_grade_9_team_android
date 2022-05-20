package com.example.myapplication

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.jraska.console.Console
import android.os.AsyncTask
import java.util.*

class Interpreter(private val userCode: MutableList<View> = mutableListOf()) : AsyncTask<Any, Void, Void>() {
    private var variables = mutableSetOf<String>()
    private var valuesOfVariables = mutableMapOf<String, Double>()
    private var listArray = mutableMapOf<String, MutableList<Double>>()
    private var checkout: Int = -1

    fun debug() {
        for(variable in valuesOfVariables){
            Console.writeLine("Debug ${variable.key} - ${variable.value}")
        }
    }

    fun start_program(listView: MutableList<View> = mutableListOf()) {
        listView.forEach { view ->
            implement(view)
        }
    }

    //method for ASYNCHRONOUS code execution to provide real-time console interaction
    override fun doInBackground(vararg params: Any?): Void? {
        start_program(userCode)
        //debug()
        return null
    }

    @SuppressLint("CutPasteId")
    private fun implement(view: View) {
        when(view.id){
            1 -> {
                val edit: EditText = view.findViewById(R.id.editText2)
                val string: String = edit.text.toString().filter { !it.isWhitespace() }
                Console.writeLine("String $string")
                val list_variables = Regex("[a-z]").findAll(string)
                list_variables.forEach { variable ->
                    Console.writeLine("variable: ${variable.value}")
                    valuesOfVariables[variable.value] = 0.0
                }
            }

            2 -> {
                val edit: EditText = view.findViewById(R.id.editText1)
                val edit2: EditText = view.findViewById(R.id.editText2)
                val variable = edit.text.toString().filter { !it.isWhitespace() }
                val value = edit2.text.toString().filter { !it.isWhitespace() }
                val result = calc(value)
                if (result != null) {
                    valuesOfVariables[variable] = result.toDouble()
                }
            }

            2131230815 -> {
                runThroughIf(view as ViewGroup)
            }

            2131230816 -> {
                runThroughIfElse(view as ViewGroup)
            }

            2131230817 -> {
                runThroughWhile(view as ViewGroup)
            }

            4 -> {
                val edit: EditText = view.findViewById(R.id.editText4)
                val string: String = edit.text.toString().filter { !it.isWhitespace() }
                val list_variables = Regex("[a-z]").findAll(string)
                list_variables.forEach { variable ->
                    Console.writeLine("Debug ${variable.value} - ${valuesOfVariables[variable.value]}")
                }
            }

            /*5 -> {

            }*/
        }
    }

    private fun runThroughIfElse(view: ViewGroup) {
        val blockOfCondition: View = view.getChildAt(0)
        val edit: EditText = blockOfCondition.findViewById(R.id.editText1)
        val edit2: EditText = blockOfCondition.findViewById(R.id.editText2)
        val condition: TextView = blockOfCondition.findViewById(R.id.select_comp)
        edit.text.toString().filter { !it.isWhitespace() }
        edit2.text.toString().filter { !it.isWhitespace() }
        when(condition.text) {
            "==" -> {
                if(calc(edit.text.toString()) == calc(edit2.text.toString())) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                } else {
                    checkout = 1
                }
            }
            "!=" -> {
                if(calc(edit.text.toString()) != calc(edit2.text.toString())) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                } else {
                    checkout = 1
                }
            }
            ">" -> {
                if(calc(edit.text.toString())!! > calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                } else {
                    checkout = 1
                }
            }
            "<" -> {
                if(calc(edit.text.toString())!! < calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                } else {
                    checkout = 1
                }
            }
            ">=" -> {
                if(calc(edit.text.toString())!! >= calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                } else {
                    checkout = 1
                }
            }
            "<=" -> {
                if(calc(edit.text.toString())!! <= calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                } else {
                    checkout = 1
                }
            }
        }
    }

    private fun runThroughWhile(view: ViewGroup) {
        val blockOfCondition: View = view.getChildAt(0)
        val edit: EditText = blockOfCondition.findViewById(R.id.editText6)
        val edit2: EditText = blockOfCondition.findViewById(R.id.editText7)
        val condition: TextView = blockOfCondition.findViewById(R.id.select_comp)
        edit.text.toString().filter { !it.isWhitespace() }
        edit2.text.toString().filter { !it.isWhitespace() }
        when(condition.text) {
            "==" -> {
                while(calc(edit.text.toString()) == calc(edit2.text.toString())) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            "!=" -> {
                while(calc(edit.text.toString()) != calc(edit2.text.toString())) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            ">" -> {
                while(calc(edit.text.toString())!! > calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            "<" -> {
                while(calc(edit.text.toString())!! < calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            ">=" -> {
                while(calc(edit.text.toString())!! >= calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            "<=" -> {
                while(calc(edit.text.toString())!! <= calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
        }
    }






    private fun runThroughIf(view: ViewGroup) {
        val blockOfCondition: View = view.getChildAt(0)
        val edit: EditText = blockOfCondition.findViewById(R.id.editText1)
        val edit2: EditText = blockOfCondition.findViewById(R.id.editText2)
        val condition: TextView = blockOfCondition.findViewById(R.id.select_comp)
        edit.text.toString().filter { !it.isWhitespace() }
        edit2.text.toString().filter { !it.isWhitespace() }
        when(condition.text) {
            "==" -> {
                if(calc(edit.text.toString()) == calc(edit2.text.toString())) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            "!=" -> {
                if(calc(edit.text.toString()) != calc(edit2.text.toString())) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            ">" -> {
                if(calc(edit.text.toString())!! > calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            "<" -> {
                if(calc(edit.text.toString())!! < calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            ">=" -> {
                if(calc(edit.text.toString())!! >= calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
            "<=" -> {
                if(calc(edit.text.toString())!! <= calc(edit2.text.toString())!!) {
                    for (index in 1 until view .childCount) {
                        val nextChild = view.getChildAt(index)
                        implement(nextChild)
                    }
                }
            }
        }
    }


    private fun delim(c: Char): Boolean {
        return c == ' '
    }

    private fun is_op(c: Char): Boolean {
        return (c == '+') || (c == '-') || (c == '*') || (c == '/') || (c == '%')
    }

    private fun priority(op: Char): Int {
        return if (op == '+' || op == '-') 1 else if (op == '*' || op == '/' || op == '%') 2 else -1
    }

    private fun process_op (st: ArrayDeque<Double>, op: Char) {
        val r = st.last()
        st.removeLast()
        var l: Double = 0.0
        if(!st.isEmpty()) {
            l = st.last()
            st.removeLast()
        }
        when(op) {
            '+' -> st.addLast(l + r)
            '-' -> st.addLast(l - r)
            '*' -> st.addLast(l * r)
            '/' -> st.addLast(l / r)
            '%' -> st.addLast(l % r)
        }
    }

    fun calc(s: String): Double? {
        var st = ArrayDeque<Double>()
        var op = ArrayDeque<Char>()
        var i = 0
        while(i < s.length) {
            if (!delim (s[i])) {
                if (s[i] == '(') {
                    op.addLast('(')
                } else if (s[i] == ')') {
                    while (op.last() != '(') {
                        process_op(st, op.last())
                        op.removeLast()
                    }
                    op.removeLast()
                } else if (is_op(s[i])) {
                    var curop: Char = s[i]
                    if(i > 0 && s[i - 1] == '-') {
                        curop = '+'
                        op.removeLast()
                    }
                    while (!op.isEmpty() && priority(op.last()) >= priority(curop)) {
                        process_op(st, op.last())
                        op.removeLast()
                    }
                    op.addLast(curop)
                } else {
                    var operand: String = ""
                    while (i < s.length && (s[i].isLetterOrDigit() || s[i] == '.')) {
                        operand += s[i++]
                    }
                    --i
                    if (operand[0].isDigit())
                        st.addLast(operand.toDouble())
                    else {
                        //Log.d("operand", operand[0].toString())
                        //Log.d("calc", (valuesOfVariables[operand].toString()))
                        st.addLast(valuesOfVariables[operand]!!)
                    }
                }
            }
            i++
        }
        while (!op.isEmpty()) {
            process_op(st, op.last())
            op.removeLast()
        }
        return st.last()
    }
}