package com.ksharshembie.whint.ui.article

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.ksharshembie.whint.App
import com.ksharshembie.whint.databinding.FragmentArticleEditBinding
import com.ksharshembie.whint.local.room.Article

class ArticleEditFragment : Fragment() {
    private lateinit var binding: FragmentArticleEditBinding
    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleEditBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFragmentResultListener("article_code") { key, bundle ->
            article = bundle.getSerializable("code") as Article
        }

        binding.btnSave.setOnClickListener {
            articleDataEdit(article)
            findNavController().navigateUp()
        }
    }

    private fun articleDataEdit(article: Article) {
        article.articleName = binding.etArticleName.text.toString()
        App.db.dao().updateArticle(article)

    }

}