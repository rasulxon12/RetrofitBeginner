package uz.akra.retrofit.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import uz.akra.retrofit.models.MyPost
import uz.akra.retrofit.models.MyTodo

interface APIService {

    @GET("plan")
    fun getAllTodo():Call<List<MyTodo>>

    @POST("plan/")
    fun postMyTodo(@Body myPost: MyPost):Call<MyTodo>

}