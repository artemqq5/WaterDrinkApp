package ppatsrrif.one.waterstate.mainPart.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.ItemWaterViewBinding
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableItemStorage
import java.util.*
import kotlin.math.round


class AdapterListItemWater(private val listener: (index: UUID) -> Unit) :
    RecyclerView.Adapter<AdapterListItemWater.CustomViewHolder>() {

    private var listItemWater = listOf<TableItemStorage>()

    // class for init components of card
    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = ItemWaterViewBinding.bind(view)
        private lateinit var idItem: UUID

        fun initializing(model: TableItemStorage) {
            binding.timeText.text = model.time
            binding.volumeText.text = addWater(model.volumeWater).toString()

            idItem = model.id

            binding.openPopMenu.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0?.id) {
                R.id.openPopMenu -> {
                    openMenu(binding.openPopMenu)
                }
            }
        }

        private fun openMenu(v: View) {

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
                listener(idItem)
                popupWindow.dismiss()
            }

            popupWindow.setOnDismissListener {
                popupWindow.dismiss()
            }

            popupWindow.showAsDropDown(v, -200, 0)

        }


    }

    // fill item data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_water_view, parent,
            false
        )
        return CustomViewHolder(view)
    }

    // start init fun with ModelItemWater object
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.initializing(listItemWater[position])
    }

    override fun getItemCount(): Int = listItemWater.size

    fun setNewList(list: List<TableItemStorage>) {
        this.listItemWater = list
        notifyDataSetChanged()
    }

    // add volume to UI
    private fun addWater(volume: Double): Double {

        return round((volume / 1000.0) * 10) / 10.0

    }

}