package edu.skku.cs.pa2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class SignInActivity : AppCompatActivity() {
    data class DataModel(var username: String ?= null)
    data class Success(var success: Boolean ?= null)

    companion object{
        const val EXT_USER = "extra_user_name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val btn = findViewById<Button>(R.id.button)
        val userEditText = findViewById<EditText>(R.id.editTextTextPersonName)
        val client = OkHttpClient()
        val urlpath = "http://swui.skku.edu:1399/users"


        btn.setOnClickListener {
            val user = userEditText.text.toString()
            val json = Gson().toJson(DataModel(user))
            val mediaType = "application/json; charset=utf-8".toMediaType()
            val req = Request.Builder().url(urlpath).post(json.toRequestBody(mediaType)).build()

            client.newCall(req).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    // Handle request failure
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        val str = response.body!!.string()
                        val responseData = Gson().fromJson(str, Success::class.java)
                        val responseResult = responseData.success.toString()

                        CoroutineScope(Dispatchers.Main).launch {
                            if (responseResult == "false"){
                                Toast.makeText(this@SignInActivity,"Wrong User Name",Toast.LENGTH_SHORT).show()
                            } else {
                                val intent = Intent(this@SignInActivity, MazeSelectionActivity::class.java).apply{
                                    putExtra(EXT_USER, user)
                                }
                                startActivity(intent)

                            }

                        }
                    }
                }
            })
        }
    }
}
