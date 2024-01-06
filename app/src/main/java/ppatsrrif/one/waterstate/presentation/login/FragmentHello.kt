package ppatsrrif.one.waterstate.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.databinding.FragmentHelloBinding

class FragmentHello : Fragment() {

    private lateinit var binding: FragmentHelloBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHelloBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonLogIn.setOnClickListener {

            //run method from LoginActivity
            (activity as LoginActivity).nextFragment(FragmentName.newInstance())
        }
    }
}