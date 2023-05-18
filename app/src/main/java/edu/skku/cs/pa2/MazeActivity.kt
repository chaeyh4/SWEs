package edu.skku.cs.pa2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MazeActivity : AppCompatActivity() {
    data class MazeMap(var maze: String ?= null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maze)
        val client = OkHttpClient()
        val url = "http://swui.skku.edu:1399/maze/map?name="
        val name = intent.getStringExtra(MazeSelectionActivity.EXT_MAP)
        val req = Request.Builder().url(url+name).build()
        var location: Int = 0
        var mazeSize: Int = 0
        var count: Int = 0
        val mazeData = arrayListOf<Int>()




        client.newCall(req).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val data = response.body!!.string()
                    val responseData = Gson().fromJson(data, MazeActivity.MazeMap::class.java)
                    val mapinfo = responseData.maze.toString()

                    val rows = mapinfo.split("\n")
                    for (row in rows) {
                        if (row.isNotBlank()) {
                            val cells = row.trim().split(" ").map { it.toInt() }
                            mazeData.addAll(cells)
                        }
                    }
                    mazeSize = mazeData[0]
                    mazeData.removeAt(0)

                    CoroutineScope(Dispatchers.Main).launch{
                        val gridView = findViewById<GridView>(R.id.gridView)
                        gridView.numColumns = mazeSize
                        val mazeAdapter = MazeAdapter(this@MazeActivity, mazeData, mazeSize)
                        gridView.adapter = mazeAdapter

                        gridView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                            override fun onGlobalLayout() {
                                // 첫 번째 아이템의 imageView2에 접근하여 이미지 설정
                                val firstItemView = gridView.getChildAt(0)
                                if (firstItemView != null) {
                                    val imageView2 =
                                        firstItemView.findViewById<ImageView>(R.id.imageView2)
                                    imageView2.setImageResource(R.drawable.user)
                                }
                                val lastItemView = gridView.getChildAt(mazeSize*mazeSize-1)

                                if (lastItemView != null) {
                                    val imageView3 =
                                        lastItemView.findViewById<ImageView>(R.id.imageView2)
                                    imageView3.setImageResource(R.drawable.goal)
                                }

                                // 레이아웃 리스너 제거
                                gridView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                            }
                        })



                    }
                }
            }
        })


        val btnUp = findViewById<Button>(R.id.button5)
        val btnDown = findViewById<Button>(R.id.button6)
        val btnLeft = findViewById<Button>(R.id.button7)
        val btnRight = findViewById<Button>(R.id.button8)



        btnUp.setOnClickListener {
            val gridView = findViewById<GridView>(R.id.gridView)
            val value = mazeData.get(location)
            val tv = findViewById<TextView>(R.id.textView7)
            val final:Int = mazeSize*mazeSize-1

            when (value) {
                8, 9, 10, 11, 12, 13, 14, 15 -> {

                }
                else -> {
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageDrawable(null)
                    location -= mazeSize
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.user)
                    count += 1
                    tv.text = "Turn : "+count.toString()
                    if (location == final){
                        Toast.makeText(this,"Finish!",Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }

        btnDown.setOnClickListener {
            val gridView = findViewById<GridView>(R.id.gridView)
            val value = mazeData.get(location)
            val tv = findViewById<TextView>(R.id.textView7)
            val final:Int = mazeSize*mazeSize-1

            when (value) {
                 2, 3, 6, 10, 7, 11, 14, 15-> {

                }
                else -> {
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageDrawable(null)
                    location += mazeSize
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.user)
                    rotateImageDown(gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2))
                    count += 1
                    tv.text = "Turn : "+count.toString()
                    if (location == final){
                        Toast.makeText(this,"Finish!",Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

        btnLeft.setOnClickListener {
            val gridView = findViewById<GridView>(R.id.gridView)
            val value = mazeData.get(location)
            val tv = findViewById<TextView>(R.id.textView7)
            val final:Int = mazeSize*mazeSize-1

            when (value) {
                4, 5, 6, 7, 12, 13, 14, 15 -> {

                }
                else ->{
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageDrawable(null)
                    location -= 1
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.user)
                    rotateImageLeft(gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2))
                    count += 1
                    tv.text = "Turn : "+count.toString()
                    if (location == final){
                        Toast.makeText(this,"Finish!",Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }

        btnRight.setOnClickListener {
            val gridView = findViewById<GridView>(R.id.gridView)
            val value = mazeData.get(location)
            val tv = findViewById<TextView>(R.id.textView7)
            val final:Int = mazeSize*mazeSize-1

            when (value) {
                1, 3, 5, 7, 9, 11, 13, 15 -> {
                }
                else -> {
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageDrawable(null)
                    location += 1
                    gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2).setImageResource(R.drawable.user)
                    rotateImageRight(gridView.getChildAt(location).findViewById<ImageView>(R.id.imageView2))
                    count += 1
                    tv.text = "Turn : "+count.toString()
                    if (location == final){
                        Toast.makeText(this,"Finish!",Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

        val btnHint = findViewById<Button>(R.id.button4)
        var hintCount: Int = 0
        btnHint.setOnClickListener {
            if (hintCount == 0){

                hintCount += 1
                val solver = MazeSolver(mazeData.toTypedArray(), mazeSize, mazeSize)

                val x : Int = location / mazeSize
                val y : Int = location % mazeSize
                val nextPosition = solver.findPathFrom(Position(x, y))
                val nextX = nextPosition?.x
                val nextY = nextPosition?.y
                val newLocation = nextX!!*mazeSize+nextY!!
                val gridView = findViewById<GridView>(R.id.gridView)
                val HintView = gridView.getChildAt(newLocation)
                if (HintView != null) {
                    val imageView2 =
                        HintView.findViewById<ImageView>(R.id.imageView2)
                    imageView2.setImageResource(R.drawable.hint)
                }

            } else {

            }

        }







    }

    private fun rotateImageLeft(imageView: ImageView) {
        val originalBitmap = (imageView.drawable as BitmapDrawable).bitmap
        val matrix = Matrix()
        matrix.setRotate(-90f)
        val rotatedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.width, originalBitmap.height, matrix, true)
        imageView.setImageBitmap(rotatedBitmap)
    }

    private fun rotateImageRight(imageView: ImageView) {
        val originalBitmap = (imageView.drawable as BitmapDrawable).bitmap
        val matrix = Matrix()
        matrix.setRotate(90f)
        val rotatedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.width, originalBitmap.height, matrix, true)
        imageView.setImageBitmap(rotatedBitmap)
    }

    private fun rotateImageDown(imageView: ImageView) {
        val originalBitmap = (imageView.drawable as BitmapDrawable).bitmap
        val matrix = Matrix()
        matrix.setRotate(180f)
        val rotatedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, originalBitmap.width, originalBitmap.height, matrix, true)
        imageView.setImageBitmap(rotatedBitmap)
    }


}


class MazeAdapter(val context: Context, val mazeCells: ArrayList<Int>, val mazeSize: Int) : BaseAdapter() {

    override fun getCount(): Int {
        return mazeCells.size
    }

    override fun getItem(p0: Int): Any {
        return mazeCells.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getView(p0: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.maze_cell, null)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val marginParams = imageView.layoutParams as ViewGroup.MarginLayoutParams

        val value = getItem(p0) as Int
        val marginDp = dpToPx(3F)

        val binaryIntArray = decimalToBinaryArray(value, 4).reversedArray()
        val topMargin = if (binaryIntArray[0] == 1) marginDp else 0
        val leftMargin = if (binaryIntArray[1] == 1) marginDp else 0
        val bottomMargin = if (binaryIntArray[2] == 1) marginDp else 0
        val rightMargin = if (binaryIntArray[3] == 1) marginDp else 0

        marginParams.topMargin = topMargin
        marginParams.rightMargin = rightMargin
        marginParams.leftMargin = leftMargin
        marginParams.bottomMargin = bottomMargin

        val cellsize = 350f/mazeSize

        val sizeInDp = dpToPx(cellsize)
        marginParams.width = sizeInDp - marginParams.leftMargin - marginParams.rightMargin
        marginParams.height = sizeInDp - marginParams.topMargin - marginParams.bottomMargin
        imageView.layoutParams = marginParams
        //view.layoutParams = layoutParams

        return view
    }

    private fun dpToPx(dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        val size = dp * scale
        return size.toInt()

    }

    private fun decimalToBinaryArray(decimal: Int, size: Int): IntArray {
        val binaryArray = IntArray(size){0}
        var value = decimal
        for (i in 0 until size) {
            binaryArray[i] = value % 2
            value /= 2
        }
        return binaryArray
    }


}


data class Position(val x: Int, val y: Int)
data class Node(val position: Position, val path: List<Position>)

class MazeSolver(private val mazeData: Array<Int>, private val rows: Int, private val cols: Int) {
    private val directions = arrayOf(
        Position(0, 1),  // 오른쪽
        Position(1, 0),  // 아래쪽
        Position(0, -1), // 왼쪽
        Position(-1, 0)  // 위쪽
    )

    private fun isWall(value: Int, direction: Int): Boolean {
        return (value and (1 shl direction)) != 0
    }

    private fun isValid(x: Int, y: Int): Boolean {
        return x in 0 until rows && y in 0 until cols
    }

    fun findPathFrom(startPosition: Position): Position? {
        val visited = Array(rows) { BooleanArray(cols) }
        val queue = LinkedList<Node>()
        val endPosition = Position(rows-1, cols-1)

        queue.add(Node(startPosition, emptyList()))

        while (queue.isNotEmpty()) {
            val current = queue.poll()
            val position = current.position
            val x = position.x
            val y = position.y

            if (position == endPosition) {
                return current.path.getOrNull(1)  // 현재 위치의 바로 다음 위치 반환
            }

            if (visited[x][y]) continue
            visited[x][y] = true

            for (i in directions.indices) {
                val newX = x + directions[i].x
                val newY = y + directions[i].y
                if (isValid(newX, newY) && !isWall(mazeData[x * cols + y], i) && !visited[newX][newY]) {
                    queue.add(Node(Position(newX, newY), current.path + position))
                }
            }
        }

        return null
    }
}

