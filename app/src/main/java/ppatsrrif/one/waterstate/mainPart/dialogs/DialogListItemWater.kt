package ppatsrrif.one.waterstate.mainPart.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdView
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.adMob.KeysAds
import ppatsrrif.one.waterstate.databinding.DialogListItemWaterBinding
import ppatsrrif.one.waterstate.mainPart.recyclerView.AdapterListItemWater
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem


class DialogListItemWater : DialogFragment() {

    private lateinit var bindingDialog: DialogListItemWaterBinding
    private val adapterRecycler by lazy {
        AdapterListItemWater {
            viewModelItem.deleteItem(it)
        }
    }

    private val viewModelItem by lazy {
        ViewModelProvider(requireActivity())[ViewModelItem::class.java]
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

        // ads
        val bannerView = AdView(requireContext())
        view.findViewById<FrameLayout>(R.id.adViewContainer).addView(bannerView)
        KeysAds().run {
            loadBanner(
                bannerView, KeysAds.justBannerKey,
                adSize(requireActivity(), view.findViewById(R.id.adViewContainer), requireContext())
            )
        }

        // set params for dialog
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        // set adapter, layoutManager to RecyclerView
        bindingDialog.recyclerItemWater.run {
            adapter = adapterRecycler
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModelItem.date.observe(viewLifecycleOwner) {

            // set observer to RecyclerView
            viewModelItem.listSomeDay(it).observe(viewLifecycleOwner, { list ->
                adapterRecycler.setNewList(list)
            })

        }


        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener {
            dismiss()
        }


    }

}