package com.example.dbstest

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbstest.adapters.DataListAdapter
import com.example.dbstest.models.DataRepo

import com.example.dbstest.network.ServiceGenerator
import com.example.dbstest.viewmodels.DataListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class DataListActivity : BaseActivity() {

    companion object {
        val TAG = DataListActivity::class.java.simpleName
    }

    private lateinit var dataListViewModel: DataListViewModel
    private lateinit var adapter: DataListAdapter
    private lateinit var dataRepoList: List<DataRepo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      //  setSupportActionBar(findViewById(R.id.dashboard_toolbar))

        ServiceGenerator(this)

        adapter = DataListAdapter(this,{ item -> doClick(item) })
        rv_repo.adapter = adapter
        rv_repo.layoutManager = LinearLayoutManager(this)
        rv_repo.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        // Get the view model
        initialiseViewModel()
        fetchDataRepos()
    }

    private fun initialiseViewModel() {
        dataListViewModel =
            ViewModelProviders.of(this).get(DataListViewModel(application)::class.java)
        dataListViewModel.getStatus().observe(this, Observer { handleStatus(it) })
    }

    private fun  fetchDataRepos() {
        showProgressBar(true)
        dataListViewModel.getRepos().observe(this, Observer { repoList ->
            Log.i(TAG, "Viewmodel response: $repoList")

            repoList?.let {

                showProgressBar(false)
                if (it.isNotEmpty()) {
                    empty_list.visibility = View.GONE
                    dataRepoList = it
                    adapter.clear()
                    adapter.setRepos(dataRepoList)
                } else {
                    empty_list.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun handleStatus(status: String?) {
        showProgressBar(false)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error Message")
        builder.setMessage(status)

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            dialog.dismiss()
        }
        builder.show()
    }


    override fun onResume() {
        super.onResume()
        showNetworkMessage(hasNetwork())
    }

    override fun onPause() {

        super.onPause()
    }


    fun doClick(data:Bundle){
        val intent = Intent(this, TitleDetailActivity::class.java).apply {
            putExtra("data", data)
        }
        startActivity(intent)
    }

    fun showProgressBar(visibility: Boolean) {
        progress_bar.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }

}
