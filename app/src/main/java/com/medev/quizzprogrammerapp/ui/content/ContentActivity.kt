package com.medev.quizzprogrammerapp.ui.content
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.medev.quizzprogrammerapp.R
import com.medev.quizzprogrammerapp.adapter.ContentAdapter
import com.medev.quizzprogrammerapp.databinding.ActivityContentBinding
import com.medev.quizzprogrammerapp.model.Content
import com.medev.quizzprogrammerapp.repository.Repository

class ContentActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_NICKNAME = "extra_nickname"
        const val EXTRA_CONTENTS = "extra_contents"
    }

    private lateinit var contentBinding: ActivityContentBinding
    private lateinit var contentAdapter: ContentAdapter
    private lateinit var layoutmanager: LinearLayoutManager
    private var dataSize = 0
    private var currentPosition = 0
    private var nickname:String? = null


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
        if(intent != null){
            nickname = intent.getStringExtra(EXTRA_NICKNAME)
        }

        if (savedInstanceState != null){
            nickname = savedInstanceState.getString(EXTRA_NICKNAME)
            val contents = savedInstanceState.getParcelableArrayList<Content>(EXTRA_CONTENTS)
            showDataContens(contents)
        }else{
            //Get data from repository
            val contents = Repository.getDataContents(this)
            //Show data
            showDataContens(contents)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_NICKNAME, nickname)
        val contents = contentAdapter.getResults()
        outState.putParcelableArrayList(EXTRA_CONTENTS, contents as ArrayList)
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

        dataSize = layoutmanager.itemCount
        contentBinding.pbContent.max = dataSize - 1

        //First show index
        var index = "${currentPosition + 1} / $dataSize"
        contentBinding.tvIndexContent.text = index

        contentBinding.rvContent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                currentPosition = layoutmanager.findFirstVisibleItemPosition()
                index = "${currentPosition + 1} / $dataSize"
                contentBinding.tvIndexContent.text = index
                contentBinding.pbContent.progress = currentPosition
            }
        })
    }
}