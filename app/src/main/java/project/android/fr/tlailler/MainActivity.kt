package project.android.fr.tlailler

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), RecyclerAdapter.BookRecyclerListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.containerFrameLayout, ListFragment(), ListFragment::class.java.name).commit()

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            supportFragmentManager.beginTransaction().replace(R.id.containerFrameLayout2, ListFragment(), ListFragment::class.java.name).commit()
        }

    }

    override fun onNext(book:Book) {

        val bundle = Bundle()
        bundle.putParcelable("Book",book)

        val details = BookDetailsFragment()
        details.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.containerFrameLayout, details, BookDetailsFragment::class.java.name).addToBackStack(ListFragment::class.java.name).commit()

    }
}