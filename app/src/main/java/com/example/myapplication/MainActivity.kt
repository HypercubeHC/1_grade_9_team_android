package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.DragEvent
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main.setOnDragListener(dragListener)
        binding.one.setOnDragListener(dragListener)
        binding.two.setOnDragListener(dragListener)
        binding.three.setOnDragListener(dragListener)
        val inflated23: View
        var child: View
        var inflatedView: View
        var inflatedView2: View
        val variable = VariableBlock()
        val popupMenu2 = PopupMenu(this, binding.button)
        popupMenu2.inflate(R.menu.popupmenu)
        popupMenu2.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.variable -> {
                    binding.textView.text = "Блок переменных"
                    inflatedView = View.inflate(this, R.layout.my_listview, null)
                    binding.one.addView(inflatedView,  ViewGroup.LayoutParams(1080, 195))
                    child = inflatedView
                    child.setOnLongClickListener{
                        val clipText = "This is our Clipdata Text"
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(it)
                        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                        it.visibility = View.INVISIBLE
                        true
                    }
                }
                R.id.assign -> {
                    binding.textView.text = "Блок присваивания"
                    val layout: LinearLayout = binding.one
                    val count = layout.childCount
                    var v: View? = null
                    for (i in 0 until count) {
                        v = layout.getChildAt(i)
                        val text = resources.getResourceEntryName(v.getId())
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(applicationContext, text, duration)
                        toast.show()
                    }
                }
                R.id.arithmetic -> {
                    binding.textView.text = "Блок арифметических операций"
                    val layout: LinearLayout = binding.one
                    val count = layout.childCount
                    var v: View? = null
                    for (i in 0 until count) {
                        v = layout.getChildAt(i)
                        binding.one.removeView(v)
                    }
                }
                R.id.assign_test -> {
                    inflatedView2 = View.inflate(this, R.layout.block_assign, null)
                    binding.one.addView(inflatedView2,  ViewGroup.LayoutParams(1080, 195))
                    child = inflatedView2
                    child.setOnLongClickListener{
                        val clipText = "This is our Clipdata Text"
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(it)
                        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                        it.visibility = View.INVISIBLE
                        true
                    }
                    val btn: TextView = findViewById(R.id.select)
                    val popupMenu1: PopupMenu = PopupMenu(this, btn)
                    popupMenu1.inflate(R.menu.popupmenu)
                    btn.setOnClickListener {
                        val array = variable.get()
                        popupMenu1.menu.clear()
                        array.forEach() {
                            popupMenu1.getMenu().add(it) //динамично добавлять при нажатии кнопки, иначе он не видит
                        }
                        popupMenu1.show()
                    }
                    popupMenu1.setOnMenuItemClickListener{
                        var a = it.title
                        val toast = Toast.makeText(applicationContext, a, Toast.LENGTH_SHORT)//в чем пркол true?
                        toast.show()
                        btn.text = a
                        true
                    }
                }
                R.id.create_lay -> {
                    val new = LinearLayout(this)
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        195
                    ).apply {
                        gravity = Gravity.CENTER
                    }
                    new.setOrientation(LinearLayout.VERTICAL)
                    new.setLayoutParams(params)
                    binding.main.addView(new)
                    new.setOnDragListener(dragListener)
                    val toast = Toast.makeText(applicationContext, "Создан слой", Toast.LENGTH_SHORT)//в чем пркол true?
                    toast.show()
                }
            }
            false
        }
        binding.button.setOnClickListener {
            popupMenu2.show()
        }
        binding.floatingActionButton.setOnClickListener {
            val editText2: EditText = findViewById(R.id.editText2)
            val a = editText2.getText().toString()
            val toast = Toast.makeText(applicationContext, a, Toast.LENGTH_SHORT)
            toast.show()
            variable.set(a)
        }
    }
    val dragListener = View.OnDragListener { view, event ->
        when(event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true
            DragEvent.ACTION_DRAG_EXITED -> {
                view.invalidate()
                true
            }
            DragEvent.ACTION_DROP -> {
                val item = event.clipData.getItemAt(0)
                val dragData = item.text
                Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()
                view.invalidate()
                val v = event.localState as View
                val  owner = v.parent as ViewGroup
                owner.removeView(v)
                val destination = view as LinearLayout

                destination.addView(v)
                v.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                true
            }
            else -> false
        }
    }
}