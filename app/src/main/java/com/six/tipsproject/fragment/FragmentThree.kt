package com.six.tipsproject.fragment

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.six.tipsproject.R
import kotlinx.android.synthetic.main.frag_one.*

/**
 * @CopyRight six.ca
 * Created by Heavens on 2018-08-19.
 */
class FragmentThree: Fragment() {

    val LOCATION_REQUEST_CODE = 0x1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_one, container, false)!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_label.setText(R.string.label_fragment_three)
        btn_load.setText(R.string.label_request_loc)
        btn_load.setOnClickListener {
            getLocPermission()
        }
    }

    private fun getLocPermission() {
        if(context?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.ACCESS_FINE_LOCATION) } !=
                PackageManager.PERMISSION_GRANTED){
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)){
                AlertDialog.Builder(context)
                        .setMessage("Request Location Permission")
                        .setPositiveButton("Ok", { dialog, which ->
                            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
                        })
                        .create()
                        .show()
            } else {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        println("xxl-request-result")
        if(requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(context, "Loc Permission Granted", Toast.LENGTH_LONG).show()
            } else {
                if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    Toast.makeText(context, "Loc Permission Denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}