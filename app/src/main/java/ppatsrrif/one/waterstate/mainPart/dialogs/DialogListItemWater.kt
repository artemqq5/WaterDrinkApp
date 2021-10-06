package ppatsrrif.one.waterstate.mainPart.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ppatsrrif.one.waterstate.databinding.DialogListItemWaterBinding
import ppatsrrif.one.waterstate.mainPart.helperClasses.DateHelper
import ppatsrrif.one.waterstate.mainPart.recyclerView.AdapterListItemWater
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem

class DialogListItemWater : DialogFragment() {

    private lateinit var bindingDialog: DialogListItemWaterBinding
    private lateinit var adapterRecycler: AdapterListItemWater

    private lateinit var viewModelItem: ViewModelItem

    private val dateHelper by lazy {
        DateHelper()
    }

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
        dialog?.setCancelable(false)
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        // initializing ViewModelItem
        viewModelItem = ViewModelProvider(requireActivity())[ViewModelItem::class.java]



        // create adapter
        adapterRecycler = AdapterListItemWater {
            viewModelItem.deleteItem(it)
        }


        // set adapter, layoutManager to RecyclerView
        bindingDialog.recyclerItemWater.run {
            adapter = adapterRecycler
            layoutManager = LinearLayoutManager(requireContext())
        }


        // set observer to RecyclerView
        viewModelItem.listSomeDay(dateHelper.getDay()).observe(viewLifecycleOwner, Observer { list ->
            adapterRecycler.setNewList(list)
        })

        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener{
            dismiss()
        }



    }

    override fun onResume() {
        super.onResume()

        // set observer to RecyclerView
        viewModelItem.listSomeDay(dateHelper.getDay()).observe(viewLifecycleOwner, Observer { list ->
            adapterRecycler.setNewList(list)
        })
    }

}