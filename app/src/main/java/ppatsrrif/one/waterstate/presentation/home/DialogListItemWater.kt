package ppatsrrif.one.waterstate.presentation.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ppatsrrif.one.waterstate.databinding.DialogListItemWaterBinding
import ppatsrrif.one.waterstate.presentation.home.recyclerView.AdapterListItemWater
import ppatsrrif.one.waterstate.presentation.viewmodel.WaterViewModel


@AndroidEntryPoint
class DialogListItemWater : DialogFragment() {

    private lateinit var bindingDialog: DialogListItemWaterBinding

    private val adapterRecycler by lazy {
        AdapterListItemWater {}
    }

    private val waterViewModel: WaterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // initializing binding
        bindingDialog = DialogListItemWaterBinding.inflate(inflater)

        return bindingDialog.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // set params for dialog
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        bindingDialog.recyclerItemWater.adapter = adapterRecycler

        adapterRecycler.listener = {
            waterViewModel.deleteWater(it)
        }

        waterViewModel.listCurrentWater.observe(viewLifecycleOwner) {
            adapterRecycler.setNewList(it)
        }

        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener {
            dismiss()
        }


    }

}