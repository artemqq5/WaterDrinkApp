package ppatsrrif.one.waterstate.mainPart.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ppatsrrif.one.waterstate.mainPart.roomDataBase.Repository
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableItemStorage
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableItemStorageWeek
import java.util.*

class ViewModelItem(application: Application) : AndroidViewModel(application) {

    val listWaterItem: LiveData<List<TableItemStorage>> =
        Repository.getInstance().getAllItem()

    val listWaterItemWeek: LiveData<List<TableItemStorageWeek>> =
        Repository.getInstance().getItemFromWeek()


    fun addItem(itemStorage: TableItemStorage) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.getInstance().addItem(itemStorage)
        }
    }

    fun deleteItem(id: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.getInstance().deleteItem(id)
        }
    }

}