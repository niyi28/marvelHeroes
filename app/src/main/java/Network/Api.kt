package Network

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("quotes")
    fun getSuperHeroes(): Call<List<Heroes>>
    
}