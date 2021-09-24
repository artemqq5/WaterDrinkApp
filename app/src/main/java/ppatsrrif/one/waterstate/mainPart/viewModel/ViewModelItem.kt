package ppatsrrif.one.waterstate.mainPart.viewModel

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ppatsrrif.one.waterstate.mainPart.roomDataBase.DataBase
import ppatsrrif.one.waterstate.mainPart.roomDataBase.Repository
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableItemStorage
import java.util.*

class ViewModelItem(application: Application) : AndroidViewModel(application) {

    val listWaterItem: LiveData<List<TableItemStorage>> =
        Repository.getInstance().getAllItem()

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