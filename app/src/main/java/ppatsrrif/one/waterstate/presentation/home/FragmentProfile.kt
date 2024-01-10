package ppatsrrif.one.waterstate.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ppatsrrif.one.waterstate.ApplicationStart.Companion.log
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentProfileBinding
import ppatsrrif.one.waterstate.domain.repository.UserRepository
import javax.inject.Inject

@AndroidEntryPoint
class FragmentProfile : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    @Inject
    lateinit var userRepositoryImp: UserRepository

    private val excCoroutine = CoroutineExceptionHandler { _, throwable ->
        log("FragmentProfile: $throwable")
    }

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

        // todo
        // додати mvvm

        // set data to profile info
        lifecycleScope.launch(Dispatchers.Main + excCoroutine) {
            val user = withContext(Dispatchers.IO) { userRepositoryImp.getUser() }

            binding.name.text = user.name
            binding.physicalActivity.text = setPhysicalActivityByIndex(user.physical)
            binding.baseHaracteristics.text = resources.getString(
                R.string.base_characteristics_profile,
                user.gender,
                user.weight.toString()
            )
        }

    }

    private fun setPhysicalActivityByIndex(index: Float): String {
        return when (index) {
            1.2f -> resources.getString(R.string.physical_1_2)
            1.4f -> resources.getString(R.string.physical_1_4)
            1.6f -> resources.getString(R.string.physical_1_6)
            1.8f -> resources.getString(R.string.physical_1_8)

            else -> resources.getString(R.string.physical_1_0)
        }
    }

}