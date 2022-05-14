package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("WrongViewCast", "ClickableViewAccessibility", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.main.setOnDragListener(dragListener)
        val popupMenu2 = PopupMenu(this, binding.button)
        val listEdit: MutableList<EditText> = mutableListOf()
        val listView: MutableList<View> = mutableListOf()
        popupMenu2.inflate(R.menu.popupmenu)
        popupMenu2.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.variable -> {
                    val view1 = block_initialization(this)
                    view1.id = 1
                    binding.main.addView(view1)
                    val edit: EditText = view1.findViewById(R.id.editText2)
                    listEdit.add(edit)
                    view1.setOnClickListener {
                        for (i in listEdit) {
                            i.isEnabled = true
                        }
                    }
                    view1.setOnLongClickListener { view ->
                        for (i in listEdit) {
                            i.isEnabled = false
                        }
                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(view)
                        view.startDragAndDrop(data, dragShadowBuilder, view, 0)
                        view.visibility = View.INVISIBLE
                        true
                    }
                }
                R.id.assign -> {
                    val view2 = block_assign(this)
                    view2.id = 2
                    binding.main.addView(view2)
                    val edit: EditText = view2.findViewById(R.id.editText2)
                    val edit2: EditText = view2.findViewById(R.id.editText1)
                    listEdit.add(edit)
                    listEdit.add(edit2)
                    view2.setOnClickListener {
                        for (i in listEdit) {
                            i.isEnabled = true
                        }
                    }
                    view2.setOnLongClickListener { view ->
                        for (i in listEdit) {
                            i.isEnabled = false
                        }
                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(view)
                        view.startDragAndDrop(data, dragShadowBuilder, view, 0)
                        view.visibility = View.INVISIBLE
                        true
                    }
                }
                R.id.constif -> {
                    val view3 = block_if(this)
                    binding.main.addView(view3)
                    view3.id = 2131230815
                    view3.block.id = 10
                    view3.setOnDragListener(dragListener)
                    view3.popupMenu3.inflate(R.menu.popup_menu_comparisons)
                    view3.popupMenu3.setOnMenuItemClickListener { menu ->
                        when (menu.itemId) {
                            R.id.more -> {
                                view3.btn2.text = menu.title
                            }
                            R.id.less -> {
                                view3.btn2.text = menu.title
                            }
                            R.id.notequally -> {
                                view3.btn2.text = menu.title
                            }
                            R.id.equally -> {
                                view3.btn2.text = menu.title
                            }
                            R.id.moreequal -> {
                                view3.btn2.text = menu.title
                            }
                            R.id.lessequal -> {
                                view3.btn2.text = menu.title
                            }
                        }
                        false
                    }
                    val edit1: EditText = view3.findViewById(R.id.editText1)
                    val edit2: EditText = view3.findViewById(R.id.editText2)
                    listEdit.add(edit1)
                    listEdit.add(edit2)
                    view3.setOnClickListener {
                        for (i in listEdit) {
                            i.isEnabled = true
                        }
                    }
                    view3.setOnLongClickListener { view ->
                        for (i in listEdit) {
                            i.isEnabled = false
                        }
                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(view)
                        view.startDragAndDrop(data, dragShadowBuilder, view, 0)
                        view.visibility = View.INVISIBLE
                        true
                    }
                    view3.btn2.setOnClickListener {
                        view3.popupMenu3.show()
                    }
                }
                R.id.printf -> {
                    val view4 = block_print(this)
                    view4.id = 4
                    binding.main.addView(view4)
                    val edit: EditText = view4.findViewById(R.id.editText4)
                    listEdit.add(edit)
                    view4.setOnClickListener {
                        for (i in listEdit) {
                            i.isEnabled = true
                        }
                    }
                    view4.setOnLongClickListener { view ->
                        for (i in listEdit) {
                            i.isEnabled = false
                        }
                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(view)
                        view.startDragAndDrop(data, dragShadowBuilder, view, 0)
                        view.visibility = View.INVISIBLE
                        true
                    }
                }
                R.id.scanf -> {
                    val view5 = block_scan(this)
                    view5.id = 5
                    binding.main.addView(view5)
                    val edit: EditText = view5.findViewById(R.id.editText5)
                    listEdit.add(edit)
                    view5.setOnClickListener {
                        for (i in listEdit) {
                            i.isEnabled = true
                        }
                    }
                    view5.setOnLongClickListener { view ->
                        for (i in listEdit) {
                            i.isEnabled = false
                        }
                        val clipText = ""
                        val item = ClipData.Item(clipText)
                        val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
                        val data = ClipData(clipText, mimeTypes, item)
                        val dragShadowBuilder = View.DragShadowBuilder(view)
                        view.startDragAndDrop(data, dragShadowBuilder, view, 0)
                        view.visibility = View.INVISIBLE
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
            val layout: LinearLayout = binding.main
            val count = layout.childCount
            var v: View?
            for (i in 0 until count) {
                v = layout.getChildAt(i)
                listView.add(v)
            }
            /*
                Log.d("Child's id: ", v.id.toString())
                if (v.id == 2131230815) {
                    Log.d("Child's of If:", ": ")
                    for (index in 0 until (v as ViewGroup).childCount) {
                        val nextChild = v.getChildAt(index)
                        Log.d("If child's id: ", nextChild.id.toString())
                        if (nextChild.id == 2131230815) {
                            Log.d("Child's of If2:", ": ")
                            for (iteration in 0 until (nextChild as ViewGroup).childCount) {
                                val nextChild2 = nextChild.getChildAt(iteration)
                                Log.d("If child's id2: ", nextChild2.id.toString())
                            }
                        }
                    }
                }
            }*/
            val start = interpreter()
            start.start_program(listView)
            start.debug()
        }
    }
    private val dragListener =
        View.OnDragListener { view, event ->
            when (event.action) {
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
                    view.invalidate()
                    Log.d("position Y: ", event.y.toString())
                    val v = event.localState as View
                    val owner = v.parent as ViewGroup
                    val count = owner.childCount
                    owner.removeView(v)
                    if (count <= 1) {
                        binding.main.removeView(owner)
                    } else {
                        if (owner.id == 2131230815) {
                            val params = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                (count - 1) * 195
                            ).apply {
                                gravity = Gravity.CENTER
                            }
                            owner.layoutParams = params
                        }
                    }
                    Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()
                    val destination = view as LinearLayout
                    val count2 = destination.childCount
                    Log.d("Id: ", destination.id.toString())
                    Log.d("View Id: ", v.id.toString())
                    if (destination.id == 2131230815) {
                        if (v.id == 2131230815) {
                            val params =
                                LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    count2 * 195 + v.height
                                ).apply {
                                    gravity = Gravity.CENTER
                                }
                            destination.orientation = LinearLayout.VERTICAL
                            destination.layoutParams = params
                        } else {
                            val params = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                count2 * 195 + v.height
                            ).apply {
                                gravity = Gravity.CENTER
                            }
                            destination.orientation = LinearLayout.VERTICAL
                            destination.layoutParams = params
                        }
                    }
                    if (destination.id == 2131230815) {
                        destination.background =
                            ContextCompat.getDrawable(this, R.drawable.layout_bg_purple)
                    }
                    Log.d("Count: ", count2.toString())
                    for (index in 0 until count2) {
                        Log.d("Iteration: ", index.toString())
                        val nextChild = destination.getChildAt(index)
                        if ((nextChild.top + 97.5 < event.y) && (nextChild.top + 195 > event.y)) {
                            Log.d("Index: ", index.toString())
                            destination.addView(v, index + 1)
                            break
                        } else {
                            if ((nextChild.top + 97.5 > event.y) && (nextChild.top < event.y)) {
                                Log.d("Index: ", index.toString())
                                if (destination.id == 2131230815 && index == 0) {
                                    destination.addView(v, index + 1)
                                } else {
                                    destination.addView(v, index)
                                }
                                break
                            }
                        }
                        if (index == count2 - 1) {
                            destination.addView(v, index + 1)
                        }
                        Log.d("position of child's: ", nextChild.top.toString())
                    }
                    v.visibility = View.VISIBLE
                    true
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                    view.invalidate()
                    if (event.result.toString() == "false") {
                        val v = event.localState as View
                        binding.main.removeView(v)
                    }
                    true
                }
                else -> false
            }
        }
}