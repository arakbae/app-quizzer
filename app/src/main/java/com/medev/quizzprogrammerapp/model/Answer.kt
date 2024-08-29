package com.medev.quizzprogrammerapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer(

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("correct_answer")
	val correctAnswer: Boolean? = null,

	@field:SerializedName("is_click")
	val isClick: Boolean? = null,

	@field:SerializedName("option")
	val option: String? = null
) : Parcelable