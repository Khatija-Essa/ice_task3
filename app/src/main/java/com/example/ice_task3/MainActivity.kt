package com.example.ice_task3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var fetchDataButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchDataButton = findViewById(R.id.fetchDataButton)
        resultTextView = findViewById(R.id.resultTextView)

        fetchDataButton.setOnClickListener {
            fetchDataFromApi()
        }
    }

    private fun fetchDataFromApi() {
        val call = RetrofitClient.instance.getData()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    apiResponse?.let {
                        resultTextView.text = it.message
                    }
                } else {
                    resultTextView.text = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                resultTextView.text = "API call failed: ${t.message}"
            }
        })
    }
}