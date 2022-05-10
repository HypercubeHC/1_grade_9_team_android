package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @SuppressLint("WrongViewCast", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main.setOnDragListener(dragListener)
        //binding.buttons.setOnDragListener(dragListener)
        var child: View
        var inflatedView: View
        var inflatedView2: View
        var inflatedView3: View
        val variable = VariableBlock()
        val popupMenu2 = PopupMenu(this, binding.button)
        var listEdit: MutableList<EditText> = mutableListOf<EditText>()
        popupMenu2.inflate(R.menu.popupmenu)
        popupMenu2.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.variable -> {
                    inflatedView = View.inflate(this, R.layout.my_listview, null)
                    binding.main.addView(inflatedView,  ViewGroup.LayoutParams(1080, 195))
                    child = inflatedView

                    val edit: EditText = child.findViewById(R.id.editText2)
                    listEdit.add(edit)

                    child.setOnClickListener {
                        for(i in listEdit) {
                            i.setEnabled(true)
                        }
                    }

                    child.setOnLongClickListener{
                        for(i in listEdit) {
                            i.setEnabled(false)
                        }
                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(it)
                        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                        it.visibility = View.INVISIBLE
                        true
                    }
                    //val edit: EditText = findViewById(R.id.editText2)
                    //edit.setEnabled(false);
                    //edit.setFocusable(false);
                    //text.setOnDragListener(OnDragListener { v, event -> true })
                }
                R.id.assign -> {
                    inflatedView2 = View.inflate(this, R.layout.block_assign, null)
                    binding.main.addView(inflatedView2,  ViewGroup.LayoutParams(1080, 195))
                    child = inflatedView2

                    val edit: EditText = child.findViewById(R.id.editText2)
                    listEdit.add(edit)

                    child.setOnClickListener {
                        for(i in listEdit) {
                            i.setEnabled(true)
                        }
                    }

                    child.setOnLongClickListener{

                        for(i in listEdit) {
                            i.setEnabled(false)
                        }

                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(it)
                        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                        it.visibility = View.INVISIBLE
                        true
                    }
                    val btn: TextView = child.findViewById(R.id.select)
                    val popupMenu1 = PopupMenu(this, btn)
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
                        val a = it.title
                        val toast = Toast.makeText(applicationContext, a, Toast.LENGTH_SHORT)
                        toast.show()
                        btn.text = a
                        true
                    }
                }
                R.id.constif -> {
                    inflatedView3 = View.inflate(this, R.layout.block_if, null)
                    //binding.main.addView(inflatedView3,  ViewGroup.LayoutParams(1080, 195))
                    binding.main.addView(inflatedView3)
                    inflatedView3.setOnDragListener(dragListener)
                    child = inflatedView3

                    val edit1: EditText = child.findViewById(R.id.editText1)
                    val edit2: EditText = child.findViewById(R.id.editText2)
                    listEdit.add(edit1)
                    listEdit.add(edit2)

                    child.setOnClickListener {
                        for(i in listEdit) {
                            i.setEnabled(true)
                        }
                    }

                    child.setOnLongClickListener{

                        for(i in listEdit) {
                            i.setEnabled(false)
                        }

                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(it)
                        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                        it.visibility = View.INVISIBLE
                        true
                    }
                    val btn2: TextView = child.findViewById(R.id.select_comp)
                    val popupMenu3 = PopupMenu(this, btn2)
                    popupMenu3.inflate(R.menu.popup_menu_comparisons)
                    popupMenu3.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.more -> {
                                btn2.text = it.title
                            }
                            R.id.less -> {
                                btn2.text = it.title
                            }
                            R.id.notequally -> {
                                btn2.text = it.title
                            }
                            R.id.equally -> {
                                btn2.text = it.title
                            }
                            R.id.moreequal -> {
                                btn2.text = it.title
                            }
                            R.id.lessequal -> {
                                btn2.text = it.title
                            }
                        }
                        false
                    }
                    btn2.setOnClickListener {
                        popupMenu3.show()
                    }
                }
                R.id.printf -> {
                    inflatedView = View.inflate(this, R.layout.block_print, null)
                    binding.main.addView(inflatedView,  ViewGroup.LayoutParams(1080, 195))
                    child = inflatedView

                    val edit: EditText = child.findViewById(R.id.editText4)
                    listEdit.add(edit)

                    child.setOnClickListener {
                        for(i in listEdit) {
                            i.setEnabled(true)
                        }
                    }

                    child.setOnLongClickListener{

                        for(i in listEdit) {
                            i.setEnabled(false)
                        }

                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(it)
                        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                        it.visibility = View.INVISIBLE
                        true
                    }
                }
                R.id.scanf -> {
                    inflatedView = View.inflate(this, R.layout.block_scan, null)
                    binding.main.addView(inflatedView,  ViewGroup.LayoutParams(1080, 195))
                    child = inflatedView

                    val edit: EditText = child.findViewById(R.id.editText5)
                    listEdit.add(edit)

                    child.setOnClickListener {
                        for(i in listEdit) {
                            i.setEnabled(true)
                        }
                    }

                    child.setOnLongClickListener{

                        for(i in listEdit) {
                            i.setEnabled(false)
                        }

                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(it)
                        it.startDragAndDrop(data, dragShadowBuilder, it, 0)
                        it.visibility = View.INVISIBLE
                        true
                    }
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
    val dragListener = View.OnDragListener { view, event ->     //дописать расширение для вложенного if
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
            DragEvent.ACTION_DROP -> {                                          //добавить  перенос блока вверх
                //val item = event.clipData.getItemAt(0)
                //val dragData = item.text
                //Toast.makeText(this, dragData, Toast.LENGTH_SHORT).show()
                view.invalidate()
                Log.d("position Y: ", event.y.toString()) //относительно род.элемента
                val v = event.localState as View
                val  owner = v.parent as ViewGroup
                val count = owner.childCount
                owner.removeView(v)
                if (count <= 1) {
                    binding.main.removeView(owner)
                }
                else {
                    if(owner.id == 2131230815) {
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            (count - 1) * 195
                        ).apply {
                            gravity = Gravity.CENTER
                        }
                        //owner.setOrientation(LinearLayout.VERTICAL)
                        owner.setLayoutParams(params)
                    }
                }
                Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()

                val destination = view as LinearLayout
                /*if(owner == destination) {
                    Toast.makeText(this, "тот же layout", Toast.LENGTH_SHORT).show()
                }*/
                val count2 = destination.childCount
                Log.d("Id: ", destination.id.toString())
                Log.d("View Id: ", v.id.toString())



                if(destination.id == 2131230815) {
                    if(v.id == 2131230815) {
                        val params = LinearLayout.LayoutParams( //прописать расширение вложенных if
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            count2 * 195 + v.height
                        ).apply {
                            gravity = Gravity.CENTER
                        }
                        destination.setOrientation(LinearLayout.VERTICAL)
                        destination.setLayoutParams(params)
                    } else {
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            count2 * 195 + v.height
                        ).apply {
                            gravity = Gravity.CENTER
                        }
                        destination.setOrientation(LinearLayout.VERTICAL)
                        destination.setLayoutParams(params)
                    }

                } /*else {
                    val params = FrameLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        count2 * 195 + 195
                    ).apply {
                        gravity = Gravity.CENTER
                    }
                    destination.setOrientation(LinearLayout.VERTICAL)
                    destination.setLayoutParams(params)
                }*/
                //if(1 == count2) {
                //    destination.addView(v)
                //}
                    //else {
                    Log.d("Count: ", count2.toString())
                    for (index in 0 until count2) {
                        Log.d("Iteration: ", index.toString())
                        val nextChild = destination.getChildAt(index)
                        if ((nextChild.getTop() + 97.5 < event.y) && (nextChild.getTop() + 195 > event.y)){
                            Log.d("Index: ", index.toString())
                            destination.addView(v, index + 1)
                            break
                        } else {
                            if((nextChild.getTop() + 97.5 > event.y) && (nextChild.getTop() < event.y)) {
                                Log.d("Index: ", index.toString())
                                destination.addView(v, index)
                                break
                            }
                        }
                        if (index == count2 - 1) {
                            destination.addView(v, index + 1)
                        }
                        Log.d("position of childs: ", nextChild.getTop().toString())
                    }
                //}
                //destination.addView(v)
                v.visibility = View.VISIBLE
                //Log.d("position: ", v.getTop().toString()) //относительно род.элемента
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                view.invalidate()
                if(event.getResult().toString() == "false"){
                    val v = event.localState as View
                    v.visibility = View.VISIBLE
                    binding.main.removeView(v)//прописать удаление edittext из списка
                }
                true
            }
            else -> false //прописать выход блока за пределы
        }
    }
}