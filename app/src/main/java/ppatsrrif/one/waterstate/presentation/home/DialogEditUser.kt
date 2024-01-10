package ppatsrrif.one.waterstate.presentation.home


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.DialogEditUserBinding
import ppatsrrif.one.waterstate.domain.repository.UserRepository
import ppatsrrif.one.waterstate.domain.repository.model.UserModel
import javax.inject.Inject

@AndroidEntryPoint
class DialogEditUser : DialogFragment() {

    private lateinit var bindingDialog: DialogEditUserBinding

    @Inject
    lateinit var userRepositoryImp: UserRepository


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

        // default user params
        bindingDialog.nameEditInput.editText?.setText(userRepositoryImp.getUser().name)

        // change name text
        bindingDialog.nameEditInput.editText?.addTextChangedListener(textListener)

        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener {
            dismiss()
        }

        // save data in dialog
        bindingDialog.saveEditsButton.setOnClickListener {

            val userName = bindingDialog.nameEditInput.editText?.text.toString()

            // check for validity
            if (validationName(userName)) {

                val user = userRepositoryImp.getUser()
                userRepositoryImp.setUser(
                    UserModel(
                        userName,
                        user.weight,
                        user.gender,
                        user.physical
                    )
                )

                dismiss()

            } else {
                bindingDialog.nameEditInput.error = resources.getString(R.string.empty_error)
            }

        }

    }

    //    listener for editText
    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val name = bindingDialog.nameEditInput.editText?.text.toString()

            if (validationName(name)) {
                bindingDialog.nameEditInput.error = null
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


}