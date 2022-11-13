package com.ksharshembie.whint.ui.home.stockIn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.App
import com.ksharshembie.whint.R
import com.ksharshembie.whint.databinding.FragmentStockInBinding
import com.ksharshembie.whint.local.room.Slip

class StockInFragment : Fragment() {
    private lateinit var binding: FragmentStockInBinding
    private lateinit var slip: Slip

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStockInBinding.inflate(LayoutInflater.from(context),container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        slip = Slip(
            idDate = System.currentTimeMillis()
        )

        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.stockInAddFragment)
            App.db.daoSlip().insert(slip)
            slip.idSlip = App.db.daoSlip().getID(slip.idDate).toString().toLong()
            setFragmentResult(SLIP_ID, bundleOf(ID to slip.idSlip))
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnSave.setOnClickListener {
            findNavController().navigateUp()
        }

    }

    companion object{
        const val SLIP_ID = "slipID"
        const val ID = "id"
    }


}