# CLAUDE.md

此文件为 Claude Code (claude.ai/code) 在本仓库中工作时提供指导。

## 项目概述

一个 Jetpack Compose 学习项目，面向从 XML 布局转型 Compose 的资深 Android 开发者。单模块应用，使用 Material Design 3、Kotlin 2.0.21 和 Compose BOM 2024.09.00。

## 构建命令

```bash
./gradlew assembleDebug          # 构建 Debug APK
./gradlew installDebug           # 构建并安装到已连接的设备
./gradlew test                   # 运行单元测试
./gradlew connectedAndroidTest   # 运行插桩测试（需要设备/模拟器）
./gradlew clean build            # 清理并重新构建
```

## 技术栈

- **编译/目标 SDK**：36 (Android 15)，**最低 SDK**：24
- **Kotlin**：2.0.21，**JVM 目标版本**：11
- **Compose BOM**：2024.09.00，**Material3**
- **依赖管理**：`gradle/libs.versions.toml`

## 架构

- 单模块项目，位于 `app/` 目录下
- 入口：`MainActivity.kt` — 使用 `ComponentActivity`，启用了全屏沉浸（edge-to-edge）和 `Scaffold`
- 主题：`ui/theme/` — Material 3，支持浅色/深色方案及 Android 12+ 动态取色
- 所有 UI 均基于 Compose（无 XML 布局）

## 开发规范

- 包名：`com.champion.mycompose`
- 新增 Compose 示例应以可组合函数形式添加，并附带 `@Preview` 注解
- 使用 Material 3 组件和 `MaterialTheme` 的设计令牌（颜色、字体排版），避免硬编码值
- 遵循 Kotlin 官方代码风格（已在 `gradle.properties` 中配置）
