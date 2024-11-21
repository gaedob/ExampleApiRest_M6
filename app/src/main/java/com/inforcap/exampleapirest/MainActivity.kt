package com.inforcap.exampleapirest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.inforcap.exampleapirest.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var postList: ArrayList<PostEntity> = arrayListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initRecyclerView()
        loadApiData()
        borrar()

    }
    private fun borrar() {
        lifecycleScope.launch(Dispatchers.IO) {
            loadApiData2()
        }

    }

    suspend fun loadApiData2() : Unit{
        Log.d("INITTTT"," -------------------")
        val apiService = RetrofitClient.retrofitInstancex().create(ApiService::class.java)
        try {
            val response = apiService.getAll("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3MzIxNTcyMDgsImV4cCI6MTczMjE2MDgwOH0.UXUQModqdqe_6XixoT_7nZiLFi_ZGsjNXXlp5zH2ShI")
            println("Response: $response")
            Log.d("INITTTT - Response", "${response}")

        } catch (e: Exception) {

            Log.d("INITTTT - ERROR", "${ e.printStackTrace()}")
            e.printStackTrace()

        }

    }

    private fun initRecyclerView() {
        adapter = PostAdapter(postList)
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = adapter
    }

    private fun loadApiData() {
        val service = RetrofitClient.retrofitInstance()
        val call = service.getAllPost()
        call.enqueue(object: Callback<ArrayList<PostEntity>> {

            override fun onResponse(
                call: Call<ArrayList<PostEntity>>,
                response: Response<ArrayList<PostEntity>>
            ) {
                response.body()?.map {
                    Log.d("MAIN_API","${it.id} ${it.title}")
                    postList.add(it)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<PostEntity>>, t: Throwable) {
                Toast.makeText(applicationContext,"Error: No logramos establecer conexión con los datos", Toast.LENGTH_SHORT).show()
            }
        })
    }

}