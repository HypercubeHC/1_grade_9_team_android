package com.example.myapplication

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import java.util.ArrayDeque

class interpreter {
    private var variables = mutableSetOf<String>()
    private var valuesOfVariables = mutableMapOf<String, Double>()

    fun debug() {
        for(variable in valuesOfVariables){
            Log.d("Debug","${variable.key} - ${variable.value}")
        }
    }

    fun start_program(listView: MutableList<View> = mutableListOf()) {
        listView.forEach { view ->
            implement(view)
        }
    }

    @SuppressLint("CutPasteId")
    private fun implement(view: View) {
        when(view.id){
            1 -> {
                val edit: EditText = view.findViewById(R.id.editText2)
                val string: String = edit.text.toString().filter { !it.isWhitespace() }
                Log.d("String", string)
                val list_variables = Regex("[a-z]").findAll(string)
                list_variables.forEach { variable ->
                    Log.d("variable: ", variable.value)
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

            /*4 -> {

            }

            5 -> {

            }*/
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