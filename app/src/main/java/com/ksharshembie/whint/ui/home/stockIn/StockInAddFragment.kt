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
import com.ksharshembie.whint.local.room.SlipItem
import com.ksharshembie.whint.ui.article.ArticleEditFragment
import com.ksharshembie.whint.ui.scan.ScanFragment

class StockInAddFragment : Fragment() {

    private lateinit var binding: FragmentStockInAddBinding
    private lateinit var article: Article
    private lateinit var slipItem: SlipItem
    private var slipID: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStockInAddBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnScan.setOnClickListener {
            findNavController().navigate(R.id.scanFragment)
        }
        binding.btnExit.setOnClickListener {
            findNavController().navigateUp()
        }

        setFragmentResultListener(ScanFragment.SCAN_RESULT) { _, bundle ->
            binding.tvArticleCode.text = bundle.getString(ScanFragment.ARTICLE)
            articleDataCheck(bundle.getString(ScanFragment.ARTICLE).toString())
            binding.fabEdit.visibility = View.VISIBLE
        }
        binding.fabEdit.setOnClickListener {
            article = Article(
                binding.articleID.text.toString().toLong(),
                binding.tvArticleCode.text.toString()
            )
            setFragmentResult(
                ARTICLE_CODE, bundleOf(
                    CODE to article
                )
            )
            findNavController().navigate(R.id.articleEditFragment)
        }
        setFragmentResultListener(ArticleEditFragment.UPDATED_DATA) { _, bundle ->
            article = bundle.getSerializable(ArticleEditFragment.UPDATED_ARTICLE) as Article
            binding.apply {
                tvArticleCode.text = article.articleCode
                tvArticleName.text = article.articleName.toString()
                articleID.text = article.idArticle.toString()
            }
        }

        binding.btnAdd.setOnClickListener {
            if (binding.tvArticleCode.text.isNotEmpty() && binding.articleID.text.isNotEmpty()
                && binding.etQuantity.text.isNotEmpty() && binding.etArticlePrice.text.isNotEmpty()
            ) {
                slipItem = SlipItem(
                    idSlip = slipIDListener(),
                    idArticle = binding.articleID.text.toString().toLong(),
                    quantity = binding.etQuantity.text.toString().toInt(),
                    price = binding.etArticlePrice.text.toString().toLong(),
                    idDate = System.currentTimeMillis()
                )
                App.db.daoSlipItem().insert(slipItem)
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.item_saved),
                    Toast.LENGTH_LONG
                )
                    .show()
            } else {
                Toast.makeText(
                    requireActivity(),
                    getString(R.string.please_fill_all_fields),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    private fun slipIDListener(): Long {
        setFragmentResultListener(StockInFragment.SLIP_ID) { _, bundle ->
            slipID = bundle.getLong(StockInFragment.ID).toString().toLong()
        }
        return slipID
    }

    private fun articleDataCheck(article: String) {
        if (App.db.dao().isRowIsExist(article)) {
            binding.tvArticleName.text = App.db.dao().getArticle(article).articleName.toString()
            binding.articleID.text = App.db.dao().getArticle(article).idArticle.toString()
        }
    }

    companion object {
        const val ARTICLE_CODE = "article_code"
        const val CODE = "code"
    }

}