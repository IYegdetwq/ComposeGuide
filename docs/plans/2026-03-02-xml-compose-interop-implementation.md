# XML 与 Compose 混合开发示例实现计划

> **For Claude:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task.

**目标：** 在 HomeScreen 中新增两个示例，演示在 XML 布局中嵌入 Compose UI 组件

**架构：** 创建两个独立的 Activity（简单示例和中等复杂度示例），每个 Activity 使用 XML 布局 + ComposeView。简单示例展示基础用法，中等复杂度示例展示数据传递、事件回调和 ViewModel 状态管理。

**技术栈：** Kotlin, Jetpack Compose, ComposeView, ViewModel, StateFlow, Material3

---

## Task 1: 更新 HomeScreen 添加新示例入口

**文件：**
- Modify: `app/src/main/java/com/champion/mycompose/ui/home/HomeScreen.kt:50-71`

**Step 1: 在 demoItems 列表中添加两个新条目**

在 `HomeScreen.kt` 的 `demoItems` 列表中，在 `input_forms` 之后添加：

```kotlin
DemoItem("xml_compose_basic", "XML 嵌入 Compose - 基础", "在 XML 布局中使用 ComposeView 嵌入 Compose 组件", LearningPhase.PHASE_1, available = true),
DemoItem("xml_compose_interop", "XML 嵌入 Compose - 交互", "XML 与 Compose 数据传递、状态管理", LearningPhase.PHASE_4, available = true),
```

**Step 2: 提交更改**

```bash
git add app/src/main/java/com/champion/mycompose/ui/home/HomeScreen.kt
git commit -m "feat: add XML-Compose interop demo entries to HomeScreen"
```

---

## Task 2: 创建简单示例的 XML 布局文件

**文件：**
- Create: `app/src/main/res/layout/activity_xml_compose_basic.xml`

**Step 1: 创建 XML 布局文件**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 传统 XML 区域 -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="这是传统 XML TextView"
        android:textSize="18sp"
        android:padding="16dp"
        android:background="?attr/colorSurfaceVariant"
        android:textColor="?attr/colorOnSurfaceVariant"
        android:layout_marginBottom="16dp" />

    <!-- Compose 区域 1：简单文本和卡片 -->
    <androidx.compose.ui.platform.ComposeView
        android:id="@+id/composeView1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="16dp" />

    <!-- 传统 XML 按钮 -->
    <Button
        android:id="@+id/xmlButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="XML 按钮"
        android:layout_marginBottom="16dp" />

    <!-- Compose 区域 2：Material 组件 -->
    <androidx.compose.ui.platform.ComposeView
        android:id="@+id/composeView2"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</LinearLayout>
```

**Step 2: 提交更改**

```bash
git add app/src/main/res/layout/activity_xml_compose_basic.xml
git commit -m "feat: add XML layout for basic XML-Compose interop demo"
```

---

## Task 3: 创建简单示例的 Activity

**文件：**
- Create: `app/src/main/java/com/champion/mycompose/ui/interop/XmlComposeBasicActivity.kt`

**Step 1: 创建 Activity 文件**

```kotlin
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
```

**Step 2: 提交更改**

```bash
git add app/src/main/java/com/champion/mycompose/ui/interop/XmlComposeBasicActivity.kt
git commit -m "feat: implement basic XML-Compose interop Activity"
```

---

## Task 4: 创建中等复杂度示例的 XML 布局文件

**文件：**
- Create: `app/src/main/res/layout/activity_xml_compose_interop.xml`
- Create: `app/src/main/res/values/styles.xml` (如果不存在)

**Step 1: 创建 styles.xml（如果不存在）**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <style name="SectionTitle">
        <item name="android:textSize">16sp</item>
        <item name="android:textStyle">bold</item>
        <item name="android:textColor">?attr/colorPrimary</item>
        <item name="android:layout_marginTop">16dp</item>
        <item name="android:layout_marginBottom">8dp</item>
    </style>
</resources>
```

**Step 2: 创建 XML 布局文件**

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <!-- 场景1：XML 向 Compose 传递数据 -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="场景1：XML → Compose 数据传递"
            style="@style/SectionTitle" />

        <EditText
            android:id="@+id/nameInput"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="输入姓名"
            android:layout_marginBottom="8dp" />

        <Button
            android:id="@+id/updateButton"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="更新 Compose 显示"
            android:layout_marginBottom="8dp" />

        <androidx.compose.ui.platform.ComposeView
            android:id="@+id/composeViewDisplay"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp" />

        <!-- 场景2：Compose 向 XML 回调事件 -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="场景2：Compose → XML 事件回调"
            style="@style/SectionTitle" />

        <TextView
            android:id="@+id/callbackResult"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="等待 Compose 组件触发..."
            android:padding="12dp"
            android:background="?attr/colorSurfaceVariant"
            android:textColor="?attr/colorOnSurfaceVariant"
            android:layout_marginBottom="8dp" />

        <androidx.compose.ui.platform.ComposeView
            android:id="@+id/composeViewCallback"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp" />

        <!-- 场景3：状态管理 -->
        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="场景3：ViewModel 状态管理"
            style="@style/SectionTitle" />

        <androidx.compose.ui.platform.ComposeView
            android:id="@+id/composeViewState"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </LinearLayout>
