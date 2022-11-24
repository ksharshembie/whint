package com.ksharshembie.whint.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.App
import com.ksharshembie.whint.R
import com.ksharshembie.whint.databinding.FragmentHomeBinding
import com.ksharshembie.whint.local.room.Stock

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var stock: List<Stock>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        systemStockCheck()
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStockIn.setOnClickListener {
            findNavController().navigate(R.id.stockInFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun systemStockCheck() {
        if (App.db.daoStock().isSystemStockExist()) {
            stock = App.db.daoStock().getSystemStock()
        } else {
            val stock01 = Stock(
                idStock = 0,
                code = "01",
                name = getString(R.string.main_stock),
                isSystemStock = true
            )
            val stock02 = Stock(
                idStock = 0,
                code = "02",
                name = getString(R.string.damaged_stock),
                isSystemStock = true
            )
            val stock03 = Stock(
                idStock = 0,
                code = "03",
                name = getString(R.string.return_stock),
                isSystemStock = true
            )
            App.db.daoStock().insert(listOf(stock01, stock02, stock03))
        }
    }

}