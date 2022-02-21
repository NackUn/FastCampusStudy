package com.nackun.base

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel

abstract class BaseMavericksViewModel<S : MavericksState>(
    uiState: S
) : MavericksViewModel<S>(uiState)
