package project.android.fr.tlailler

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_view.view.*

class RecyclerAdapter( private val books: Array<Book>, val listener : BookRecyclerListener) : RecyclerView.Adapter<RecyclerAdapter.BookHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.BookHolder {
        val inflatedView = parent.inflate(R.layout.book_view, false)
        return BookHolder(inflatedView)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: RecyclerAdapter.BookHolder, position: Int) {
        val itemBook = books[position]
        holder.bindBook(itemBook, listener)

    }


    class BookHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v
        private var book: Book? = null

        init {
        }

        companion object {
            private val BOOK_KEY = "BOOK"
        }

        fun bindBook(book: Book, listener: BookRecyclerListener) {
            this.book = book
            Picasso.get().load(book.cover).into(view.itemImage)
            view.itemTitle.text = book.title
            //view.itemDescription.text = book.synopsis[0]

            view.setOnClickListener {

                listener.onNext(book)
            }
        }
    }

    interface BookRecyclerListener{
        fun onNext(book:Book)
    }
}