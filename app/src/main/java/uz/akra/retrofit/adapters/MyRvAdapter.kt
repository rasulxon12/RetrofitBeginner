package uz.akra.retrofit.adapters

import android.icu.text.Transliterator.Position
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.akra.retrofit.R
import uz.akra.retrofit.databinding.ItemRvBinding
import uz.akra.retrofit.models.MyTodo


class MyRvAdapter(var list: List<MyTodo>, val rvAction: RvAction) :
    RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myTodo: MyTodo, position: Int) {
            itemRvBinding.tvName.text = myTodo.sarlavha
            itemRvBinding.tvCondition.text = myTodo.holat
            itemRvBinding.tvDeadline.text = myTodo.oxirgi_muddat
            itemRvBinding.tvText.text = myTodo.matn


            itemRvBinding.root.setOnClickListener {
                rvAction.onClickUpdate(myTodo, position)
            }

            itemRvBinding.root.setOnLongClickListener {
                rvAction.longClickDelete(myTodo, position)

                true
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

    interface RvAction {
        fun onClickUpdate(myTodo: MyTodo, position: Int)


        fun longClickDelete(myTodo: MyTodo, position: Int)
    }


}