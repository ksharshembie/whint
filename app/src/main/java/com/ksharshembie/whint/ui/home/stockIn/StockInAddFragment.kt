package com.ksharshembie.whint.ui.home.stockIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.App
import com.ksharshembie.whint.R
import com.ksharshembie.whint.databinding.FragmentStockInAddBinding
import com.ksharshembie.whint.local.room.Article

class StockInAddFragment : Fragment() {

    private lateinit var binding: FragmentStockInAddBinding
    private lateinit var article: Article

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
        setFragmentResultListener("scan_result") { key, bundle ->
            binding.tvArticleCode.text = bundle.getString("article")
            articleDataCheck(bundle.getString("article").toString())
        }
        binding.fabEdit.setOnClickListener {
            article = Article(binding.articleID.text.toString().toLong(),binding.tvArticleCode.text.toString())
            setFragmentResult(
                    "article_code", bundleOf(
                    "code" to article
                )
            )
            findNavController().navigate(R.id.articleEditFragment)
        }
    }

    private fun articleDataCheck(article: String) {
        if (App.db.dao().isRowIsExist(article)) {
            binding.tvArticleName.text = App.db.dao().getArticle(article).articleName.toString()
            binding.articleID.text = App.db.dao().getArticle(article).idArticle.toString()
        } else {

        }
    }


}