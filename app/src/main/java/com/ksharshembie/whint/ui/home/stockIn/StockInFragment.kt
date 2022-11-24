package com.ksharshembie.whint.ui.home.stockIn

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.App
import com.ksharshembie.whint.R
import com.ksharshembie.whint.databinding.FragmentStockInBinding
import com.ksharshembie.whint.local.room.Slip
import com.ksharshembie.whint.local.room.SlipItem
import com.ksharshembie.whint.local.room.StockCount
import java.util.Calendar

class StockInFragment : Fragment() {
    private lateinit var binding: FragmentStockInBinding
    private var idSlip: Long = 0
    private lateinit var adapter: StockInAdapter
    private lateinit var list: List<SlipItem>
    private val calendar = Calendar.getInstance()
    private val year = calendar.get(Calendar.YEAR)
    private val month = calendar.get(Calendar.MONTH)
    private val day = calendar.get(Calendar.DAY_OF_MONTH)
    private lateinit var option: Spinner
    private var stockID: Long = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = StockInAdapter()
        val slip = Slip()
        App.db.daoSlip().insert(slip)
        idSlip = App.db.daoSlip().getID(slip.idDate).toString().toLong()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStockInBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.stockInAddFragment)
            setFragmentResult(SLIP_ID, bundleOf(ID to idSlip))
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        setStockID()
        list = App.db.daoSlipItem().getSlipItems(idSlip)
        Log.e("SlipItem", "SlipItems: ${list}}")
        binding.rvSlipItems.adapter = adapter
        adapter.addItem(list)
        slipDatePicker()
        totalCalculate(list)
        binding.btnSave.setOnClickListener {
            saveSlip(
                idSlip,
                binding.etSlipNumber.text.toString(),
                binding.tvSlipDate.text.toString(),
                binding.tvNetAmount.text.toString(),
                stockID
            )
        }
    }


    private fun slipDatePicker() {
        //Calendar
        binding.btnCalendar.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { _, mYear, mMonth, mDay ->
                    binding.tvSlipDate.setText(
                        "$mDay/$mMonth/$mYear"
                    )
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
    }

    companion object {
        const val SLIP_ID = "slipID"
        const val ID = "id"
    }

    override fun onDestroy() {
        super.onDestroy()
        isSlipSaved(idSlip)
    }

    private fun isSlipSaved(slipID: Long) {
        if (App.db.daoSlip().isSlipExist(slipID) && !App.db.daoSlip().isSlipSaved(slipID)) {
            if (App.db.daoSlipItem().isSlipItemExist(slipID)) {
                App.db.daoSlipItem().deleteSlipItem(slipID)
            }
            App.db.daoSlip().deleteSlip(slipID)
        }
    }

    private fun saveSlip(
        slipID: Long,
        docNumber: String,
        docDate: String,
        netAmount: String,
        stockID: Long
    ) {
        if (!App.db.daoSlipItem().isSlipItemExist(slipID)) {
            showToast(getString(R.string.no_item_added_please_add))
        } else if (docNumber.isEmpty()) {
            showToast(getString(R.string.please_fill_doc_number))
        } else if (docDate == getString(R.string.document_date)) {
            showToast(getString(R.string.please_select_doc_date))
        } else {
            App.db.daoSlip().slipSaved(slipID, docNumber, docDate, netAmount, 1, stockID)
            stockIn(slipID)
            findNavController().navigateUp()
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(
            requireActivity(),
            text,
            Toast.LENGTH_LONG
        )
            .show()
    }

    private fun totalCalculate(list: List<SlipItem>) {
        var sum: Long = 0
        var quantity: Long = 0
        list.forEach {
            sum += it.price.toString().toLong() * it.quantity.toString().toLong()
            quantity += it.quantity.toString().toLong()
        }
        binding.apply {
            tvNetAmount.text = sum.toString()
            tvQuantity.text = quantity.toString()
        }

    }


    private fun setStockID() {
        val options = App.db.daoStock().getAllStock()
        option = binding.spStore
        option.adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            options
        )
        option.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                stockID = App.db.daoStock().getStockID(option.adapter.getItem(p2).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }

        }
    }

    private fun stockIn(slipID: Long) {

        val listSlipItem: List<SlipItem>
        var stock: StockCount
        val stockList = ArrayList<StockCount>()
        var stockExists: StockCount
        var stockIncreased: StockCount

        listSlipItem = App.db.daoSlipItem().getSlipItems(slipID)
        listSlipItem.forEach {
            stock = StockCount(null, it.idArticle, stockID, it.quantity)
            stockList.add(stock)
        }
        stockList.forEach {
            if (!App.db.daoStockCount().isStockExist(it.idArticle, it.idStock)) {
                App.db.daoStockCount().insert(it)
            } else {
                stockExists = App.db.daoStockCount().existStock(it.idArticle, it.idStock)
                stockIncreased = StockCount(stockExists.idStocks, stockExists.idArticle, stockExists.idStock, it.quantity + stockExists.quantity)
                App.db.daoStockCount().inreaseStocks(stockIncreased.idStocks, stockIncreased.quantity)
            }

        }
    }
}