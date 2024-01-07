package ppatsrrif.one.waterstate.presentation.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ppatsrrif.one.waterstate.repository.RepositoryImp
import ppatsrrif.one.waterstate.repository.database.table.TableIItemStorageGoals
import ppatsrrif.one.waterstate.repository.database.table.TableItemStorage
import java.util.*

class ViewModelItem(application: Application) : AndroidViewModel(application) {


    val date by lazy {
        MutableLiveData<Int>()
    }


//    val listWaterItem: LiveData<List<TableItemStorage>> =
//        RepositoryImp.getInstance().getAllItem()
//
//    val listSomeDay: (type: Int) -> LiveData<List<TableItemStorage>> = {
//        RepositoryImp.getInstance().getSomeDay(it)
//    }
//
//    fun addItem(itemStorage: TableItemStorage) {
//        viewModelScope.launch(Dispatchers.IO) {
//            RepositoryImp.getInstance().addItem(itemStorage)
//        }
//    }
//
//    fun deleteItem(id: UUID) {
//        viewModelScope.launch(Dispatchers.IO) {
//            RepositoryImp.getInstance().deleteItem(id)
//        }
//    }
//
//    // delete dt storage
//    fun deleteTStorage() {
//        viewModelScope.launch(Dispatchers.IO) {
//            RepositoryImp.getInstance().deleteWeek()
//        }
//    }
//
//
//    // get list of goals
//    val listGoals: LiveData<List<TableIItemStorageGoals>> =
//        RepositoryImp.getInstance().getGoals()
//
//
//    // add goals
//    fun addGoals(item: TableIItemStorageGoals) {
//        viewModelScope.launch(Dispatchers.IO) {
//            RepositoryImp.getInstance().addGoals(item)
//        }
//    }
//
//    fun updateGoals(day: Int, status: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            RepositoryImp.getInstance().updateGoals(day, status)
//        }
//    }
//
//    // delete dt goals
//    fun deleteGoalsT() {
//        viewModelScope.launch(Dispatchers.IO) {
//            RepositoryImp.getInstance().deleteGoals()
//        }
//    }


}