package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
@StringRes
val stringResourceId: Int,
@DrawableRes
val imageResourceId:Int,
@DrawableRes
val iconResourceId:Int,
@DrawableRes
val iconPause:Int,
@DrawableRes
val soundRes:Int,

)
