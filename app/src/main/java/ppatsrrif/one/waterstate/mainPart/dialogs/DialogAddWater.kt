package ppatsrrif.one.waterstate.mainPart.dialogs


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import ppatsrrif.one.waterstate.databinding.DialogCreateBinding
import ppatsrrif.one.waterstate.mainPart.helperClasses.DateHelper
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableItemStorage
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem
import java.text.SimpleDateFormat
import java.util.*

class DialogAddWater : DialogFragment() {

    private lateinit var bindingDialog: DialogCreateBinding
    private val viewModelItem by lazy {
        ViewModelProvider(requireActivity())[ViewModelItem::class.java]
    }

    private val dateHelper by lazy {
        DateHelper()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        bindingDialog = DialogCreateBinding.inflate(inflater)

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

            val dateNow = Date()
            val stringFormat = SimpleDateFormat("HH:mm")
            val resultTime = stringFormat.format(dateNow)

            // check for validity
            if (bindingDialog.waterInput.editText?.text.toString().isNotEmpty()) {
                if (volume.toDouble() in 100.0..1000.0) {

                    val volumeWaterItem = volume.toDouble()

                    viewModelItem.addItem(
                        TableItemStorage(
                            time = resultTime, volumeWater = volumeWaterItem,
                            typeDay = dateHelper.getDay()
                        )
                    )

                    // close dialog
                    dismiss()
                } else bindingDialog.waterInput.error = "от 100 до 1000 мл."

            } else bindingDialog.waterInput.error = "Пустое поле"


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