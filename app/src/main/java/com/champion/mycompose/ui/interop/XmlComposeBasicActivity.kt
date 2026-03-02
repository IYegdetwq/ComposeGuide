package com.champion.mycompose.ui.interop

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.champion.mycompose.R
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * XML 与 Compose 混合开发 - 基础示例
 *
 * 演示如何在传统 XML 布局中使用 ComposeView 嵌入 Compose 组件
 */
class XmlComposeBasicActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xml_compose_basic)

        // 设置 Compose 区域 1：展示简单的 Text 和 Card
        findViewById<ComposeView>(R.id.composeView1).setContent {
            MyComposeTheme {
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
                            text = "这是 Compose Text",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "使用 ComposeView 在 XML 中嵌入 Compose 组件",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }

        // 设置 XML 按钮点击事件：传统方式
        findViewById<Button>(R.id.xmlButton).setOnClickListener {
            Toast.makeText(this, "这是传统 XML 按钮点击事件", Toast.LENGTH_SHORT).show()
        }

        // 设置 Compose 区域 2：展示 Material3 Button 和 Icon
        findViewById<ComposeView>(R.id.composeView2).setContent {
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
                            text = "Compose Material3 组件",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Button(onClick = {
                                Toast.makeText(
                                    this@XmlComposeBasicActivity,
                                    "这是 Compose Button 点击事件",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                                Text("Compose 按钮")
                            }
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "喜欢",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}
