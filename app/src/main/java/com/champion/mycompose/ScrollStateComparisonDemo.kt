package com.champion.mycompose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * ScrollState 对比演示
 *
 * 具体场景：购物 App 的商品列表
 * 1. 用户滚动到第 20 个商品
 * 2. 点击某个商品的"收藏"按钮（触发重组）
 * 3. 观察滚动位置是否保持
 */
@Composable
fun ScrollStateComparisonDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            "场景：购物列表中点击收藏按钮",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            "操作步骤：\n" +
                    "1. 向下滚动到 Item 20 左右\n" +
                    "2. 点击「收藏」按钮触发重组\n" +
                    "3. 观察滚动位置变化",
            style = MaterialTheme.typography.bodyMedium
        )

        // 示例 1：使用 rememberScrollState（正确）
        SectionTitle("✅ 使用 rememberScrollState（正确）")
        ShoppingListWithRemember()

        Spacer(modifier = Modifier.height(32.dp))

        // 示例 2：不使用 remember（错误）
        SectionTitle("❌ 不使用 remember（错误示范）")
        ShoppingListWithoutRemember()
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun ShoppingListWithRemember() {
    // ✅ 正确：使用 rememberScrollState()
    val scrollState = rememberScrollState()
    var favoriteCount by remember { mutableIntStateOf(0) }

    Column {
        Text(
            "当前滚动位置: ${scrollState.value}px | 收藏数: $favoriteCount",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(8.dp)
                )
                .verticalScroll(scrollState)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(30) { index ->
                ProductItem(
                    name = "商品 ${index + 1}",
                    price = "${(index + 1) * 99}",
                    onFavorite = { favoriteCount++ }
                )
            }
        }

        Text(
            "✓ 点击收藏后，滚动位置保持不变",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun ShoppingListWithoutRemember() {
    // ❌ 错误：每次重组都创建新的 ScrollState
    var favoriteCount by remember { mutableIntStateOf(0) }

    // 模拟不使用 remember 的情况：每次 favoriteCount 变化就创建新的 ScrollState
    val scrollState = remember(favoriteCount) { ScrollState(initial = 0) }

    Column {
        Text(
            "当前滚动位置: ${scrollState.value}px | 收藏数: $favoriteCount",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.errorContainer,
                    RoundedCornerShape(8.dp)
                )
                .verticalScroll(scrollState)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(30) { index ->
                ProductItem(
                    name = "商品 ${index + 1}",
                    price = "${(index + 1) * 99}",
                    onFavorite = { favoriteCount++ }
                )
            }
        }

        Text(
            "✗ 点击收藏后，滚动位置丢失，跳回顶部",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun ProductItem(name: String, price: String, onFavorite: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                RoundedCornerShape(4.dp)
            )
            .padding(12.dp)
    ) {
        Text(name, style = MaterialTheme.typography.bodyLarge)
        Text(
            "¥$price",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Button(
            onClick = onFavorite,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text("收藏", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ScrollStateComparisonDemoPreview() {
    MyComposeTheme {
        ScrollStateComparisonDemo()
    }
}
