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
import androidx.fragment.app.activityViewModels
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.databinding.DialogEditUserBinding
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelUser

class DialogEditUser : DialogFragment() {

    private lateinit var bindingDialog: DialogEditUserBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private val liveDataUser: ViewModelUser by activityViewModels()

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

        // close dialog
        bindingDialog.closeDialogButton.setOnClickListener{
            dialog?.cancel()
        }

        // save data in dialog
        bindingDialog.saveEditsButton.setOnClickListener {

            val userWeight = bindingDialog.weightEditSlider.value
            val userName = bindingDialog.nameEditInput.editText?.text.toString()

            sharedPreferencesHelper.setUserWeight(userWeight)
            sharedPreferencesHelper.setUserName(userName)


            dialog?.cancel()
        }

        // set start weight and profile
        liveDataUser.liveDataName.observe(requireActivity()) {
            bindingDialog.nameEditInput.editText?.setText(it)
        }

        liveDataUser.liveDataName.observe(requireActivity()) {
            bindingDialog.finalWeight.text = "${getString(R.string.weight)} $it"
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



}