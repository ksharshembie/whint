package com.ksharshembie.whint.ui.home.stockIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.App
import com.ksharshembie.whint.R
import com.ksharshembie.whint.databinding.FragmentStockInBinding
import com.ksharshembie.whint.local.room.Slip
import com.ksharshembie.whint.local.room.SlipItem

class StockInFragment : Fragment() {
    private lateinit var binding: FragmentStockInBinding
    private var idSlip: Long = 0
    private lateinit var adapter: StockInAdapter
    private lateinit var list: List<SlipItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = StockInAdapter()
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
            val slip = Slip()
            App.db.daoSlip().insert(slip)
            idSlip = App.db.daoSlip().getID(slip.idDate).toString().toLong()
            setFragmentResult(SLIP_ID, bundleOf(ID to idSlip))
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnSave.setOnClickListener {
            findNavController().navigateUp()
        }
        list = App.db.daoSlipItem().getSlipItems(idSlip)
        binding.rvSlipItems.adapter = adapter
        adapter.addItem(list)

    }

    companion object {
        const val SLIP_ID = "slipID"
        const val ID = "id"
    }


}