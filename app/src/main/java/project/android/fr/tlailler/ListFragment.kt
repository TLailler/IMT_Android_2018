package project.android.fr.tlailler

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class ListFragment : Fragment() {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter

    private var listener : RecyclerAdapter.BookRecyclerListener? = null

    companion object {


    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        when(context){
            is RecyclerAdapter.BookRecyclerListener -> listener = context
            else -> throw Exception("bad listener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.book_list, container, false)

        val retrofit = Retrofit.Builder().baseUrl("http://henri-potier.xebia.fr/").addConverterFactory(GsonConverterFactory.create()).build()
        val api = retrofit.create(Api::class.java)
        val books = api.listBooks()

        val recyclerView = view.findViewById<RecyclerView>(R.id.bookRecyclerView)
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager


        books.enqueue(object : Callback<Array<Book>> {

            override fun onFailure(call: Call<Array<Book>>, t: Throwable) {

            }

            override fun onResponse(call: Call<Array<Book>>,
                                    response: Response<Array<Book>>){
                adapter = RecyclerAdapter(response.body()!!, listener!!)
                recyclerView.adapter = adapter
            }
        })

        return view
    }
}