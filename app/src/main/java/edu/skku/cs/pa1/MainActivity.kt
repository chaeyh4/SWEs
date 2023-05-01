package edu.skku.cs.pa1

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream: InputStream = applicationContext.assets.open("wordle_words.txt")
        val list = inputStream.readBytes().toString(Charsets.UTF_8)
        val wordList = list.trim().split("\n") //\n을 기준으로 분리
        val wordListnew1 = wordList.map { it.trim() } //공백 제거
        val wordListnew2 = wordListnew1.map { it.uppercase() } //대문자로 변환

        val wordArray = list.split("\n").toTypedArray()
        val answer1 = wordArray.random() //random으로 answer 단어 설정
        val answer = answer1.uppercase() //대문자로 변환
        val answerList = answer.toCharArray().toList() //Character로 분리하여 List에 저장

        val ansletter0 = answerList[0].toString() //첫번째 철자를 String으로 저장
        val ansletter1 = answerList[1].toString() //두번째
        val ansletter2 = answerList[2].toString() //세번째
        val ansletter3 = answerList[3].toString() //네번째
        val ansletter4 = answerList[4].toString() //다섯번째

        val tryTextList = arrayListOf<Trytext>() //입력한 값을 저장할 List
        val ansTextList = arrayListOf<Answertext>() //정답을 저장할 List
        val grayTextList = arrayListOf<GrayText>() //Gray List에 저장할 List
        val yellowTextList = arrayListOf<YellowText>() //Yellow List에 저장할 List
        val greenTextList = arrayListOf<GreenText>() //Green List에 저장할 List

        ansTextList.add(Answertext(ansletter0,ansletter1,ansletter2,ansletter3,ansletter4)) //List에 정답인 단어의 철자를 각각 저장함

        val btn = findViewById<Button>(R.id.button) //Submit 버튼
        btn.setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            val text1 = editText.text.toString()
            val text = text1.uppercase() //대문자로 변환

            if (wordListnew2.contains(text)) {

                //단어가 존재할 때 -> if-else문 이후 코드를 실행함

            } else { //단어가 존재하지 않을 때
                Toast.makeText(applicationContext, "Word '${text1}' not in dictionary!", Toast.LENGTH_LONG).show()
                editText.text.clear()
                return@setOnClickListener; //다음 코드 실행하지 않기 위함
            }

            val charList = text.toCharArray().toList() //입력한 단어를 철자로 나누어서 List에 저장

            val letter0 = charList[0].toString() //첫번째 철자
            val letter1 = charList[1].toString() //두번째
            val letter2 = charList[2].toString() //세번째
            val letter3 = charList[3].toString() //네번째
            val letter4 = charList[4].toString() //다섯번째

            tryTextList.add(Trytext(letter0,letter1,letter2,letter3,letter4)) //입력한 단어들을 저장하는 list에 추가

            val listAdapterText = CustomAdapter1(this, tryTextList, ansTextList)
            val mainList = findViewById<ListView>(R.id.listview)
            mainList.adapter = listAdapterText

            //첫번째 철자에 대하여

            if (letter0 == ansletter0){ //green listview에 넣을 철자 선정
                for (i in yellowTextList.indices) {
                    if (yellowTextList[i].yellowletter == letter0) {
                        yellowTextList.removeAt(i)
                        break
                    }
                }

                if (!greenTextList.any { it.greenletter == letter0 }) {
                    greenTextList.add(GreenText(letter0))
                }

            } else if(letter0 == ansletter1 || letter0 == ansletter2 || letter0 == ansletter3 || letter0 == ansletter4) { //yellow listview에 넣을 철자 설정
                if (!yellowTextList.any { it.yellowletter == letter0 } && !greenTextList.any { it.greenletter == letter0 }) {
                    yellowTextList.add(YellowText(letter0))
                }

            } else { //gray listview에 넣을 철자 설정
                if (!grayTextList.any { it.grayletter == letter0 }) {
                    grayTextList.add(GrayText(letter0))
                }

            }

            //두번째 철자에 대하여

            if (letter1 == ansletter1){
                for (i in yellowTextList.indices) {
                    if (yellowTextList[i].yellowletter == letter1) {
                        yellowTextList.removeAt(i)
                        break
                    }
                }
                if (!greenTextList.any { it.greenletter == letter1 }) {
                    greenTextList.add(GreenText(letter1))
                }

            } else if(letter1 == ansletter0 || letter1 == ansletter2 || letter1 == ansletter3 || letter1 == ansletter4) {
                if (!yellowTextList.any { it.yellowletter == letter1 } && !greenTextList.any { it.greenletter == letter1 }) {
                    yellowTextList.add(YellowText(letter1))
                }
            } else {
                if (!grayTextList.any { it.grayletter == letter1 }) {
                    grayTextList.add(GrayText(letter1))
                }
            }

            //세번째 철자에 대하여

            if (letter2 == ansletter2){
                for (i in yellowTextList.indices) {
                    if (yellowTextList[i].yellowletter == letter2) {
                        yellowTextList.removeAt(i)
                        break
                    }
                }
                if (!greenTextList.any { it.greenletter == letter2 }) {
                    greenTextList.add(GreenText(letter2))
                }

            } else if(letter2 == ansletter0 || letter2 == ansletter1 || letter2 == ansletter3 || letter2 == ansletter4) {
                if (!yellowTextList.any { it.yellowletter == letter2 } && !greenTextList.any { it.greenletter == letter2 }) {
                    yellowTextList.add(YellowText(letter2))
                }
            } else {
                if (!grayTextList.any { it.grayletter == letter2 }) {
                    grayTextList.add(GrayText(letter2))
                }
            }

            //네번째 철자에 대하여

            if (letter3 == ansletter3){
                for (i in yellowTextList.indices) {
                    if (yellowTextList[i].yellowletter == letter3) {
                        yellowTextList.removeAt(i)
                        break
                    }
                }
                if (!greenTextList.any { it.greenletter == letter3 }) {
                    greenTextList.add(GreenText(letter3))
                }

            } else if(letter3 == ansletter0 || letter3 == ansletter1 || letter3 == ansletter2 || letter3 == ansletter4) {
                if (!yellowTextList.any { it.yellowletter == letter3 } && !greenTextList.any { it.greenletter == letter3 }) {
                    yellowTextList.add(YellowText(letter3))
                }
            } else {
                if (!grayTextList.any { it.grayletter == letter3 }) {
                    grayTextList.add(GrayText(letter3))
                }
            }

            //다섯번째 철자에 대하여

            if (letter4 == ansletter4){
                for (i in yellowTextList.indices) {
                    if (yellowTextList[i].yellowletter == letter4) {
                        yellowTextList.removeAt(i)
                        break
                    }
                }
                if (!greenTextList.any { it.greenletter == letter4 }) {
                    greenTextList.add(GreenText(letter4))
                }
            } else if(letter4 == ansletter0 || letter4 == ansletter1 || letter4 == ansletter2 || letter4 == ansletter3) {
                if (!yellowTextList.any { it.yellowletter == letter4 } && !greenTextList.any { it.greenletter == letter4 }) {
                    yellowTextList.add(YellowText(letter4))
                }
            } else {
                if (!grayTextList.any { it.grayletter == letter4 }) {
                    grayTextList.add(GrayText(letter4))
                }
            }

            //green listview를 위한 adapter

            val letter3AdapterText = CustomAdapter2(this,greenTextList)
            val letterList3 = findViewById<ListView>(R.id.listview3)
            letterList3.adapter = letter3AdapterText

            //yellow listview를 위한 adapter

            val letter2AdapterText = CustomAdapter3(this,yellowTextList)
            val letterList2 = findViewById<ListView>(R.id.listview2)
            letterList2.adapter = letter2AdapterText

            //gray listview를 위한 adapter

            val letter1AdapterText = CustomAdapter4(this,grayTextList)
            val letterList1 = findViewById<ListView>(R.id.listview1)
            letterList1.adapter = letter1AdapterText

            editText.text.clear() //submit 버튼을 눌렀을 때 edittext clear
        }
    }
}

