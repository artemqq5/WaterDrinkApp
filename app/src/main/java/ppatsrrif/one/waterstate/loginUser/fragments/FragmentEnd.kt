package ppatsrrif.one.waterstate.loginUser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ppatsrrif.one.waterstate.databinding.FragmentEndBinding
import ppatsrrif.one.waterstate.loginUser.activity.LoginActivity

class FragmentEnd : Fragment() {

    private lateinit var binding: FragmentEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEndBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonStartMain.setOnClickListener {

            //run method from LoginActivity
            (activity as LoginActivity).startMainAct()
        }
    }

    companion object {
        fun newInstance() = FragmentEnd()
    }
}