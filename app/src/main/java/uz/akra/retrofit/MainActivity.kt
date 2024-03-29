package uz.akra.retrofit

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.akra.retrofit.adapters.MyRvAdapter
import uz.akra.retrofit.databinding.ActivityMainBinding
import uz.akra.retrofit.databinding.ItemDialogBinding
import uz.akra.retrofit.models.MyPost
import uz.akra.retrofit.models.MyTodo
import uz.akra.retrofit.retrofit.APIClient

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), MyRvAdapter.RvAction {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var myRvAdapter: MyRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        APIClient.apiService.getAllTodo()
            .enqueue(object : Callback<List<MyTodo>> {
                override fun onResponse(
                    call: Call<List<MyTodo>>,
                    response: Response<List<MyTodo>>
                ) {
                    if (response.isSuccessful) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        val list = response.body()
                        list?.forEach {
                            Log.d(TAG, "onResponse: $it")
                        }
                        binding.myPrBar.visibility = View.GONE
                        myRvAdapter = MyRvAdapter(list!!, this@MainActivity)
                        binding.myContainer.adapter = myRvAdapter

                    }


                }

                override fun onFailure(call: Call<List<MyTodo>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Hatolikka tushdi", Toast.LENGTH_SHORT).show()

                }
            })

        binding.btnUpdate.setOnClickListener {
            APIClient.apiService.getAllTodo()
                .enqueue(object : Callback<List<MyTodo>> {
                    override fun onResponse(
                        call: Call<List<MyTodo>>,
                        response: Response<List<MyTodo>>
                    ) {
                        if (response.isSuccessful) {
                            Log.d(TAG, "onResponse: ${response.body()}")
                            val list = response.body()
                            list?.forEach {
                                Log.d(TAG, "onResponse: $it")
                            }
                            binding.myPrBar.visibility = View.GONE
                            myRvAdapter = MyRvAdapter(list!!, this@MainActivity)
                            binding.myContainer.adapter = myRvAdapter

                        }


                    }

                    override fun onFailure(call: Call<List<MyTodo>>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Hatolikka tushdi", Toast.LENGTH_SHORT)
                            .show()

                    }
                })

            myRvAdapter.notifyDataSetChanged()
        }






        binding.btnAdd.setOnClickListener {
            val dialog = AlertDialog.Builder(this).create()
            val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)

            itemDialogBinding.btnSave.setOnClickListener {
                val myPost = MyPost(
                    itemDialogBinding.edtCondition.text.toString(),
                    itemDialogBinding.edtText.text.toString(),
                    itemDialogBinding.edtDeadline.text.toString(),
                    itemDialogBinding.edtName.text.toString()
                )
                APIClient.apiService.postMyTodo(myPost).enqueue(object : Callback<MyTodo> {
                    override fun onResponse(call: Call<MyTodo>, response: Response<MyTodo>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@MainActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                            dialog.cancel()
                        }
                    }

                    override fun onFailure(call: Call<MyTodo>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            "Saqlashda hatolikka tushdi",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }

            dialog.setView(itemDialogBinding.root)
            dialog.show()

        }


    }

    override fun onClickUpdate(myTodo: MyTodo, position: Int) {
        val dialog = AlertDialog.Builder(this).create()
        val itemDialogBinding = ItemDialogBinding.inflate(layoutInflater)
        itemDialogBinding.edtName.setText(myTodo.sarlavha)
        itemDialogBinding.edtText.setText(myTodo.matn)
        itemDialogBinding.edtDeadline.setText(myTodo.oxirgi_muddat)
        itemDialogBinding.edtCondition.setText(myTodo.holat)

        itemDialogBinding.btnSave.setOnClickListener {
            val myPost = MyPost(
                itemDialogBinding.edtCondition.text.toString(),
                itemDialogBinding.edtText.text.toString(),
                itemDialogBinding.edtDeadline.text.toString(),
                itemDialogBinding.edtName.text.toString()
            )

            APIClient.apiService.updateMyTodo(myTodo.id, myPost).enqueue(object : Callback<MyTodo> {
                override fun onResponse(call: Call<MyTodo>, response: Response<MyTodo>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@MainActivity, "Yanglandi", Toast.LENGTH_SHORT).show()
                        dialog.cancel()
                    }

                }

                override fun onFailure(call: Call<MyTodo>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Yangilashda hatolikka tushdi",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })


        }

        dialog.setView(itemDialogBinding.root)
        dialog.show()


    }

    override fun longClickDelete(myTodo: MyTodo, position: Int) {
        APIClient.apiService.deleteMyTodo(myTodo.id).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "O'chirildi", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "O'chirishda hatolikka tushdi",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }


}
