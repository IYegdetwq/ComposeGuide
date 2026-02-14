package com.champion.mycompose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 基础布局 Demo
 *
 * 对照关系：
 * - Column  → LinearLayout(vertical)
 * - Row     → LinearLayout(horizontal)
 * - Box     → FrameLayout
 * - Modifier → XML 属性 (padding, size, background...)
 */
@Composable
fun BasicLayoutDemo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        SectionTitle("1. Column — 垂直排列（≈ LinearLayout vertical）")
        ColumnDemo()

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle("2. Row — 水平排列（≈ LinearLayout horizontal）")
        RowDemo()

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle("3. Box — 层叠布局（≈ FrameLayout）")
        BoxDemo()

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle("4. Modifier — 修饰符链（≈ XML 属性）")
        ModifierDemo()

        Spacer(modifier = Modifier.height(24.dp))

        SectionTitle("5. 综合实战 — 个人信息卡片")
        ProfileCard()
    }
}

// region ---- 小节标题 ----

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )
    Spacer(modifier = Modifier.height(8.dp))
}

// endregion

// region ---- 1. Column Demo ----

@Composable
private fun ColumnDemo() {
    // verticalArrangement 控制子元素间距，类似 LinearLayout 的 gravity + margin
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(12.dp,10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp) // 子元素间距 8dp
    ) {
        Text("第一行：默认靠左")
        Text(
            "第二行：居中对齐",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            "第三行：靠右对齐",
            modifier = Modifier.align(Alignment.End)
        )
    }
}

// endregion

// region ---- 2. Row Demo ----

@Composable
private fun RowDemo() {
    // horizontalArrangement 控制水平分布方式
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // 2.1 均匀分布
        Text("SpaceEvenly — 均匀分布：", style = MaterialTheme.typography.bodySmall)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ColorBlock(Color(0xFFEF5350), "A")
            ColorBlock(Color(0xFF42A5F5), "B")
            ColorBlock(Color(0xFF66BB6A), "C")
        }

        // 2.2 weight 权重分配（≈ layout_weight）
        Text("Weight — 权重分配：", style = MaterialTheme.typography.bodySmall)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .background(Color(0xFFEF5350), RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) { Text("1f", color = Color.White) }
            Box(
                modifier = Modifier
                    .weight(2f)
                    .height(40.dp)
                    .background(Color(0xFF42A5F5), RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) { Text("2f", color = Color.White) }
        }
    }
}

@Composable
private fun ColorBlock(color: Color, label: String) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(color, RoundedCornerShape(4.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(label, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

// endregion

// region ---- 3. Box Demo ----

@Composable
private fun BoxDemo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            "底层(TopStart)",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
        )
        Box(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("中层", color = MaterialTheme.colorScheme.onPrimary)
        }
        Text(
            "顶层(BottomEnd)",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

// endregion

// region ---- 4. Modifier Demo ----

@Composable
private fun ModifierDemo() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("注意：Modifier 的调用顺序会影响最终效果", style = MaterialTheme.typography.bodySmall)

        // 先 padding 后 background → padding 区域没有背景色
        Text(
            text = "先 padding(16) 后 background",
            modifier = Modifier
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                .padding(8.dp)
        )

        // 先 background 后 padding → padding 区域有背景色
        Text(
            text = "先 background 后 padding(16)",
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(8.dp))
                .padding(16.dp)
        )

        Text("常用 Modifier 组合：", style = MaterialTheme.typography.bodySmall)
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 50.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.secondary)
                .border(2.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("size + clip + bg + border", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

// endregion

// region ---- 5. 综合实战：个人信息卡片 ----

@Composable
private fun ProfileCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(MaterialTheme.colorScheme.primary, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "C",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(
                "Champion",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Android 开发工程师 · 10年经验",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

// endregion

// region ---- Preview ----

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun BasicLayoutDemoPreview() {
    MyComposeTheme {
        BasicLayoutDemo()
    }
}

// endregion