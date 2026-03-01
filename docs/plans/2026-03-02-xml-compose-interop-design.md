# XML 与 Compose 混合开发示例设计文档

**日期：** 2026-03-02
**状态：** 已批准
**目标：** 在 HomeScreen 中新增示例，演示在现有 XML 布局中嵌入 Compose 开发的 UI 组件

## 背景

MyCompose 是一个面向从 XML 布局转型 Compose 的资深 Android 开发者的学习项目。当前项目完全使用 Compose 开发，缺少 XML 与 Compose 混合开发的示例。在实际工作中，很多项目需要从传统 XML 逐步迁移到 Compose，因此需要演示如何在 XML 布局中嵌入 Compose 组件。

## 需求概述

1. **使用场景：** 在 Android 传统 XML 布局中嵌入 Compose UI
2. **复杂度层次：** 提供简单示例和中等复杂度示例两种
3. **重点内容：**
   - XML 与 Compose 之间的数据传递
   - 状态管理与 ViewModel 集成

## 设计方案

### 方案选择

采用**双页面独立示例**方案，创建两个独立的 Activity + XML 布局文件：

- **简单示例：** 演示 ComposeView 基础用法
- **中等复杂度示例：** 演示数据传递、事件回调、状态管理

**选择理由：**
1. 符合项目"一个功能一个独立页面"的架构风格
2. 学习体验好，从简单到复杂渐进式学习
3. 代码清晰，职责单一，易于维护
4. 扩展性强，未来可轻松添加更多示例

## 架构设计

### 文件结构

```
app/src/main/
├── java/com/champion/mycompose/
│   ├── ui/interop/
│   │   ├── XmlComposeBasicActivity.kt          # 简单示例
│   │   └── XmlComposeInteropActivity.kt        # 中等复杂度示例
│   └── navigation/
│       └── Screen.kt                            # 添加新的路由
├── res/layout/
│   ├── activity_xml_compose_basic.xml           # 简单示例布局
│   └── activity_xml_compose_interop.xml         # 中等复杂度布局
└── AndroidManifest.xml                          # 注册新 Activity
```

### HomeScreen 集成

在 `demoItems` 列表中添加两个新条目：

```kotlin
DemoItem(
    "xml_compose_basic",
    "XML 嵌入 Compose - 基础",
    "在 XML 布局中使用 ComposeView 嵌入 Compose 组件",
    LearningPhase.PHASE_1,
    available = true
),
DemoItem(
    "xml_compose_interop",
    "XML 嵌入 Compose - 交互",
    "XML 与 Compose 数据传递、状态管理",
    LearningPhase.PHASE_4,
    available = true
)
```

**分类说明：**
- 简单示例归入"第一阶段：基础入门"
- 中等复杂度归入"第四阶段：进阶实战"

## 详细设计

### 1. 简单示例（XmlComposeBasicActivity）

#### 功能目标
演示最基础的 XML 与 Compose 混合开发，让开发者理解 ComposeView 的基本用法。

#### XML 布局（activity_xml_compose_basic.xml）

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    android:padding="16dp">

    <!-- 传统 XML 区域 -->
    <TextView
        android:text="这是传统 XML TextView"
        android:textSize="18sp"
        android:padding="16dp"
    />

    <!-- Compose 区域 1：简单文本 -->
    <androidx.compose.ui.platform.ComposeView
        android:id="@+id/composeView1"
        android:layout_height="wrap_content"
    />

    <!-- 传统 XML 按钮 -->
    <Button
        android:id="@+id/xmlButton"
        android:text="XML 按钮"
    />

    <!-- Compose 区域 2：Material 组件 -->
    <androidx.compose.ui.platform.ComposeView
        android:id="@+id/composeView2"
        android:layout_height="wrap_content"
    />

