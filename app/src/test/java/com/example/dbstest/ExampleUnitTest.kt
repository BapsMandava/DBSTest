package com.example.dbstest

import android.os.AsyncTask.execute
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.dbstest.models.DataRepo
import com.example.dbstest.network.ServiceGenerator
import com.example.dbstest.repositories.DataRepository
import com.example.dbstest.viewmodels.DataListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    lateinit var dataListVM: DataListViewModel


    fun setUp(){
        dataListVM = mock(DataListViewModel::class.java)
    }



    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun login_Success() {
        setUp()
        var data: MutableLiveData<List<DataRepo>> = MutableLiveData()
        var api =   dataListVM.getRepos()
        try {
            assertEquals(api.value?.size,4)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
