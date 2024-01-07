package ppatsrrif.one.waterstate.presentation.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
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
import ppatsrrif.one.waterstate.databinding.DialogListItemWaterBinding
import ppatsrrif.one.waterstate.domain.repository.WaterRepository
import ppatsrrif.one.waterstate.domain.usecase.DateUseCase
import ppatsrrif.one.waterstate.presentation.home.recyclerView.AdapterListItemWater
import javax.inject.Inject


@AndroidEntryPoint
class DialogListItemWater : DialogFragment() {

    private lateinit var bindingDialog: DialogListItemWaterBinding

    private val adapterRecycler by lazy {
        AdapterListItemWater {}
    }

    @Inject
    lateinit var waterRepositoryImp: WaterRepository

    private val excCoroutine = CoroutineExceptionHandler { _, throwable ->
        Log.i("MyLog", "DialogListItemWater: $throwable")
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
        dialog?.window?.run {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }

        bindingDialog.recyclerItemWater.adapter = adapterRecycler

        adapterRecycler.listener = {
            lifecycleScope.launch(Dispatchers.IO + excCoroutine) {
                waterRepositoryImp.deleteWaterItem(it)
            }
        }

        waterRepositoryImp.getWaterItemByDate(
            DateUseCase().getCurrentStartDate(),
            DateUseCase().getCurrentEndDate()
        ).observe(viewLifecycleOwner) {
            adapterRecycler.setNewList(it)
        }


        // ads
//        val bannerView = AdView(requireContext())
//        view.findViewById<FrameLayout>(R.id.adViewContainer).addView(bannerView)
//        KeysAds().run {
//            loadBanner(bannerView, KeysAds.justBannerKey)
//        }


        // button close dialog
        bindingDialog.closeDialogButton.setOnClickListener {
            dismiss()
        }


    }

}