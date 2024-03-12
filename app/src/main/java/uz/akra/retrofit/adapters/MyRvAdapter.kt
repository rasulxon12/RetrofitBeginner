package uz.akra.retrofit.adapters

import android.icu.text.Transliterator.Position
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.akra.retrofit.databinding.ItemRvBinding
import uz.akra.retrofit.models.MyTodo


class MyRvAdapter(var list: List<MyTodo>, val rvAction: RvAction):RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding):
            RecyclerView.ViewHolder(itemRvBinding.root){

                fun onBind(myTodo: MyTodo, position: Int){
                    itemRvBinding.tvName.text = myTodo.sarlavha
                    itemRvBinding.tvCondition.text = myTodo.holat
                    itemRvBinding.tvDeadline.text = myTodo.oxirgi_muddat
                    itemRvBinding.tvText.text = myTodo.matn


                    itemRvBinding.root.setOnClickListener {
                        rvAction.onClick(myTodo, position)
                    }

//                    itemRvBinding.btnLike.setOnClickListener {
//                        rvAction.onClickLiked(myCurrency, position)
//                    }
//
//                    if (myCurrency.liked == 1){
//                        itemRvBinding.btnLike.setImageResource(R.drawable.ic_heart)
//                    }else{
//                        itemRvBinding.btnLike.setImageResource(R.drawable.ic_heartt)
//                    }

                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int  = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

//        val currentCurrency = list[position]
//        // Ma'lumotlarni ViewHolder ga joylash
//        holder.itemRvBinding.tvUzbsom.text = currentCurrency.Rate.toString()
    }

    interface RvAction{
        fun onClick(myTodo: MyTodo, position: Int)
//        fun onClickLiked(myCurrency: MyCurrency, position: Int)
    }

}