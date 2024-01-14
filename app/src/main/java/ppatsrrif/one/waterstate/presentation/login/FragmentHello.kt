package ppatsrrif.one.waterstate.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentHelloBinding

@AndroidEntryPoint
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
            findNavController().navigate(R.id.action_fragmentHello_to_fragmentUser)
        }
    }
}