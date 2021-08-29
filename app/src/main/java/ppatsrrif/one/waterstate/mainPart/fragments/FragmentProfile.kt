package ppatsrrif.one.waterstate.mainPart.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ppatsrrif.one.waterstate.mainPart.dialogs.DialogEditUser
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.SplashScreen
import ppatsrrif.one.waterstate.databinding.FragmentProfileBinding
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser

class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private lateinit var dialogEditUser: DialogEditUser
    private val liveDataUser: ViewModelUser by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater)

        // initializing sharedPreferenceHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        //initializing dialog UserEdit
        dialogEditUser = DialogEditUser()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // reset profile
        binding.resetProfile.setOnClickListener {
           sharedPreferencesHelper.setStartMode(0)
           startActivity(Intent(requireContext(), SplashScreen::class.java))
        }


        // show listener to show dialogEditUser
        binding.editUserProfile.setOnClickListener {
            dialogEditUser.show(childFragmentManager, "DialogUserEdit")
        }


        // set data to profile info
        liveDataUser.liveDataName.observe(requireActivity()) {
            binding.nameText.text = it
        }

        liveDataUser.liveDataWeight.observe(requireActivity()) {
            binding.weightText.text = "$it кг"
        }




    }

    companion object {

        fun newInstance() = FragmentProfile()
    }
    
}