</LinearLayout>
```

#### Activity 实现要点

1. **ComposeView 1：** 展示简单的 Compose Text 和 Card
2. **ComposeView 2：** 展示 Material3 Button 和 Icon
3. **XML Button：** 点击后通过 Toast 提示，对比传统方式
4. **主题继承：** ComposeView 自动继承 Activity 的 Material Theme

#### 展示内容
- ComposeView 的基本使用方法（findViewById + setContent）
- Compose 组件与 XML View 的视觉对比
- 主题自动继承的效果

### 2. 中等复杂度示例（XmlComposeInteropActivity）

#### 功能目标
演示 XML 与 Compose 之间的数据传递、事件回调、状态管理，展示实际开发中的常见场景。

#### XML 布局（activity_xml_compose_interop.xml）

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView>
    <LinearLayout
        android:orientation="vertical"
        android:padding="16dp">

        <!-- 场景1：XML 向 Compose 传递数据 -->
        <TextView
            android:text="场景1：XML → Compose 数据传递"
            style="@style/SectionTitle"
        />

        <EditText
            android:id="@+id/nameInput"
            android:hint="输入姓名"
        />

        <Button
            android:id="@+id/updateButton"
            android:text="更新 Compose 显示"
        />

        <androidx.compose.ui.platform.ComposeView
            android:id="@+id/composeViewDisplay"
            android:layout_height="wrap_content"
        />

        <!-- 场景2：Compose 向 XML 回调事件 -->
        <TextView
            android:text="场景2：Compose → XML 事件回调"
            style="@style/SectionTitle"
        />

        <TextView
            android:id="@+id/callbackResult"
            android:text="等待 Compose 组件触发..."
        />

        <androidx.compose.ui.platform.ComposeView
            android:id="@+id/composeViewCallback"
            android:layout_height="wrap_content"
        />

        <!-- 场景3：状态管理 -->
        <TextView
            android:text="场景3：ViewModel 状态管理"
            style="@style/SectionTitle"
        />

        <androidx.compose.ui.platform.ComposeView
            android:id="@+id/composeViewState"
            android:layout_height="wrap_content"
        />

    </LinearLayout>
</ScrollView>
```

#### ViewModel 设计

```kotlin
class XmlComposeInteropViewModel : ViewModel() {
    // 共享状态
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter.asStateFlow()

    private val _userName = MutableStateFlow("")
    val userName: StateFlow<String> = _userName.asStateFlow()

    fun incrementCounter() {
        _counter.value++
    }

    fun updateUserName(name: String) {
        _userName.value = name
    }
}
```

#### Activity 实现要点

**场景1 - XML → Compose 数据传递：**
- EditText 输入内容
- 点击 Button 后，通过 ComposeView.setContent 传递参数给 Compose
- Compose 组件显示接收到的数据

**场景2 - Compose → XML 事件回调：**
- Compose 中有一个按钮
- 点击后通过 lambda 回调通知 Activity
- Activity 更新 XML TextView 显示结果

**场景3 - ViewModel 状态管理：**
- Activity 持有 ViewModel
- Compose 组件通过 collectAsState() 观察 ViewModel 状态
- Compose 中的操作（如计数器）更新 ViewModel
- 展示 XML 和 Compose 共享同一个 ViewModel 的场景

### 3. 导航集成

#### Screen.kt 更新

```kotlin
object XmlComposeBasic : Screen("xml_compose_basic")
object XmlComposeInterop : Screen("xml_compose_interop")
```

#### NavGraph.kt 更新

```kotlin
composable(Screen.XmlComposeBasic.route) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.startActivity(Intent(context, XmlComposeBasicActivity::class.java))
    }
}

composable(Screen.XmlComposeInterop.route) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        context.startActivity(Intent(context, XmlComposeInteropActivity::class.java))
    }
}
```

#### AndroidManifest.xml 更新

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

## 技术要点

### ComposeView 使用模式

```kotlin
composeView.setContent {
    MyComposeTheme {
        // Compose 内容
    }
}
```

### 主题继承
- ComposeView 会自动继承 Activity 的 Material Theme
- 需要在 setContent 中包裹 MyComposeTheme 确保使用项目主题

### 生命周期处理
- ComposeView 自动处理生命周期，无需手动管理
- 与 Activity/Fragment 生命周期自动同步

### 状态观察

```kotlin
val state by viewModel.counter.collectAsState()
```

### 注释规范
- 每个 ComposeView 的 setContent 添加中文注释说明用途
- 每个场景添加详细的功能说明注释
- 关键代码添加"为什么这样做"的注释

## 学习路径

1. **第一阶段：** 学习 XmlComposeBasicActivity，理解 ComposeView 基础用法
2. **第四阶段：** 学习 XmlComposeInteropActivity，掌握数据传递和状态管理
3. **实战应用：** 在实际项目中应用 XML 与 Compose 混合开发

## 预期成果

完成后，开发者将能够：
1. 理解 ComposeView 的基本使用方法
2. 掌握 XML 与 Compose 之间的数据传递
3. 理解如何在混合开发中使用 ViewModel 管理状态
4. 具备在现有 XML 项目中逐步引入 Compose 的能力
5. 覆盖实际开发中 80% 的混合开发场景

## 后续扩展

未来可以考虑添加：
1. Compose 中嵌入 XML View 的示例（AndroidView）
2. Fragment 中使用 ComposeView 的示例
3. 复杂的双向数据绑定场景
4. 性能优化最佳实践
