/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations.data

import android.annotation.SuppressLint
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

/**
 * [Datasource] generates a list of [Affirmation]
 */
class Datasource() {
    @SuppressLint("ResourceType")
    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.sunflower,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.sunflower),
            Affirmation(R.string.affirmation2, R.drawable.blinding,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.weekend),
            Affirmation(R.string.affirmation3, R.drawable.tmd,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.threeman),
            Affirmation(R.string.affirmation4, R.drawable.bruno,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.bruno),
            Affirmation(R.string.affirmation5, R.drawable.lilnasx,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.lilnas),
            Affirmation(R.string.affirmation6, R.drawable.image6,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.song),
//            Affirmation(R.string.affirmation7, R.drawable.image7,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.song),
//            Affirmation(R.string.affirmation8, R.drawable.image8,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.song),
//            Affirmation(R.string.affirmation9, R.drawable.image9,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.song),
//            Affirmation(R.string.affirmation10, R.drawable.image10,R.drawable.baseline_play_arrow_24,R.drawable.pause,R.raw.song)

        )
    }

}
