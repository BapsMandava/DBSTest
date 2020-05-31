package com.example.dbstest.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


import com.example.dbstest.models.DataRepo
import com.example.dbstest.repositories.DataRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class DataListViewModel(application: Application) : AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: DataRepository
    var data: MutableLiveData<List<DataRepo>> = MutableLiveData()
    private val mDisposables = CompositeDisposable()

    init {
        repository = DataRepository(application.baseContext)
    }

    fun getRepos(): LiveData<List<DataRepo>> {
        val observable: Observable<List<DataRepo>> = repository.getAllDataRepos()
        val result = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                data.postValue(result)
            }, { error ->
                error.printStackTrace()
            }, {
                //completed
            })
        mDisposables.add(result)
        return data
    }
}