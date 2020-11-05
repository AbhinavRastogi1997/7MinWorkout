package com.example.a7minutesworkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_status.view.*

class ExerciseStatusAdapter(val items: ArrayList<Excercise>, val context: Context) : RecyclerView.Adapter<ExerciseStatusAdapter.viewHolder>(){

        class viewHolder(view: View) : RecyclerView.ViewHolder(view){
            val tvItem = view.tvItem
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.item_status,
                        parent,
                        false
                )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val model: Excercise = items[position]
        holder.tvItem.text = model.id.toString()

        if(model.isSelected){
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.white_bg)
        }
        else if(model.isCompleted){
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.selected_mark)
        }
    }
}