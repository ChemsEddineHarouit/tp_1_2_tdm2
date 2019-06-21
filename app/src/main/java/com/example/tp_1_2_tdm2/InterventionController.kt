package com.example.tp_1_2_tdm2

import java.util.*
import kotlin.collections.ArrayList

class InterventionController private constructor(){

    val interventionList = ArrayList<Intervention>()

    init{
        interventionList.clear()
        interventionList.add(Intervention(1, "plombier1", "type1", Date()))
        interventionList.add(Intervention(2, "plombier2", "type1", Date()))
        interventionList.add(Intervention(3, "plombier3", "type2", Date()))
        interventionList.add(Intervention(4, "plombier4", "type2", Date()))
        interventionList.add(Intervention(5, "plombier5", "type3", Date()))
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



}