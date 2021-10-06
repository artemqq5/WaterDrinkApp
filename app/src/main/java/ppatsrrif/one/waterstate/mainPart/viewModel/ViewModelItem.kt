package ppatsrrif.one.waterstate.mainPart.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ppatsrrif.one.waterstate.mainPart.roomDataBase.Repository
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableIItemStorageGoals
import ppatsrrif.one.waterstate.mainPart.roomDataBase.TableItemStorage
import java.util.*

class ViewModelItem(application: Application) : AndroidViewModel(application) {


    val date by lazy {
        MutableLiveData<String>()
    } 


    val listWaterItem: LiveData<List<TableItemStorage>> =
        Repository.getInstance().getAllItem()

    val listSomeDay: (type: String) -> LiveData<List<TableItemStorage>> = {
        Repository.getInstance().getSomeDay(it)
    }

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

    // delete dt storage
    fun deleteTStorage() {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.getInstance().deleteWeek()
        }
    }





    // get list of goals
    val listGoals: LiveData<List<TableIItemStorageGoals>> =
        Repository.getInstance().getGoals()


    // add goals
    fun addGoals(item: TableIItemStorageGoals) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.getInstance().addGoals(item)
        }
    }

    fun updateGoals(day: Int, status: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.getInstance().updateGoals(day, status)
        }
    }

    // delete dt goals
    fun deleteGoalsT() {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.getInstance().deleteGoals()
        }
    }




}