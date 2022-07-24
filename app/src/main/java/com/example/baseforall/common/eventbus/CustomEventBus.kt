package com.example.baseforall.eventbus

import com.example.baseforall.common.observables.BaseObservable


class CustomEventBus : BaseObservable<CustomEventBus.Listener?>() {
    interface Listener {
        fun onEvent(event: Any?)
    }

    fun postEvent(event: Any?) {
        for (listener in listeners) {
            listener?.onEvent(event)
        }
    }
}