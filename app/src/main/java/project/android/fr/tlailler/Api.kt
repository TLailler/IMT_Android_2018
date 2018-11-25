package project.android.fr.tlailler

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("books")
    fun listBooks(): Call<Array<Book>>
}
