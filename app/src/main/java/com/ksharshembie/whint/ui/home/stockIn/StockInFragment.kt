package com.ksharshembie.whint.ui.home.stockIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.App
import com.ksharshembie.whint.R
import com.ksharshembie.whint.databinding.FragmentStockInBinding

class StockInFragment : Fragment() {
    private lateinit var binding: FragmentStockInBinding

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
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.stockInAddFragment)
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnSave.setOnClickListener {
            findNavController().navigateUp()
        }

    }

}