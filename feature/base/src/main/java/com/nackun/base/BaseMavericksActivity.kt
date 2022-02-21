package com.nackun.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksView

abstract class BaseMavericksActivity<S : MavericksState, VM : BaseMavericksViewModel<S>> :
    AppCompatActivity(),
    MavericksView {

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeData()
    }

    abstract fun observeData()

    override fun onDestroy() {
        super.onDestroy()
    }
}
