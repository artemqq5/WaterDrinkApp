package ppatsrrif.one.waterstate.presentation.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.presentation.login.activity.LoginActivity
import ppatsrrif.one.waterstate.presentation.viewmodel.UserViewModel

@AndroidEntryPoint
class DialogResetProfile : DialogFragment() {

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.resetProfileTitle))
            .setMessage(getString(R.string.resetProfileMessage))
            .setNegativeButton(resources.getString(R.string.button_cancel)) { _, _ ->
                dismiss()
            }
            .setPositiveButton(resources.getString(R.string.reset_ok)) { _, _ ->

                userViewModel.clearDataUser()
                startActivity(Intent(requireActivity(), LoginActivity::class.java))

                dismiss()
            }.create()

}