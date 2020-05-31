package com.example.dbstest.network

import com.example.dbstest.models.DataRepo
import io.reactivex.Observable


class ApiClient {
    fun getDataRepos(): Observable<List<DataRepo>> {
        return ServiceGenerator.instance.getDataRepoApi().getDataRepos()
    }

}