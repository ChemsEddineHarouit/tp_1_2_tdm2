package com.example.tp_1_2_tdm2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.app.DatePickerDialog
import android.net.Uri
import android.support.v4.app.FragmentTransaction
import android.view.View
import android.widget.Toast
import com.example.tp_1_2_tdm2.AddIntervention.OnFragmentInteractionListener
import java.util.*


class MainActivity : AppCompatActivity() , OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        interventionAdapter.notifyDataSetChanged()
        Toast.makeText(this, "interaction", Toast.LENGTH_SHORT).show()
    }


    lateinit var add_interv_fragment : AddIntervention
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

//        add_interv_btn.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        interventionsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        interventionAdapter = InterventionAdapter(this)
        interventionsRecyclerView.adapter = interventionAdapter



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

    override fun onResume() {
        super.onResume()

        if(intent.getStringExtra("plumber") == null){
            return
        }

        val plumber = intent.getStringExtra("plumber")
        val type = intent.getStringExtra("type")
        val num = intent.getIntExtra("num", interventionAdapter.itemCount)

        val intervention = Intervention(num, plumber, type, Date())

        interventionAdapter.addIntervention(intervention)
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

    fun show_add_interv_form(view : View):Unit{
//        add_interv_fragment = AddIntervention.newInstance()
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, add_interv_fragment)
//            .addToBackStack(add_interv_fragment.toString())
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            .commit()
        val frag = AddIntervention.newInstance()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_container, frag)
        .addToBackStack(frag.toString())
        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }
}
