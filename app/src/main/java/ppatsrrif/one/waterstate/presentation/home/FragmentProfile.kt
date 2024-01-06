package ppatsrrif.one.waterstate.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import ppatsrrif.one.waterstate.databinding.FragmentProfileBinding
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelUser

class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val liveDataUser: ViewModelUser by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // reset profile
        binding.resetProfile.setOnClickListener {
            DialogResetProfile().show(parentFragmentManager, "DialogResetProfile")
        }

        // show dialogEditUser FullScreen
        binding.editUserProfile.setOnClickListener {
            val dialog = DialogEditUser()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            transaction
                .replace(android.R.id.content, dialog)
                .commit()
        }

        // set data to profile info
        liveDataUser.liveDataName.observe(requireActivity()) {
            binding.nameText.text = it
        }

        liveDataUser.liveDataWeight.observe(requireActivity()) {
            binding.weightText.text = "$it " + liveDataUser.getStringWeight()
        }

    }

    companion object {

        fun newInstance() = FragmentProfile()
    }

}