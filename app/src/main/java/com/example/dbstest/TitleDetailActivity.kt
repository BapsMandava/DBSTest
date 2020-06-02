package com.example.dbstest


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.dbstest.databinding.ActivityTitleDetailBinding
import com.example.dbstest.viewmodels.DataListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_title_detail.*


class TitleDetailActivity : BaseActivity() {

    private lateinit var dataListViewModel: DataListViewModel
    lateinit var binding: ActivityTitleDetailBinding;
    var title: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_title_detail)
        binding.executePendingBindings()
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        initialiseViewModel()
        var data = intent.getBundleExtra("data")
        title = data.getString("title").toString()
        getSupportActionBar()?.setTitle(title);
        getDataDetails(data.getInt("id"),data.getString("avatar"))
    }

    private fun initialiseViewModel() {
        dataListViewModel =
            ViewModelProviders.of(this).get(DataListViewModel(application)::class.java)
    }

    fun getDataDetails(id:Int,avatar:String?){
        if(hasNetwork()) {
            showProgressBar(true)
            dataListViewModel.getSelectedData(id).observe(this, Observer { selectionMsg ->
                Log.i(DataListActivity.TAG, "Viewmodel response: $selectionMsg")
                showProgressBar(false)
                selectionMsg?.let {
                    Glide.with(this).load(avatar).placeholder(R.drawable.ic_broken_image)
                        .into(ivAvatar)
                    binding.titleDetails = it
                }
            })
        } else {
            showNetworkMessage(hasNetwork())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onBackPressed() {

        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.edit ->{
                val intent = Intent(this, EditActivity::class.java).apply {
                    putExtra("summaryData", txtSummary.text.toString());
                    putExtra("editTitle",title)
                }
                startActivity(intent)
            }
            android.R.id.home -> {
                onBackPressed();
                return true;
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun showProgressBar(visibility: Boolean) {
        progress_bar1.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }
}
