package ppatsrrif.one.waterstate.mainPart.dialogs

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.SharedPreferencesHelper
import ppatsrrif.one.waterstate.SplashScreen

class DialogResetProfile : DialogFragment() {


    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.resetProfileTitle))
            .setMessage(getString(R.string.resetProfileMessage))
            .setNegativeButton("ОТМЕНА") { _, _ ->
                dismiss()
            }
            .setPositiveButton("ОК") { _, _ ->

                // initializing SharedPreferencesHelper
                sharedPreferencesHelper = SharedPreferencesHelper(requireContext())

                sharedPreferencesHelper.setStartMode(0)
                startActivity(Intent(requireContext(), SplashScreen::class.java))
                dismiss()
            }
            .create()

}