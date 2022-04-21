package com.example.roomgetsimpleexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.Article
import com.example.roomexample.ArticleDatabase
import com.example.roomget.data.NewService
import com.example.roomget.data.News
import com.example.roomgetsimpleexample.adapter.NewsAdapter
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object DB{
    var dataList = mutableListOf<Article>()
}

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
    lateinit var newsList: RecyclerView
    lateinit var db: ArticleDatabase

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = ArticleDatabase.getDatabase(this@MainActivity)
        newsList = findViewById(R.id.newsList)

        GlobalScope.launch(Dispatchers.IO) {
            getNews()
        }
    }

    fun getNews() {

        val news = NewService.newInstance.getHeadLines(1)

        news.enqueue(object : Callback<News> {

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("CODE", "Error")
            }


            override fun onResponse(
                call: Call<News>,
                response: Response<News>
            ) {
                val news: News? = response.body()


                news!!.articles.forEach {
                    Log.d("CODE", "${it}")
                    CoroutineScope(Dispatchers.IO).launch {
                       // val a = db.artDao().getAll().toMutableList()
                            if ( !(db.artDao().isNotExists(it.title) ) )
                                db.artDao().insertAll( it )
                    }//
                }
            }
        })
    }//getNews

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            DB.dataList += (db.artDao().getAll()).toMutableList()
        }

        adapter = NewsAdapter(this@MainActivity, DB.dataList)
        newsList.adapter = adapter
        newsList.layoutManager = LinearLayoutManager(this@MainActivity)

    }
}
fun check (list1:MutableList<Article>):Boolean{
    for (i in  0 until list1.size )
        for (j in 0 until list1.size)
            if (i!=j && list1[i] == list1[j])
                return true
    return false
}