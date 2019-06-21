package com.example.tp_1_2_tdm2

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.CompoundButton
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.widget.Toast
import android.R.id.toggle
import android.app.DatePickerDialog
import android.opengl.Visibility
import android.view.View
import kotlinx.android.synthetic.main.row_intervention.*
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var interventionAdapter: InterventionAdapter
    val dateFilterShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val m = c.get(Calendar.MONTH)
        val d = c.get(Calendar.DAY_OF_MONTH)


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        interventionsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        interventionAdapter = InterventionAdapter(this)
        interventionsRecyclerView.adapter = interventionAdapter


        load_btn.setOnClickListener(){
            val controller = InterventionController.instance
            controller.load(this)
        }

        save_btn.setOnClickListener(){
            val controller = InterventionController.instance
            controller.save(this)
            interventionAdapter.notifyDataSetChanged()
        }

        date_filter.setOnClickListener(){
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, mY, mM, mD ->
                println("--------------------------------------")
                println(""+ mY + "/" + mM + "/" + mD)
                interventionAdapter!!.filter(mY, mM, mD)
            }, y, m, d)
            datePickerDialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
