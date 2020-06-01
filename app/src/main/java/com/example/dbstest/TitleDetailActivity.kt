package com.example.dbstest


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.dbstest.viewmodels.DataListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_title_detail.*


class TitleDetailActivity : BaseActivity() {

    private lateinit var dataListViewModel: DataListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title_detail)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        initialiseViewModel()
        var data = intent.getBundleExtra("data")
        getSupportActionBar()?.setTitle(data.getString("title"));
        getDataDetails(data.getInt("id"),data.getString("avatar"))
    }

    private fun initialiseViewModel() {
        dataListViewModel =
            ViewModelProviders.of(this).get(DataListViewModel(application)::class.java)
    }

    fun getDataDetails(id:Int,avatar:String?){
        showProgressBar(true)
        dataListViewModel.getSelectedData(id).observe(this, Observer { selectionMsg ->
            Log.i(DataListActivity.TAG, "Viewmodel response: $selectionMsg")

            selectionMsg?.let {
                Glide.with(this).load(avatar).placeholder(R.drawable.ic_broken_image).into(ivAvatar)
                txtSummary.setText(it.text)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.edit -> Toast.makeText(this, "Clicked Menu 1", Toast.LENGTH_SHORT).show()
            else -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showProgressBar(visibility: Boolean) {
        progress_bar1.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }
}
