package ppatsrrif.one.waterstate.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentEndBinding

@AndroidEntryPoint
class FragmentEnd : Fragment() {

    private lateinit var binding: FragmentEndBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentEndBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.buttonStartMain.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentEnd_to_mainActivity)
        }
    }


}