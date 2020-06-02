package com.example.dbstest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.dbstest.viewmodels.DataListViewModel
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        var title = intent.getStringExtra("editTitle")
        var data = intent.getStringExtra("summaryData")
        getSupportActionBar()?.setTitle("Edit "+title)
        //getDataDetails(data.getInt("id"),data.getString("avatar"))
        txtEditText.setText(data)
        btnSave.setOnClickListener {
            finish()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onBackPressed() {

        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.cancel ->{
                    finish()
            }
            android.R.id.home -> {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
