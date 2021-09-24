package ppatsrrif.one.waterstate.mainPart.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import ppatsrrif.one.waterstate.R
import ppatsrrif.one.waterstate.databinding.ItemWaterViewBinding
import ppatsrrif.one.waterstate.mainPart.dialogs.DialogListItemWater
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableItemStorage
import ppatsrrif.one.waterstate.mainPart.viewModel.ViewModelItem
import java.util.*

class AdapterListItemWater(viewModelItem: ViewModel) :
    RecyclerView.Adapter<AdapterListItemWater.CustomViewHolder>() {


    var viewModelItem = viewModelItem as ViewModelItem
    private var listItemWater = listOf<TableItemStorage>()

    // class for init components of card
    class CustomViewHolder(view: View, var viewModelItem: ViewModelItem) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = ItemWaterViewBinding.bind(view)
        private lateinit var idItem: UUID

        fun initializing(model: TableItemStorage) {
            binding.timeText.text = model.time
            binding.volumeText.text = model.volumeWater.toString()

            idItem = model.id

            binding.card.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when(p0?.id) {
                R.id.card -> {

                    viewModelItem.deleteItem(idItem)

                }

            }
        }


    }

    // fill item data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_water_view, parent,
            false)
        return CustomViewHolder(view, viewModelItem)
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

}