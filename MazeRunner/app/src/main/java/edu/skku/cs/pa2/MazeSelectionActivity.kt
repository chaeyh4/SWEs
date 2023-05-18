package edu.skku.cs.pa2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException

class MazeSelectionActivity : AppCompatActivity() {
    data class Maze(var name: String ?= null, var size: Int ?= null)
    companion object{
        const val EXT_MAP = "extra_map"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze_selection)
        val tv2 = findViewById<TextView>(R.id.textView2)
        val name = intent.getStringExtra(SignInActivity.EXT_USER)
        tv2.text = name

        val client = OkHttpClient()
        val urlpath = "http://swui.skku.edu:1399/maps"
        val req = Request.Builder().url(urlpath).build()
        client.newCall(req).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val data = response.body!!.string()

                    val typeToken = object : TypeToken<List<Maze>>() {}.type
                    val mapList = Gson().fromJson<List<Maze>>(data,typeToken)

                    CoroutineScope(Dispatchers.Main).launch{
                        val listAdapter = CustomAdapter(this@MazeSelectionActivity,mapList)
                        val lv = findViewById<ListView>(R.id.listView)
                        lv.adapter=listAdapter
                    }
                }
            }
        })
    }
}

class CustomAdapter(val context:Context, val maps:List<MazeSelectionActivity.Maze>):BaseAdapter(){
    override fun getCount(): Int {
        return maps.size
    }

    override fun getItem(p0: Int): Any {
        return maps.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater: LayoutInflater =
            LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.maze_entry, null)

        val nameView = view.findViewById<TextView>(R.id.textView4)
        val sizeView = view.findViewById<TextView>(R.id.textView5)
        val btn = view.findViewById<Button>(R.id.button3)

        nameView.text = maps.get(p0).name.toString()
        sizeView.text = maps.get(p0).size.toString()

        btn.setOnClickListener {
            val selectedMaze = maps[p0].name.toString()
            val intent = Intent(context, MazeActivity::class.java).apply{
                putExtra(MazeSelectionActivity.EXT_MAP, selectedMaze)
            }
            context.startActivity(intent)
        }

        return view
    }

}