package ppatsrrif.one.waterstate.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

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