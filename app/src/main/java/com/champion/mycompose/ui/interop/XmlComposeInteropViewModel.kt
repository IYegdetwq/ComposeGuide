package com.champion.mycompose.ui.interop

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * XML 与 Compose 混合开发的 ViewModel
 *
 * 用于在 XML 和 Compose 之间共享状态
 */
class XmlComposeInteropViewModel : ViewModel() {
    // 计数器状态
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    // 用户名状态
    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    /**
     * 增加计数器
     */
    fun incrementCounter() {
        _counter.value++
    }

    /**
     * 更新用户名
     */
    fun updateUserName(name: String) {
        _userName.value = name
    }
}
