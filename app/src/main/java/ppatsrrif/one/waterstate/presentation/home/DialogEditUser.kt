package ppatsrrif.one.waterstate.presentation.home


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.ApplicationStart.Companion.log
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.DialogEditUserBinding
import ppatsrrif.one.waterstate.domain.repository.model.UserGender
import ppatsrrif.one.waterstate.domain.repository.model.UserModel
import ppatsrrif.one.waterstate.presentation.viewmodel.UserViewModel

@AndroidEntryPoint
class DialogEditUser : DialogFragment() {

    private lateinit var bindingDialog: DialogEditUserBinding

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingDialog = DialogEditUserBinding.inflate(inflater)

        return bindingDialog.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set params for dialog
        dialog?.setCancelable(false)
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        bindingDialog.physicalChoicer.adapter = ArrayAdapter(
            requireContext(),
            R.layout.physical_item,
            resources.getStringArray(R.array.physical_activity)
        )

        // default user params
        setDefaultUserParams(userViewModel.getDefaultUser())

        // change name text
        bindingDialog.nameEditInput.editText?.addTextChangedListener(textListener)
        bindingDialog.weightEditInput.editText?.addTextChangedListener(textListener)

        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener {
            dismiss()
        }

        // save data in dialog
        bindingDialog.saveEditsButton.setOnClickListener {

            val name = bindingDialog.nameEditInput.editText?.text.toString()
            val weight = bindingDialog.weightEditInput.editText?.text.toString()
            val gender =
                if (bindingDialog.radioMan.isChecked) UserGender.Male else UserGender.Female
            val physical =
                resources.getStringArray(R.array.physical_activity_value)[bindingDialog.physicalChoicer.selectedItemPosition]

            // check for validity
            if (validationName(name) && validationWeight(weight)) {

                userViewModel.setNewUser(
                    UserModel(
                        name,
                        weight.toFloat(),
                        gender,
                        physical.toFloat()
                    )
                )

                dismiss()

            }

        }

    }

    //    listener for editText
    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val name = bindingDialog.nameEditInput.editText?.text.toString()
            val weight = bindingDialog.weightEditInput.editText?.text.toString()

            if (name.isNotEmpty() && validationName(name)) {
                bindingDialog.nameEditInput.error = null
            }

            if (weight.isNotEmpty() && validationWeight(weight)) {
                bindingDialog.weightEditInput.error = null
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

    private fun validationName(name: String): Boolean {
        if (name.isEmpty()) {
            bindingDialog.nameEditInput.error = resources.getString(R.string.empty_error)
            return false
        }

        return true
    }

    private fun validationWeight(weight: String): Boolean {
        try {
            if (weight.toFloat() !in 30.0..150.0) {
                bindingDialog.weightEditInput.error =
                    resources.getString(R.string.weight_range_error)
                return false
            }

        } catch (e: Exception) {
            bindingDialog.weightEditInput.error = resources.getString(R.string.weight_format_error)
            return false
        }

        return true
    }

    private fun setDefaultUserParams(user: UserModel) {
        bindingDialog.nameEditInput.editText?.setText(user.name)
        bindingDialog.weightEditInput.editText?.setText(user.weight.toString())
        bindingDialog.physicalChoicer.setSelection(setPhysicalActivityByValue(user.physical))

        if (user.gender == UserGender.Male) bindingDialog.radioMan.isChecked = true
        else bindingDialog.radioWoman.isChecked = true
    }

    private fun setPhysicalActivityByValue(value: Float): Int {
        return when (value) {
            1.2f -> 1
            1.4f -> 2
            1.6f -> 3
            1.8f -> 4

            else -> 0
        }
    }


}