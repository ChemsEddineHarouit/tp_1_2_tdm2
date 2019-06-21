package com.example.tp_1_2_tdm2

import android.content.Context
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter
import java.util.*
import kotlin.collections.ArrayList


class InterventionController private constructor(){

    var interventionList = ArrayList<Intervention>()
    val dataBaseFileName = "interventions.json"


    init{
        interventionList.clear()
        //We don't need these initialisations because the load function is called in the init of the adapter
//        interventionList.add(Intervention(1, "plombier1", "type1", Date()))
//        interventionList.add(Intervention(2, "plombier2", "type1", Date()))
//        interventionList.add(Intervention(3, "plombier3", "type2", Date()))
//        interventionList.add(Intervention(4, "plombier4", "type2", Date()))
//        interventionList.add(Intervention(5, "plombier5", "type3", Date()))
    }


    fun addIntervention(pub : Intervention){
        interventionList.add(pub)
    }

    fun getIntervention(index : Int) : Intervention? {
        for (intervention in interventionList){
            if (intervention.num == index) return intervention
        }
        return null
    }

    private object Holder{
        val INSTANCE= InterventionController()
    }
    companion object {
        val instance: InterventionController by lazy { Holder.INSTANCE }
    }

    //we need context for the file Path
    fun save(context: Context) {
        val fileName = context.getFilesDir().getPath().toString() + dataBaseFileName
        val gson = Gson()
        val jsonInterventionList = gson.toJson(interventionList)
        val interventionsFile = FileWriter(fileName, false)
        interventionsFile.flush()
        interventionsFile.write(jsonInterventionList)
        interventionsFile.close()
        println("----------------------------------saving--------------------------------------------------------------")
        println(jsonInterventionList)
    }

    //we need context for the file Path
    fun load(context: Context) {
        val fileName = context.getFilesDir().getPath().toString() + dataBaseFileName
        val gson = Gson()

        val jsonInterventionList = File(fileName).readText()
        val interventionList = gson.fromJson(jsonInterventionList, Array<Intervention>::class.java).toMutableList()
        println("----------------------------------loading--------------------------------------------------------------")
        println(interventionList)
        this.interventionList = interventionList as ArrayList<Intervention>
    }

    fun delete(index: Int) {
        interventionList.removeAt(index)

    }


}