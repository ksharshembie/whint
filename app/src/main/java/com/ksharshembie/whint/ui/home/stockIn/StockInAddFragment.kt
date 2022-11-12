package com.ksharshembie.whint.ui.home.stockIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.R
import com.ksharshembie.whint.databinding.FragmentStockInAddBinding

class StockInAddFragment : Fragment() {

    private lateinit var binding: FragmentStockInAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStockInAddBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnScan.setOnClickListener {
                findNavController().navigate(R.id.scanFragment)
            }
            btnExit.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}