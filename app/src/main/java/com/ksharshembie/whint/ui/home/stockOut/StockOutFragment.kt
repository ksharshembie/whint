package com.ksharshembie.whint.ui.home.stockOut

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.ksharshembie.whint.App
import com.ksharshembie.whint.databinding.FragmentStockOutBinding
import com.ksharshembie.whint.local.room.StockCount

class StockOutFragment : Fragment() {
    private lateinit var binding: FragmentStockOutBinding
    private lateinit var option: Spinner
    private var stockID: Long = 0
    private lateinit var adapter: StockOutAdaptor
    private lateinit var list: List<StockCount>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("StockOut","OnCreate run")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("StockOut","OnCreateView run")
        binding = FragmentStockOutBinding.inflate(LayoutInflater.from(context),container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("StockOut","OnViewCreated run")
        getStocks()
    }

    /*private fun setStockID(): Long {
        val options = App.db.daoStock().getStockNames()
        var id: Long = 0
        option = binding.spStore
        option.adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            options
        )
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                id = App.db.daoStock().getStockID(option.adapter.getItem(p2).toString())
                Log.e("StockOut","Select StockID: ${id}")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
        return id
    }*/

    private fun getStocks(){
        val options = App.db.daoStock().getStockNames()
        option = binding.spStore
        option.adapter = ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            options
        )
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                stockID = App.db.daoStock().getStockID(option.adapter.getItem(p2).toString())
                Log.e("StockOut","Selected StockID: ${stockID}")
                Log.e("StockOut","stockID: ${stockID}")
                list = App.db.daoStockCount().getAllStockByID(stockID)
                Log.e("StockOut","Stock with ID ${stockID} from Room : ${list}")
                adapter = StockOutAdaptor()
                binding.rvStockOut.adapter = adapter
                adapter.addItem(list)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }

    }
}