package ppatsrrif.one.waterstate.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentProfileBinding
import ppatsrrif.one.waterstate.presentation.viewmodel.ViewModelUser

@AndroidEntryPoint
class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.resetProfile.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentProfile_to_dialogResetProfile)
        }

        binding.editUserProfile.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentProfile_to_dialogEditUser)
        }

        // set data to profile info
//        liveDataUser.liveDataName.observe(requireActivity()) {
//            binding.nameText.text = it
//        }
//
//        liveDataUser.liveDataWeight.observe(requireActivity()) {
//            binding.weightText.text = "$it " + liveDataUser.getStringWeight()
//        }

    }


}