package ppatsrrif.one.waterstate.mainPart.dialogs

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.SplashScreen
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem

class DialogResetProfile : DialogFragment() {


    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper
    private val viewModelItem by lazy {
        ViewModelProvider(requireActivity())[ViewModelItem::class.java]
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.resetProfileTitle))
            .setMessage(getString(R.string.resetProfileMessage))
            .setNegativeButton("ОТМЕНА") { _, _ ->
                dismiss()
            }
            .setPositiveButton("ОК") { _, _ ->

                viewModelItem.deleteTStorage()
                viewModelItem.deleteGoalsT()

                // initializing SharedPreferencesHelper
                sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

                sharedPreferencesHelper.setStartMode(0)
                startActivity(Intent(requireContext(), SplashScreen::class.java))
                dismiss()
            }
            .create()

}