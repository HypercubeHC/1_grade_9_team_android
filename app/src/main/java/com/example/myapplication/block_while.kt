package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast

open class block_while @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val view = LayoutInflater.from(context).inflate(R.layout.block_while, this, true)
    val block3: View = view.findViewById(R.id.block_while_parent)
    val btn2: TextView = view.findViewById(R.id.select_comp)
    val popupMenu3: PopupMenu = PopupMenu(context, btn2)
}