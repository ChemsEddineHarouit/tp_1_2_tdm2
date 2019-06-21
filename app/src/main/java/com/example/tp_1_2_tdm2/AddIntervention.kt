package com.example.tp_1_2_tdm2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_add_intervention.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddIntervention.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddIntervention.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddIntervention () : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        interv_submit.setOnClickListener {
            val controller = InterventionController.instance

            val num = interv_num.text.toString().toInt()
//            val date = interv_date.text
            val plumber = interv_plumber.selectedItem.toString()
            val type = interv_type.selectedItem.toString()

            if(num == null || plumber == null || type == null){
                Toast.makeText(context, "Veuillez renseigner tout les champs", Toast.LENGTH_SHORT).show()
//                return
            }
            else {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("plumber", plumber)
                intent.putExtra("num", num)
                intent.putExtra("type", type)

                startActivity(intent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_intervention, container, false)
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    fun onButtonPressed(uri: Uri) {
//        listener?.onFragmentInteraction(uri)
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AddIntervention.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = AddIntervention()
    }
}
