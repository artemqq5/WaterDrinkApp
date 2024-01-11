package ppatsrrif.one.waterstate.presentation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.FragmentUserBinding
import ppatsrrif.one.waterstate.domain.repository.UserRepository
import ppatsrrif.one.waterstate.domain.repository.model.LoadMode
import ppatsrrif.one.waterstate.domain.repository.model.UserGender
import ppatsrrif.one.waterstate.domain.repository.model.UserModel
import ppatsrrif.one.waterstate.presentation.viewmodel.UserViewModel
import javax.inject.Inject

@AndroidEntryPoint
class FragmentUser : Fragment() {

    private val nameSaveInstance = "user-name"
    private val weightSaveInstance = "weight-name"
    private val genderSaveInstance = "gender-name"
    private val physicalSaveInstance = "physical-name"

    private lateinit var binding: FragmentUserBinding

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentUserBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.physicalChoicer.adapter = ArrayAdapter(
            requireContext(),
            R.layout.physical_item,
            resources.getStringArray(R.array.physical_activity)
        )

        binding.textFieldName.editText?.addTextChangedListener(textListener)
        binding.textFieldWeight.editText?.addTextChangedListener(textListener)
        binding.RadioGenderChoicer.setOnCheckedChangeListener(radioGroupListener)

        binding.buttonNext.setOnClickListener {

            val name = binding.textFieldName.editText?.text.toString()
            val weight = binding.textFieldWeight.editText?.text.toString()
            val gender = binding.RadioGenderChoicer
            val physical =
                resources.getStringArray(R.array.physical_activity_value)[binding.physicalChoicer.selectedItemPosition]

            if (validationName(name) && validationWeight(weight) && validationGender(gender)) {

                // save data from user
                userViewModel.setNewUser(
                    UserModel(
                        name = name,
                        weight = weight.toFloat(),
                        gender = if (binding.radioMan.isChecked) UserGender.Male else UserGender.Female,
                        physical = physical.toFloat()
                    )
                )

                // set next load mode home
                userViewModel.setLoadMode(LoadMode.Home)

                // go to finish text (optional action)
                findNavController().navigate(R.id.action_fragmentUser_to_fragmentEnd)
            }

        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(nameSaveInstance, binding.textFieldName.editText?.text.toString())
        outState.putString(weightSaveInstance, binding.textFieldWeight.editText?.text.toString())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.textFieldName.editText?.setText(savedInstanceState?.getString(nameSaveInstance))
        binding.textFieldWeight.editText?.setText(savedInstanceState?.getString(weightSaveInstance))
    }


    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val name = binding.textFieldName.editText?.text.toString()
            val weight = binding.textFieldWeight.editText?.text.toString()

            if (name.isNotEmpty() && validationName(name)) {
                binding.textFieldName.error = null
            }

            if (weight.isNotEmpty() && validationWeight(weight)) {
                binding.textFieldWeight.error = null
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

    private val radioGroupListener = RadioGroup.OnCheckedChangeListener { _, _ ->
        if (binding.radioMan.isChecked || binding.radioWoman.isChecked) {
            binding.radioGroupError.visibility = View.INVISIBLE
        }
    }

    private fun validationName(name: String): Boolean {
        if (name.isEmpty()) {
            binding.textFieldName.error = resources.getString(R.string.empty_error)
            return false
        }

        return true
    }

    private fun validationWeight(weight: String): Boolean {
        try {
            if (weight.toFloat() !in 30.0..150.0) {
                binding.textFieldWeight.error = resources.getString(R.string.weight_range_error)
                return false
            }
        } catch (e: Exception) {
            binding.textFieldWeight.error = resources.getString(R.string.weight_format_error)
            return false
        }

        return true
    }

    private fun validationGender(gender: RadioGroup): Boolean {
        if (gender.checkedRadioButtonId == -1) {
            binding.radioGroupError.visibility = View.VISIBLE
            return false
        }

        return true
    }


}