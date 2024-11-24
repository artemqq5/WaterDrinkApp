package ppatsrrif.one.waterstate.presentation.home.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.ItemWaterViewBinding
import ppatsrrif.one.waterstate.domain.repository.model.WaterModel
import ppatsrrif.one.waterstate.domain.usecase.DateUseCase
import ppatsrrif.one.waterstate.domain.usecase.VolumeUseCase
import java.util.*


class AdapterListItemWater(
    private var listItemWater: List<WaterModel> = emptyList(),
    private val dateUseCase: DateUseCase = DateUseCase(),
    private val volumeUseCase: VolumeUseCase = VolumeUseCase(),
    var listener: (WaterModel) -> Unit
) :
    RecyclerView.Adapter<AdapterListItemWater.CustomViewHolder>() {

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemWaterViewBinding.bind(view)

        fun initializing(model: WaterModel) {

            when(model.volumeWater) {
                in 0.0..200.0 -> binding.imageView.setImageResource(R.drawable.w200ml)
                in 201.0..250.0 -> binding.imageView.setImageResource(R.drawable.w250ml)
                in 251.0..300.0 -> binding.imageView.setImageResource(R.drawable.w300ml)
                in 301.0..500.0 -> binding.imageView.setImageResource(R.drawable.w500ml)
                else -> binding.imageView.setImageResource(R.drawable.w1000ml)
            }

            binding.timeText.text = dateUseCase.getFormatTimeForLocale(model.date)
            binding.volumeText.text = itemView.resources.getString(
                R.string.volume_ml,
                model.volumeWater.toString()
            )

            binding.openPopMenu.setOnClickListener {
                openMenu(binding.openPopMenu, model)
            }
        }

        private fun openMenu(v: View, model: WaterModel) {

            val popupWindow = PopupWindow(v.context)
            val view = LayoutInflater.from(v.context).inflate(R.layout.popup_window, null)
            popupWindow.contentView = view

            popupWindow.isTouchable = true
            popupWindow.isFocusable = true
            popupWindow.isOutsideTouchable = true

            popupWindow.setBackgroundDrawable(null)
            popupWindow.elevation = 8.0f

            val textView = view.findViewById<TextView>(R.id.popMenu)

            textView.setOnClickListener {
                listener(model)
                popupWindow.dismiss()
            }

            popupWindow.setOnDismissListener {
                popupWindow.dismiss()
            }

            popupWindow.showAsDropDown(v, -200, 0)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_water_view, parent,
            false
        )
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.initializing(listItemWater[position])
    }

    override fun getItemCount(): Int = listItemWater.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewList(list: List<WaterModel>) {
        listItemWater = list.reversed()
        notifyDataSetChanged()
    }

}