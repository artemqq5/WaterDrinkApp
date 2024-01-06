package ppatsrrif.one.waterstate.presentation.home

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.repository.SharedPreferencesHelper
import ppatsrrif.one.waterstate.presentation.login.SplashScreen
import ppatsrrif.one.waterstate.presentation.viewModel.ViewModelItem

class DialogResetProfile : DialogFragment() {


    private val sharedPreferencesHelper by lazy {
        SharedPreferencesHelper(requireContext())
    }
    private val viewModelItem by lazy {
        ViewModelProvider(requireActivity())[ViewModelItem::class.java]
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.resetProfileTitle))
            .setMessage(getString(R.string.resetProfileMessage))
            .setNegativeButton(resources.getString(R.string.button_cancel)) { _, _ ->
                dismiss()
            }
            .setPositiveButton(resources.getString(R.string.reset_ok)) { _, _ ->

                viewModelItem.deleteTStorage()
                viewModelItem.deleteGoalsT()

                sharedPreferencesHelper.setStartMode(0)
                startActivity(Intent(requireContext(), SplashScreen::class.java))
                requireActivity().finish()
                dismiss()
            }
            .create()

}