package ppatsrrif.one.waterstate.presentation.home

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.R

@AndroidEntryPoint
class DialogResetProfile : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.resetProfileTitle))
            .setMessage(getString(R.string.resetProfileMessage))
            .setNegativeButton(resources.getString(R.string.button_cancel)) { _, _ ->
                dismiss()
            }
            .setPositiveButton(resources.getString(R.string.reset_ok)) { _, _ ->
                dismiss()
            }.create()

}