package ppatsrrif.one.waterstate.mainPart.dialogs


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.DialogEditUserBinding

class DialogEditUser : DialogFragment() {

    private lateinit var bindingDialog: DialogEditUserBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingDialog = DialogEditUserBinding.inflate(inflater)

        // initializing SharedPreferencesHelper
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

        return bindingDialog.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set params for dialog
        dialog?.setCancelable(false)
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        // change weight text
        bindingDialog.weightEditSlider.addOnChangeListener { _, value, _ ->
            bindingDialog.finalWeight.text = "${getString(R.string.weight)} $value"
        }

        // change name text
        bindingDialog.nameEditInput.editText?.addTextChangedListener(textListener)

        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener{
            dismiss()
        }


        // save data in dialog
        bindingDialog.saveEditsButton.setOnClickListener {

            val userWeight = bindingDialog.weightEditSlider.value
            val userName = bindingDialog.nameEditInput.editText?.text.toString()

            // check for validity
            if(userName.isNotEmpty()) {

            // set data to SharedPreferences
                sharedPreferencesHelper.setUserWeight(userWeight)
                sharedPreferencesHelper.setUserName(userName)

                // close dialog
                dismiss()

            } else {
                bindingDialog.nameEditInput.error = "Пустое поле"
            }

        }

    }


    //listener for editText
    private val textListener = object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if(bindingDialog.nameEditInput.editText?.text.toString().isNotEmpty()) {
                bindingDialog.nameEditInput.error = null
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }


    override fun onStart() {
        super.onStart()

        // get start weight and name from (SharedPreferences -> LiveData -> this)
        bindingDialog.nameEditInput.editText?.setText(sharedPreferencesHelper.getUserName())

        bindingDialog.weightEditSlider.value = sharedPreferencesHelper.getUserWeight()
        bindingDialog.finalWeight.text = "${getString(R.string.weight)} ${sharedPreferencesHelper.getUserWeight()}"
    }
}