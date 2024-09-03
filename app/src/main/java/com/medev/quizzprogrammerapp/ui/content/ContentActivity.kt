package com.medev.quizzprogrammerapp.ui.content
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.medev.quizzprogrammerapp.R
import com.medev.quizzprogrammerapp.adapter.ContentAdapter
import com.medev.quizzprogrammerapp.databinding.ActivityContentBinding
import com.medev.quizzprogrammerapp.model.Content
import com.medev.quizzprogrammerapp.repository.Repository

class ContentActivity : AppCompatActivity() {

    private lateinit var contentBinding: ActivityContentBinding
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var layoutmanager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityContentBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(contentBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Init
        contentAdapter = ContentAdapter()

        //Get data
        val contents = Repository.getDataContents(this)

        //Show data
        showDataContens(contents)
    }

    private fun showDataContens(contents: List<Content>?) {
        layoutmanager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper = PagerSnapHelper()

        if (contents != null){
            contentAdapter.setData(contents as MutableList<Content>)
        }

        snapHelper.attachToRecyclerView(contentBinding.rvContent)
        contentBinding.rvContent.layoutManager = layoutmanager
        contentBinding.rvContent.adapter = contentAdapter
    }
}