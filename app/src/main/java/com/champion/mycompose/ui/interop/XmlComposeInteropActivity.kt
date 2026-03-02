package com.champion.mycompose.ui.interop

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.champion.mycompose.R
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * XML 与 Compose 混合开发 - 中等复杂度示例
 *
 * 演示三个场景：
 * 1. XML → Compose 数据传递
 * 2. Compose → XML 事件回调
 * 3. ViewModel 状态管理
 */
class XmlComposeInteropActivity : ComponentActivity() {

    // 使用 viewModels() 委托创建 ViewModel
    private val viewModel: XmlComposeInteropViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_compose_interop)

        setupScene1()
        setupScene2()
        setupScene3()
    }

    /**
     * 场景1：XML → Compose 数据传递
     *
     * 从 XML 的 EditText 获取输入，点击按钮后传递给 Compose 组件显示
     */
    private fun setupScene1() {
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val updateButton = findViewById<Button>(R.id.updateButton)
        val composeViewDisplay = findViewById<ComposeView>(R.id.composeViewDisplay)

        // 初始化 Compose 视图
        composeViewDisplay.setContent {
            MyComposeTheme {
                // 使用 remember 保存显示的名字
                var displayName by remember { mutableStateOf("") }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Compose 显示区域",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = if (displayName.isEmpty()) {
                                "等待输入..."
                            } else {
                                "你好，$displayName！"
                            },
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

                // 监听 ViewModel 的用户名变化并更新显示
                val userName by viewModel.userName.collectAsState()
                displayName = userName
            }
        }

        // XML 按钮点击事件：获取输入并更新 ViewModel
        updateButton.setOnClickListener {
            val name = nameInput.text.toString()
            viewModel.updateUserName(name)
        }
    }

    /**
     * 场景2：Compose → XML 事件回调
     *
     * Compose 组件中的按钮点击后，通过回调通知 XML 层更新 TextView
     */
    private fun setupScene2() {
        val callbackResult = findViewById<TextView>(R.id.callbackResult)
        val composeViewCallback = findViewById<ComposeView>(R.id.composeViewCallback)

        composeViewCallback.setContent {
            MyComposeTheme {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Compose 交互区域",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        // 使用 remember 保存点击次数
                        var clickCount by remember { mutableStateOf(0) }

                        Button(onClick = {
                            clickCount++
                            // 回调到 XML 层：更新 TextView
                            callbackResult.text = "Compose 按钮被点击了 $clickCount 次"
                        }) {
                            Text("点击我触发回调")
                        }
                    }
                }
            }
        }
    }

    /**
     * 场景3：ViewModel 状态管理
     *
     * 展示 Compose 组件如何通过 ViewModel 管理状态
     * XML 和 Compose 共享同一个 ViewModel
     */
    private fun setupScene3() {
        val composeViewState = findViewById<ComposeView>(R.id.composeViewState)

        composeViewState.setContent {
            MyComposeTheme {
                // 从 ViewModel 收集状态
                val counter by viewModel.counter.collectAsState()

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ViewModel 状态管理",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "计数器：$counter",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(onClick = {
                                // 通过 ViewModel 更新状态
                                viewModel.incrementCounter()
                            }) {
                                Text("增加")
                            }

                            Button(onClick = {
                                // 重置计数器
                                viewModel.updateUserName("")
                                // 注意：这里演示了 ViewModel 可以被 XML 和 Compose 共享
                            }) {
                                Text("重置姓名")
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "ViewModel 在 XML 和 Compose 之间共享状态",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }
            }
        }
    }
}
