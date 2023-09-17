package com.lakhai.petprojectapplicationlakhai.data.chosen

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.datastore.ChosenRecipesDS

@Suppress("UNCHECKED_CAST")
class ChosenAdapter(private val favoriteList: ArrayList<ChosenModel>, val context: Context) :
    RecyclerView.Adapter<ChosenAdapter.ChosenViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChosenViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.chosen_item, parent, false)
        return ChosenViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChosenViewHolder, position: Int) {
        val currentItem = favoriteList[position]
        Glide.with(context)
            .load(currentItem.image)
            .fitCenter()
            .circleCrop()
            .into(holder.imageOfFood)
        holder.textViewName.text = currentItem.name

    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    inner class ChosenViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageOfFood: ImageView = itemView.findViewById(R.id.foodImageView)
        val textViewName: TextView = itemView.findViewById(R.id.nameOfFavoriteTv)
        val deleteFood: ImageView = itemView.findViewById(R.id.deleterButton)

        init {
            deleteFood.setOnClickListener {
                val position: Int = adapterPosition

                val myList = ChosenRecipesDS().getSharedPreferenceStringList(context, "key")
                val listToGo = myList!!.toMutableList()

                for (i in listToGo.indices) {
                    if (listToGo[i].toString().toInt() == favoriteList[position].id) {
                        listToGo.removeAt(i)

                        val newList=listToGo.map { it?.toInt()  }.toList()
                        ChosenRecipesDS().setSharedPreferenceStringList(
                            context,"key",
                            newList.toList() as List<Int>
                        )
                        break
                    }
                }
                favoriteList.removeAt(position)
                notifyDataSetChanged()
            }
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val idToPass = favoriteList[position].id
                Log.d("dfss", "my id is $idToPass")
            }
        }
    }
}