package com.example.tp_1_2_tdm2

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.text.SimpleDateFormat


class InterventionAdapter(): RecyclerView.Adapter<InterventionAdapter.ViewHolder>(){

    var interventionList = ArrayList<Intervention>()

    init {
        val controller = InterventionController.instance
        interventionList = controller.interventionList
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
}