class Trytext(val letter0: String, val letter1: String, val letter2: String, val letter3: String, val letter4: String) //edittext에 입력한 단어
class Answertext(val ansletter0: String, val ansletter1: String, val ansletter2: String, val ansletter3: String, val ansletter4: String ) //answer로 지정된 단어

//Main Listview에 표시하기 위한 Adapter
class CustomAdapter1(val context: Context, val items: ArrayList<Trytext>, val ansitem: ArrayList<Answertext>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(i:Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.sub_layout, null)

        //textView
        val textView1 = view.findViewById<TextView>(R.id.textView1)
        val textView2 = view.findViewById<TextView>(R.id.textView2)
        val textView3 = view.findViewById<TextView>(R.id.textView3)
        val textView4 = view.findViewById<TextView>(R.id.textView4)
        val textView5 = view.findViewById<TextView>(R.id.textView5)

        //textview의 text를 입력한 단어의 letter로 설정
        textView1.text = items[i].letter0
        textView2.text = items[i].letter1
        textView3.text = items[i].letter2
        textView4.text = items[i].letter3
        textView5.text = items[i].letter4

        //정답 단어의 철자
        val ansletter0 = ansitem[0].ansletter0
        val ansletter1 = ansitem[0].ansletter1
        val ansletter2 = ansitem[0].ansletter2
        val ansletter3 = ansitem[0].ansletter3
        val ansletter4 = ansitem[0].ansletter4

        //글자와 순서가 모두 맞을 경우: 초록
        //글자가 존재할 경우: 노랑
        //글자가 없을 경우: 회색

        //첫번째 철자
        if (textView1.text == ansletter0){
            textView1.setBackgroundColor(Color.parseColor("#99F691"))
            textView1.setTextColor(Color.parseColor("#000000"))
        } else if (textView1.text == ansletter1 || textView1.text == ansletter2 || textView1.text == ansletter3 || textView1.text == ansletter4){
            textView1.setBackgroundColor(Color.parseColor("#FFE46F"))
            textView1.setTextColor(Color.parseColor("#000000"))
        } else {
            textView1.setBackgroundColor(Color.parseColor("#787C7E"))
            textView1.setTextColor(Color.parseColor("#FFFFFF"))
        }

        //두번째 철자
        if (textView2.text == ansletter1){
            textView2.setBackgroundColor(Color.parseColor("#99F691"))
            textView2.setTextColor(Color.parseColor("#000000"))
        } else if (textView2.text == ansletter0 || textView2.text == ansletter2 || textView2.text == ansletter3 || textView2.text == ansletter4){
            textView2.setBackgroundColor(Color.parseColor("#FFE46F"))
            textView2.setTextColor(Color.parseColor("#000000"))
        } else {
            textView2.setBackgroundColor(Color.parseColor("#787C7E"))
            textView2.setTextColor(Color.parseColor("#FFFFFF"))
        }

        //세번째 철자
        if (textView3.text == ansletter2){
            textView3.setBackgroundColor(Color.parseColor("#99F691"))
            textView3.setTextColor(Color.parseColor("#000000"))
        } else if (textView3.text == ansletter0 || textView3.text == ansletter1 || textView3.text == ansletter3 || textView3.text == ansletter4){
            textView3.setBackgroundColor(Color.parseColor("#FFE46F"))
            textView3.setTextColor(Color.parseColor("#000000"))
        } else {
            textView3.setBackgroundColor(Color.parseColor("#787C7E"))
            textView3.setTextColor(Color.parseColor("#FFFFFF"))
        }

        //네번째 철자
        if (textView4.text == ansletter3){
            textView4.setBackgroundColor(Color.parseColor("#99F691"))
            textView4.setTextColor(Color.parseColor("#000000"))
        } else if (textView4.text == ansletter0 || textView4.text == ansletter1 || textView4.text == ansletter2 || textView4.text == ansletter4){
            textView4.setBackgroundColor(Color.parseColor("#FFE46F"))
            textView4.setTextColor(Color.parseColor("#000000"))
        } else {
            textView4.setBackgroundColor(Color.parseColor("#787C7E"))
            textView4.setTextColor(Color.parseColor("#FFFFFF"))
        }

        //다섯번째 철자
        if (textView5.text == ansletter4){
            textView5.setBackgroundColor(Color.parseColor("#99F691"))
            textView5.setTextColor(Color.parseColor("#000000"))
        } else if (textView5.text == ansletter0 || textView5.text == ansletter1 || textView5.text == ansletter2 || textView5.text == ansletter3){
            textView5.setBackgroundColor(Color.parseColor("#FFE46F"))
            textView5.setTextColor(Color.parseColor("#000000"))
        } else {
            textView5.setBackgroundColor(Color.parseColor("#787C7E"))
            textView5.setTextColor(Color.parseColor("#FFFFFF"))
        }

        return view
    }

}

