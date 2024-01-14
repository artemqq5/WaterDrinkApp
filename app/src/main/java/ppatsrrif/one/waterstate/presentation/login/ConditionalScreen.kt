package ppatsrrif.one.waterstate.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.domain.repository.model.LoadMode
import ppatsrrif.one.waterstate.presentation.viewmodel.UserViewModel

@AndroidEntryPoint
class ConditionalScreen : Fragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (userViewModel.getDefaultLoadMode() == LoadMode.Home) {
            findNavController().navigate(R.id.action_conditionalScreen_to_mainActivity)
        } else {
            findNavController().navigate(R.id.action_conditionalScreen_to_fragmentHello)
        }

        return null
    }

}