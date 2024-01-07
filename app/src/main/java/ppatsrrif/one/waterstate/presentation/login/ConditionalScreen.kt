package ppatsrrif.one.waterstate.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.domain.repository.model.LoadMode
import ppatsrrif.one.waterstate.domain.repository.UserRepository
import javax.inject.Inject

@AndroidEntryPoint
class ConditionalScreen : Fragment() {

    @Inject
    lateinit var userRepositoryImp: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (userRepositoryImp.getLoadMode() == LoadMode.Home) {
            findNavController().navigate(R.id.action_conditionalScreen_to_mainActivity)
        } else {
            findNavController().navigate(R.id.action_conditionalScreen_to_fragmentHello)
        }

        return null
    }

}