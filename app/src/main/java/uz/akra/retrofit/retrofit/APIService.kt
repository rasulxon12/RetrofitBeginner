package uz.akra.retrofit.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import uz.akra.retrofit.models.MyPost
import uz.akra.retrofit.models.MyTodo

interface APIService {

    @GET("plan")
    fun getAllTodo(): Call<List<MyTodo>>

    @POST("plan/")
    fun postMyTodo(@Body myPost: MyPost): Call<MyTodo>

    @PUT("plan/{id}/")
    fun updateMyTodo(@Path("id") id: Int, @Body myPost: MyPost): Call<MyTodo>

    @DELETE("plan/{id}/")
    fun deleteMyTodo(@Path("id") id: Int): Call<Any>


}



