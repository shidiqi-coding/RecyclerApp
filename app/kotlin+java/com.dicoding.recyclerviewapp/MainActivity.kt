package com.dicoding.recyclerviewapp

//import android.util.Log
//import androidx.activity.enableEdgeToEdge


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//import java.nio.file.Files.size


//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat

class   MainActivity : AppCompatActivity() {
    private lateinit var rvAircraft: RecyclerView
    private val list = ArrayList<aircraft>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvAircraft = findViewById(R.id.rv_aircraft)
        rvAircraft.setHasFixedSize(true)

        list.addAll(getListAircraft())
        showRecyclerList()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvAircraft.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvAircraft.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

  @SuppressLint("Recycle")
    private fun getListAircraft(): ArrayList<aircraft> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto= resources.obtainTypedArray(R.array.data_photo)
        val listAircraft = ArrayList<aircraft>()
      

        val size = minOf(dataName.size, dataDescription.size, dataPhoto.length())
        for (i in 0 until size) {
            val aircraft = aircraft(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listAircraft.add(aircraft)
        }
//        for(i in dataName.indices){
//            val aircraft = aircraft(dataName[i],dataDescription[i],dataPhoto.getResourceId(i, -1))
//            listAircraft.add(aircraft)
//        }

        return listAircraft
    }
    private fun showRecyclerList() {
        rvAircraft.layoutManager = LinearLayoutManager(this)
        val listAircraftAdapter = ListAircraftAdapter(list)
        rvAircraft.adapter = listAircraftAdapter

        listAircraftAdapter.setOnItemClickCallBack(object :
            ListAircraftAdapter.OnItemClickCallback {
            override fun onItemclicked(data: aircraft) {
                showSelectedAircraft(data)
            }
        })
    }

    private fun showSelectedAircraft(aircraft: aircraft){
        Toast.makeText(this, "Anda Memilih " + aircraft.name, Toast.LENGTH_SHORT).show()
    }


}


