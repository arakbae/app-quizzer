package com.medev.quizzprogrammerapp.ui.prepare

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.medev.quizzprogrammerapp.R
import com.medev.quizzprogrammerapp.databinding.ActivityPrepareBinding

class PrepareActivity : AppCompatActivity() {

    private lateinit var prepareBinding: ActivityPrepareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareBinding = ActivityPrepareBinding.inflate(layoutInflater)
        setContentView(prepareBinding.root)
        enableEdgeToEdge()
        setContentView(R.layout.activity_prepare)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        onClick()
    }

    private fun onClick() {
        prepareBinding.btnStart.setOnClickListener{
            val nickname = prepareBinding.etNickname.text.toString()
            if(checkValidation(nickname)){

            }
        }
    }

    private fun checkValidation(nickname: String): Boolean {
        return if(nickname.isEmpty()){
            prepareBinding.etNickname.error = getString(R.string.plase_field_your_nickname)
            false
        }else{
            true
        }
    }
}