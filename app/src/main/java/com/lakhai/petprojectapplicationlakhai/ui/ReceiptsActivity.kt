package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.recipes.ingredientsrecipe.recyclerview.IngredientAdapter
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityMainBinding
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityReceiptsBinding

class ReceiptsActivity : AppCompatActivity() {
    private var _binding: ActivityReceiptsBinding? = null
    private val binding get() = _binding!!
    lateinit var ingredientsAdapter: IngredientAdapter
    lateinit var ingredientList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityReceiptsBinding.inflate(layoutInflater)
        binding.backButton.setOnClickListener {
            val activityEndIntent = Intent(this@ReceiptsActivity, MenuActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }
        binding.plusButton.setOnClickListener {
            addInfo()
        }
        ingredientList = ArrayList()

        ingredientsAdapter = IngredientAdapter(this, ingredientList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.recyclerView.adapter = ingredientsAdapter
        setContentView(binding.root)
        binding.doneButton.setOnClickListener {
            if (ingredientList.size == 0) {
                val activityEndIntent =
                    Intent(this@ReceiptsActivity, RandomrecipeActivity::class.java)
                startActivity(activityEndIntent)
                finish()
            } else {
                val activityEndIntent =
                    Intent(this@ReceiptsActivity, GettedreceiptActivity::class.java)
                var ingredientsToPass = ""
                for (i in ingredientList.indices) {
                    ingredientsToPass = ingredientsToPass + ingredientList[i] + ","
                }
                activityEndIntent.putExtra("Ingredients", ingredientsToPass)
                startActivity(activityEndIntent)
                finish()
            }

        }

    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_ingredient, null)
        val addDialog = AlertDialog.Builder(this)
        val ingredientsName = v.findViewById<EditText>(R.id.etIngredient)
        addDialog.setView(v)
        addDialog.setPositiveButton("Add") { dialog, _ ->
            val ingredientToAdd = ingredientsName.text.toString()
            ingredientList.add(ingredientToAdd)
            ingredientsAdapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        addDialog.create()
        addDialog.show().window?.setBackgroundDrawableResource(R.color.gray)

    }

    override fun onBackPressed() {
        val activityEndIntent = Intent(this@ReceiptsActivity, MenuActivity::class.java)
        startActivity(activityEndIntent)
        finish()
    }
}