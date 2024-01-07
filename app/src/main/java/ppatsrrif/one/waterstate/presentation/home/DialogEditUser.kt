package ppatsrrif.one.waterstate.presentation.home


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.repository.storage.UserUserStoragePreference
import ppatsrrif.one.waterstate.databinding.DialogEditUserBinding
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelUser

class DialogEditUser : DialogFragment(), NumberPicker.OnValueChangeListener {

    private lateinit var bindingDialog: DialogEditUserBinding
    private val userStoragePreference by lazy {
        UserUserStoragePreference(requireContext())
    }
    private val viewModelUser: ViewModelUser by activityViewModels()

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

        // change name text
        bindingDialog.nameEditInput.editText?.addTextChangedListener(textListener)

        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener {
            dismiss()
        }

        //set params for numberPicker
        bindingDialog.weightChoice.apply {
            maxValue = 150
            minValue = 30
        }
        bindingDialog.weightChoiceSub.apply {
            maxValue = 9
            minValue = 0
        }

        bindingDialog.weightChoice.setOnValueChangedListener(this)
        bindingDialog.weightChoiceSub.setOnValueChangedListener(this)


        // save data in dialog
        bindingDialog.saveEditsButton.setOnClickListener {

            val userWeight = bindingDialog.weightChoice.value +
                    bindingDialog.weightChoiceSub.value / 10.0f

            val userName = bindingDialog.nameEditInput.editText?.text.toString()

            // check for validity
            if (userName.isNotEmpty()) {

                // set data to SharedPreferences
//                sharedPreferencesHelper.setUserWeight(userWeight)
//                sharedPreferencesHelper.setUserName(userName)

                // set into viewModel
                viewModelUser.liveDataWeight.value = userWeight
                viewModelUser.liveDataName.value = userName


                // close dialog
                dismiss()

            } else {
                bindingDialog.nameEditInput.error = "Пустое поле"
            }

        }

    }


    //listener for editText
    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (bindingDialog.nameEditInput.editText?.text.toString().isNotEmpty()) {
                bindingDialog.nameEditInput.error = null
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }


    override fun onStart() {
        super.onStart()

        // get start weight and name from (SharedPreferences -> LiveData -> this)
//        bindingDialog.nameEditInput.editText?.setText(sharedPreferencesHelper.getUserName())
//
//        bindingDialog.weightChoice.value = sharedPreferencesHelper.getUserWeight().toInt()
//        bindingDialog.weightChoiceSub.value =
//            ((sharedPreferencesHelper.getUserWeight() % 1) * 10).toInt()

//        bindingDialog.finalWeight.text =
//            "${getString(R.string.weight)} ${sharedPreferencesHelper.getUserWeight()}"
    }

    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {

        var value = bindingDialog.weightChoice.value +
                bindingDialog.weightChoiceSub.value / 10.0f

        bindingDialog.finalWeight.text =
            "${getString(R.string.weight)} $value"
    }
}