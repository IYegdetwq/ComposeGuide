package com.champion.mycompose.ui.forms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.champion.mycompose.ui.theme.MyComposeTheme

/**
 * 表单验证 Section
 *
 * 展示实时验证、正则校验、综合表单等常见表单验证场景，
 * 覆盖日常开发中 80% 的表单交互需求。
 */
@Composable
fun FormValidationSection() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // region 1 - 实时验证
        SectionTitle("实时验证")
        /** 用户名输入 + 实时长度/格式校验 + 错误提示 */
        var username by remember { mutableStateOf("") }
        val usernameError = when {
            username.isEmpty() -> null
            username.length < 3 -> "用户名至少 3 个字符"
            username.length > 20 -> "用户名不能超过 20 个字符"
            !username.matches(Regex("^[a-zA-Z0-9_]+$")) -> "只能包含字母、数字和下划线"
            else -> null
        }
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("用户名") },
            isError = usernameError != null,
            supportingText = {
                if (usernameError != null) {
                    Text(
                        text = usernameError,
                        color = MaterialTheme.colorScheme.error
                    )
                } else if (username.isNotEmpty()) {
                    Text(
                        text = "✓ 用户名格式正确",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        // endregion

        // region 2 - 邮箱验证
        SectionTitle("邮箱验证")
        /** 邮箱格式正则校验 + isError 状态 */
        var email by remember { mutableStateOf("") }
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        val emailError = if (email.isNotEmpty() && !email.matches(emailPattern)) {
            "请输入有效的邮箱地址"
        } else null

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("邮箱") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = emailError != null,
            supportingText = {
                if (emailError != null) {
                    Text(
                        text = emailError,
                        color = MaterialTheme.colorScheme.error
                    )
                } else if (email.isNotEmpty()) {
                    Text(
                        text = "✓ 邮箱格式正确",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        // endregion
        // region 3 - 注册表单综合示例
        SectionTitle("注册表单综合示例")
        /** 用户名 + 邮箱 + 密码 + 确认密码 + 同意条款 + 提交按钮 */
        var regUsername by remember { mutableStateOf("") }
        var regEmail by remember { mutableStateOf("") }
        var regPassword by remember { mutableStateOf("") }
        var regConfirmPassword by remember { mutableStateOf("") }
        var agreeTerms by remember { mutableStateOf(false) }

        // 验证逻辑
        val regUsernameValid = regUsername.length >= 3 && regUsername.matches(Regex("^[a-zA-Z0-9_]+$"))
        val regEmailValid = regEmail.matches(emailPattern)
        val regPasswordValid = regPassword.length >= 6
        val regPasswordMatch = regPassword == regConfirmPassword && regConfirmPassword.isNotEmpty()
        val formValid = regUsernameValid && regEmailValid && regPasswordValid && regPasswordMatch && agreeTerms

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            // 用户名
            OutlinedTextField(
                value = regUsername,
                onValueChange = { regUsername = it },
                label = { Text("用户名") },
                isError = regUsername.isNotEmpty() && !regUsernameValid,
                supportingText = {
                    if (regUsername.isNotEmpty() && !regUsernameValid) {
                        Text("至少 3 个字符，仅字母数字下划线", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // 邮箱
            OutlinedTextField(
                value = regEmail,
                onValueChange = { regEmail = it },
                label = { Text("邮箱") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                isError = regEmail.isNotEmpty() && !regEmailValid,
                supportingText = {
                    if (regEmail.isNotEmpty() && !regEmailValid) {
                        Text("请输入有效的邮箱地址", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // 密码
            OutlinedTextField(
                value = regPassword,
                onValueChange = { regPassword = it },
                label = { Text("密码") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = regPassword.isNotEmpty() && !regPasswordValid,
                supportingText = {
                    if (regPassword.isNotEmpty() && !regPasswordValid) {
                        Text("密码至少 6 个字符", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            // 确认密码
            OutlinedTextField(
                value = regConfirmPassword,
                onValueChange = { regConfirmPassword = it },
                label = { Text("确认密码") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                isError = regConfirmPassword.isNotEmpty() && !regPasswordMatch,
                supportingText = {
                    if (regConfirmPassword.isNotEmpty() && !regPasswordMatch) {
                        Text("两次密码输入不一致", color = MaterialTheme.colorScheme.error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // 同意条款
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Checkbox(
                    checked = agreeTerms,
                    onCheckedChange = { agreeTerms = it }
                )
                Text(
                    text = "我已阅读并同意《用户协议》和《隐私政策》",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // 提交按钮
            Button(
                onClick = {
                    // 提交表单逻辑
                },
                enabled = formValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("注册")
            }

            if (!formValid && (regUsername.isNotEmpty() || regEmail.isNotEmpty())) {
                Text(
                    text = "请完善表单信息并同意用户协议",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
        // endregion

        Spacer(Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun FormValidationSectionPreview() {
    MyComposeTheme {
        FormValidationSection()
    }
}
