package com.lakhai.petprojectapplicationlakhai.data.recipes.ingredientsrecipe.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.lakhai.petprojectapplicationlakhai.R

class IngredientAdapter(val c: Context,val ingredientList:ArrayList<String>) : RecyclerView.Adapter<IngredientAdapter.ingredientViewHolder>() {
    inner class ingredientViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val ingredient=v.findViewById<TextView>(R.id.nameTv)
       var ivDelete: ImageView = v.findViewById(R.id.addInnerButton)

        init{
            ivDelete.setOnClickListener {
                ingredientList.removeAt(adapterPosition)
                notifyDataSetChanged()
               Toast.makeText(c, "Deleted", Toast.LENGTH_SHORT).show()
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ingredientViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val v=inflater.inflate(R.layout.ingredient_item,parent,false)
        return ingredientViewHolder(v)
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: ingredientViewHolder, position: Int) {
    val newList=ingredientList[position]
        holder.ingredient.text=newList

    }
}