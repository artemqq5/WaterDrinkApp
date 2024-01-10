package ppatsrrif.one.waterstate.presentation.home


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ppatsrrif.one.waterstate.ApplicationStart
import ppatsrrif.one.waterstate.ApplicationStart.Companion.log
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.DialogAddWaterBinding
import ppatsrrif.one.waterstate.domain.repository.WaterRepository
import ppatsrrif.one.waterstate.domain.repository.model.WaterModel
import ppatsrrif.one.waterstate.repository.database.table.WaterItemTable
import ppatsrrif.one.waterstate.domain.usecase.DateUseCase
import javax.inject.Inject

@AndroidEntryPoint
class DialogAddWater : DialogFragment() {

    private lateinit var bindingDialog: DialogAddWaterBinding

    @Inject
    lateinit var waterRepositoryImp: WaterRepository

    private val excCoroutine = CoroutineExceptionHandler { _, throwable ->
        log("DialogAddWater: $throwable")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingDialog = DialogAddWaterBinding.inflate(inflater)

        bindingDialog.waterInput.editText?.addTextChangedListener(textListener)

        return bindingDialog.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set params for dialog
        dialog?.setCancelable(true)
        dialog?.window?.run {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        // save data in dialog
        bindingDialog.setWater.setOnClickListener {

            val volume = bindingDialog.waterInput.editText?.text.toString()

            // check for validity
            if (bindingDialog.waterInput.editText?.text.toString().isNotEmpty()) {
                if (volume.toDouble() in 100.0..1000.0) {

                    val volumeWaterItem = volume.toDouble()

                    //todo в принципі все окей, тільки треба додати mvvm
                    lifecycleScope.launch(Dispatchers.IO + excCoroutine) {
                        waterRepositoryImp.addWaterItem(
                            WaterModel(
                                date = DateUseCase().getCurrentDate(),
                                volumeWater = volumeWaterItem
                            )
                        )
                    }

                    dismiss()
                } else bindingDialog.waterInput.error = resources.getString(R.string.field_ml)

            } else bindingDialog.waterInput.error = resources.getString(R.string.empty_error)


        }

        // close
        bindingDialog.closeWater.setOnClickListener {
            dismiss()
        }

    }


    //listener for editText
    private val textListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (bindingDialog.waterInput.editText?.text.toString().isNotEmpty()) {
                if ((bindingDialog.waterInput.editText?.text.toString()).toDouble() in 100.0..1000.0) {
                    bindingDialog.waterInput.error = null
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }

    }

}