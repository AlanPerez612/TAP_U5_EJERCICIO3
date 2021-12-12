package com.example.tap_u5_ejercicio3

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    var fraseEnviada= ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val permiso = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
        if(permiso == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.SEND_SMS),0)
        }
        val extra =intent!!.extras!!
        fraseEnviada = extra.getString("Frase sms").toString()
        campoSMS.setText(fraseEnviada)
        button2.setOnClickListener {
            SmsManager.getDefault().sendTextMessage(numerocelular.text.toString(),null,campoSMS.text.toString(),null,null)
        }
        button3.setOnClickListener {
            finish()
        }
    }
}