//green ListView를 위한 Adapter
class GreenText(val greenletter: String)
class CustomAdapter2(val context: Context, val items: ArrayList<GreenText>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(i:Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.letter_list, null)

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.setBackgroundColor(Color.parseColor("#99F691"))
        textView.setTextColor(Color.parseColor("#000000"))

        textView.text = items[i].greenletter

        return view
    }
}

//Yellow Listview를 위한 Adapter
class YellowText(val yellowletter: String)
class CustomAdapter3(val context: Context, val items: ArrayList<YellowText>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(i:Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.letter_list, null)

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.setBackgroundColor(Color.parseColor("#FFE46F"))
        textView.setTextColor(Color.parseColor("#000000"))

        textView.text = items[i].yellowletter

        return view
    }
}

//Gray Listview를 위한 Adapter
class GrayText(val grayletter: String)
class CustomAdapter4(val context: Context, val items: ArrayList<GrayText>) : BaseAdapter() {

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(i:Int, cvtView: View?, parent: ViewGroup?): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.letter_list, null)

        val textView = view.findViewById<TextView>(R.id.textView)
        textView.setBackgroundColor(Color.parseColor("#787C7E"))
        textView.setTextColor(Color.parseColor("#FFFFFF"))

        textView.text = items[i].grayletter

        return view
    }
}