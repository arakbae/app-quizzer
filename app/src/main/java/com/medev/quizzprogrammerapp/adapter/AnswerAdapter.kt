package com.medev.quizzprogrammerapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.medev.quizzprogrammerapp.R
import com.medev.quizzprogrammerapp.databinding.ItemAnswerBinding
import com.medev.quizzprogrammerapp.model.Answer

class AnswerAdapter:RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {

    private var answers = mutableListOf<Answer>()

   inner class ViewHolder(private val itemAnswerBinding: ItemAnswerBinding):
        RecyclerView.ViewHolder(itemAnswerBinding.root) {
        fun bindItem(answer: Answer) {
            itemAnswerBinding.tvOption.text = answer.option
            itemAnswerBinding.tvAnswer.text = answer.answer
            if (answer.isClick!!){
                activeCheckAnswer()
            }else{
                inActiveCheckAnswer()
            }
            itemView.setOnClickListener {
                for (i in 0 until answers.size){
                   answers[i].isClick = i == adapterPosition
                }
                notifyDataSetChanged()
            }
        }

        private fun inActiveCheckAnswer() {
            itemAnswerBinding.ivCheckAnswer.setImageResource(R.drawable.baseline_panorama_fish_eye_24)
        }

        private fun activeCheckAnswer() {
            itemAnswerBinding.ivCheckAnswer.setImageResource(R.drawable.baseline_check_circle_24)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAnswerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = answers.size

    fun setData(answer: MutableList<Answer>){
        this.answers = answers
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(answers[position])
    }
}