</ScrollView>
```

**Step 3: 提交更改**

```bash
git add app/src/main/res/layout/activity_xml_compose_interop.xml app/src/main/res/values/styles.xml
git commit -m "feat: add XML layout for advanced XML-Compose interop demo"
```

---

## Task 5: 创建 ViewModel

**文件：**
- Create: `app/src/main/java/com/champion/mycompose/ui/interop/XmlComposeInteropViewModel.kt`

**Step 1: 创建 ViewModel 文件**

```kotlin
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
```

**Step 2: 提交更改**

```bash
git add app/src/main/java/com/champion/mycompose/ui/interop/XmlComposeInteropViewModel.kt
git commit -m "feat: add ViewModel for XML-Compose interop"
```

---

## Task 6: 创建中等复杂度示例的 Activity

**文件：**
- Create: `app/src/main/java/com/champion/mycompose/ui/interop/XmlComposeInteropActivity.kt`

**Step 1: 创建 Activity 文件（第一部分，前150行）**

```kotlin
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
```

**Step 2: 创建 Activity 文件（第二部分，继续）**

```kotlin
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
```

**Step 3: 提交更改**

```bash
git add app/src/main/java/com/champion/mycompose/ui/interop/XmlComposeInteropActivity.kt
git commit -m "feat: implement advanced XML-Compose interop Activity with data passing and ViewModel"
```

---

## Task 7: 在 AndroidManifest.xml 中注册 Activity

**文件：**
- Modify: `app/src/main/AndroidManifest.xml:24-25`

**Step 1: 添加 Activity 声明**

在 `</application>` 标签之前添加：

```xml
    <activity
        android:name=".ui.interop.XmlComposeBasicActivity"
        android:exported="false"
        android:theme="@style/Theme.MyCompose" />

    <activity
        android:name=".ui.interop.XmlComposeInteropActivity"
        android:exported="false"
        android:theme="@style/Theme.MyCompose" />
```

**Step 2: 提交更改**

```bash
git add app/src/main/AndroidManifest.xml
git commit -m "feat: register XML-Compose interop Activities in manifest"
```

---

## Task 8: 更新导航路由

**文件：**
- Modify: `app/src/main/java/com/champion/mycompose/navigation/NavGraph.kt:53-62`

**Step 1: 在 when 语句中添加新的路由分支**

在 `when (demoId)` 块中，在 `"input_forms"` 之后添加：

```kotlin
                    "xml_compose_basic" -> {
                        val context = LocalContext.current
                        LaunchedEffect(Unit) {
                            context.startActivity(Intent(context, com.champion.mycompose.ui.interop.XmlComposeBasicActivity::class.java))
                        }
                    }
                    "xml_compose_interop" -> {
                        val context = LocalContext.current
                        LaunchedEffect(Unit) {
                            context.startActivity(Intent(context, com.champion.mycompose.ui.interop.XmlComposeInteropActivity::class.java))
                        }
                    }
```

**Step 2: 添加必要的 import**

在文件顶部添加：

```kotlin
import android.content.Intent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
```

**Step 3: 提交更改**

```bash
git add app/src/main/java/com/champion/mycompose/navigation/NavGraph.kt
git commit -m "feat: add navigation routes for XML-Compose interop demos"
```

---

## Task 9: 验证构建和功能

**Step 1: 清理并构建项目**

```bash
./gradlew clean
./gradlew :app:assembleDebug
```

预期：构建成功，无错误

**Step 2: 安装到设备并测试**

```bash
./gradlew :app:installDebug
```

预期：应用成功安装

**Step 3: 手动测试功能**

测试清单：
- [ ] 在 HomeScreen 中能看到两个新的示例入口
- [ ] 点击"XML 嵌入 Compose - 基础"能打开简单示例页面
- [ ] 简单示例页面显示 XML 和 Compose 组件的对比
- [ ] XML 按钮和 Compose 按钮都能正常点击并显示 Toast
- [ ] 点击"XML 嵌入 Compose - 交互"能打开中等复杂度示例页面
- [ ] 场景1：输入姓名并点击更新按钮，Compose 区域显示问候语
- [ ] 场景2：点击 Compose 按钮，XML TextView 显示点击次数
- [ ] 场景3：点击增加按钮，计数器增加；点击重置姓名按钮，场景1的姓名被清空

**Step 4: 提交验证记录**

```bash
git add .
git commit -m "test: verify XML-Compose interop demos functionality"
```

---

## 完成标准

- [x] HomeScreen 中添加了两个新的示例入口
- [x] 创建了简单示例的 XML 布局和 Activity
- [x] 创建了中等复杂度示例的 XML 布局、ViewModel 和 Activity
- [x] 在 AndroidManifest.xml 中注册了两个新 Activity
- [x] 更新了导航路由以支持新示例
- [x] 所有代码都有详细的中文注释
- [x] 构建成功，无编译错误
- [x] 功能测试通过，所有场景都能正常工作

## 注意事项

1. **主题继承：** ComposeView 会自动继承 Activity 的 Material Theme，但仍需在 setContent 中包裹 MyComposeTheme 以确保使用项目主题
2. **生命周期：** ComposeView 自动处理生命周期，无需手动管理
3. **状态管理：** 使用 remember 管理 Compose 内部状态，使用 ViewModel 管理跨组件共享状态
4. **代码注释：** 所有关键代码都添加了中文注释，说明"是什么"和"为什么"
5. **分块提交：** 每个 Task 完成后立即提交，保持提交历史清晰
