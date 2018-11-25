package project.android.fr.tlailler

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_view.view.*


class BookDetailsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.book_details, container, false)

        val bundle : Bundle = getArguments()!!
        val book : Book = bundle.getParcelable("Book")

        val title = view.findViewById<TextView>(R.id.bookTitle)
        title.text = book.title

        val img = view.findViewById<ImageView>(R.id.bookImage)
        Picasso.get().load(book.cover).into(img)

        val desc = view.findViewById<TextView>(R.id.bookDescription)
        desc.text = book.synopsis[0]

        return view
    }
}