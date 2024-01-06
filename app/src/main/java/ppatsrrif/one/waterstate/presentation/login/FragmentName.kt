package ppatsrrif.one.waterstate.presentation.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.repository.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.FragmentNameBinding

class FragmentName : Fragment() {

    private lateinit var binding: FragmentNameBinding
    private val sharedPreferencesHelper by lazy {
        SharedPreferencesHelper(requireContext())
    }
    private val nameKey = "NameUser"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNameBinding.inflate(inflater)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.textFieldName.editText?.addTextChangedListener(textListener)

        binding.buttonNextWeight.setOnClickListener {

            val name = binding.textFieldName.editText?.text.toString()
            // check for validity

            if (name.isNotEmpty()) {
                //save user name
                sharedPreferencesHelper.setUserName(name)
                //run method from LoginActivity
                (activity as LoginActivity).nextFragment(FragmentWeight.newInstance())

            } else {
                binding.textFieldName.error = resources.getString(R.string.empty_error)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(nameKey, binding.textFieldName.editText?.text.toString())

        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {

        binding.textFieldName.editText?.setText(savedInstanceState?.getString(nameKey, ""))
        super.onViewStateRestored(savedInstanceState)
    }

    companion object {
        fun newInstance() = FragmentName()
    }

    //listener for editText
    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (binding.textFieldName.editText?.text.toString().isNotEmpty()) {
                binding.textFieldName.error = null
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }


}