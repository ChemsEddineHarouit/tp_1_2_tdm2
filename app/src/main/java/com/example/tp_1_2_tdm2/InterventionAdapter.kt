package com.example.tp_1_2_tdm2

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.FileNotFoundException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class InterventionAdapter(context : Context): RecyclerView.Adapter<InterventionAdapter.ViewHolder>(){

    var interventionList = ArrayList<Intervention>()
    var  interventionAllList = ArrayList<Intervention>()
    var interventionSearchList = ArrayList<Intervention>()
    var context = context


    init {
        val controller = InterventionController.instance
        try {
            controller.load(context)
            interventionList = controller.interventionList
            interventionAllList = interventionList
        }
        catch (e: FileNotFoundException){
            Toast.makeText(context, "File not found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return interventionList.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, index: Int) {
        val intervention = interventionList[index]
        holder.num.text = "numéro : " + intervention.num.toString()
        holder.type.text = "Type : " + intervention.type.toString()
        holder.plombier.text = "Plombier : " +intervention.plombier.toString()
        val dateFormat = SimpleDateFormat("dd MMMM yyyy à hh:mm")
        holder.date.text = "Ajouté le: ${dateFormat.format(intervention.date.time)}"

        holder.delete_btn.setOnClickListener {
            val controller = InterventionController.instance

            controller.delete(index)
            notifyDataSetChanged()
            controller.save(context)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_intervention, parent, false)
        return ViewHolder(v);
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val num = itemView.findViewById<TextView>(R.id.row_intervention_num)
        val plombier = itemView.findViewById<TextView>(R.id.row_intervention_plombier)
        val date = itemView.findViewById<TextView>(R.id.row_intervention_date)
        val type = itemView.findViewById<TextView>(R.id.row_intervention_type)
        val delete_btn = itemView.findViewById<Button>(R.id.delete_btn)
    }

    fun filter(mY:Int, mM:Int, mD:Int){
        if (mY*mM*mD == 0) {
            interventionSearchList = interventionAllList
        } else {
            val filteredList = ArrayList<Intervention>()
            for (intervention in interventionAllList) {
                val calendar = Calendar.getInstance()
                var date = intervention.date
                calendar.setTime(date)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val month = calendar.get(Calendar.MONTH)
                val year = calendar.get(Calendar.YEAR)
                if (day == mD && month == mM && year == mY) {
                    filteredList.add(intervention)
                }
                println(intervention.date.day.toString() + " " + mD)
            }
            interventionSearchList = filteredList
        }
        interventionList = interventionSearchList
        notifyDataSetChanged()
    }
}

