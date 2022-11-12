package com.ksharshembie.whint.ui.scan

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.ksharshembie.whint.App
import com.ksharshembie.whint.databinding.FragmentScanBinding
import com.ksharshembie.whint.local.room.Article

private const val CAMERA_REQUEST_CODE = 101

class ScanFragment : Fragment() {
    private lateinit var binding: FragmentScanBinding
    private lateinit var codeScanner: CodeScanner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentScanBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPermissions()
        codeScanner()
        viewVisibility()

    }


    private fun codeScanner() {
        codeScanner = CodeScanner(requireActivity(), binding.scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            requireActivity().runOnUiThread {
                if (!App.db.dao().isRowIsExist(it.text.toString())) {
                    App.db.dao().insert(
                        Article(
                            articleCode = it.text.toString()
                        )
                    )
                    Toast.makeText(
                        requireActivity(),
                        "New Article found: ${it.text}",
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    Toast.makeText(
                        requireActivity(),
                        "Scan result: ${it.text}",
                        Toast.LENGTH_LONG
                    ).show()

                }
                findNavController().navigateUp()
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            requireActivity().runOnUiThread {
                Toast.makeText(
                    requireActivity(), "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setupPermissions() {
        val permission: Int =
            ContextCompat.checkSelfPermission(requireActivity(), android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        requireActivity(),
                        "You need the camera permission to be able to use this app",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    //successfull
                }
            }
        }
    }

    private fun viewVisibility() {
        binding.btnManualAdd.setOnClickListener {
            binding.scannerView.visibility = View.GONE
            binding.btnManualAdd.visibility = View.GONE
            binding.etArticleCode.visibility = View.VISIBLE
            binding.btnAdd.visibility = View.VISIBLE
        }
    }
}