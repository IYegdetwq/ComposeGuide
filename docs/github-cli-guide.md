# GitHub CLI 操作手册

GitHub CLI (`gh`) 是 GitHub 官方提供的命令行工具，让你可以直接在终端中完成 GitHub 的各种操作，无需切换到浏览器。

## 目录

- [安装与配置](#安装与配置)
- [认证管理](#认证管理)
- [仓库管理](#仓库管理)
- [代码推送与拉取](#代码推送与拉取)
- [分支管理](#分支管理)
- [Pull Request 操作](#pull-request-操作)
- [Issue 管理](#issue-管理)
- [常用命令速查表](#常用命令速查表)

---

## 安装与配置

### macOS 安装

使用 Homebrew 安装（推荐）：

```bash
brew install gh
```

验证安装：

```bash
gh --version
```

### 其他平台安装

**Linux (Debian/Ubuntu)**：
```bash
sudo apt install gh
```

**Linux (Fedora/CentOS)**：
```bash
sudo dnf install gh
```

**Windows**：
```bash
winget install --id GitHub.cli
```

或使用 Scoop：
```bash
scoop install gh
```

---

## 认证管理

### 首次认证

```bash
gh auth login
```

交互式流程会询问：
1. 选择 GitHub.com 或 GitHub Enterprise Server
2. 选择认证协议（HTTPS 或 SSH）
3. 选择认证方式：
   - 通过浏览器登录（推荐）
   - 使用 Personal Access Token

### 使用 Token 认证

如果你已有 Personal Access Token：

```bash
gh auth login --with-token < token.txt
```

或直接输入：
```bash
echo "your_token_here" | gh auth login --with-token
```

### 查看认证状态

```bash
gh auth status
```

### 登出

```bash
gh auth logout
```

### 刷新认证

```bash
gh auth refresh
```

---

## 仓库管理

### 创建仓库

**创建公开仓库**：
```bash
gh repo create my-project --public
```

**创建私有仓库**：
```bash
gh repo create my-project --private
```

**从当前目录创建仓库并推送**：
```bash
gh repo create my-project --private --source=. --remote=origin --push
```

参数说明：
- `--source=.`：使用当前目录作为源代码
- `--remote=origin`：设置远程仓库名称为 origin
- `--push`：创建后立即推送代码

**使用模板创建仓库**：
```bash
gh repo create my-project --template owner/template-repo
```

### 克隆仓库

```bash
gh repo clone owner/repo-name
```

克隆到指定目录：
```bash
gh repo clone owner/repo-name target-directory
```

### 查看仓库信息

```bash
gh repo view
```

查看指定仓库：
```bash
gh repo view owner/repo-name
```

在浏览器中打开：
```bash
gh repo view --web
```

### 列出仓库

列出你的仓库：
```bash
gh repo list
```

列出指定用户/组织的仓库：
```bash
gh repo list owner
```

限制数量：
```bash
gh repo list --limit 50
```

### 删除仓库

```bash
gh repo delete owner/repo-name
```

需要确认，或使用 `--yes` 跳过确认：
```bash
gh repo delete owner/repo-name --yes
```

### Fork 仓库

```bash
gh repo fork owner/repo-name
```

Fork 并克隆到本地：
```bash
gh repo fork owner/repo-name --clone
```

### 设置仓库可见性

```bash
gh repo edit --visibility private
gh repo edit --visibility public
```

---

## 代码推送与拉取

### 推送代码

GitHub CLI 本身不直接处理推送，但可以配合 Git 使用：

```bash
git add .
git commit -m "Your commit message"
git push
```

首次推送到新分支：
```bash
git push -u origin branch-name
```

### 拉取代码

```bash
git pull
```

### 同步 Fork

如果你 fork 了别人的仓库，可以同步上游更新：

```bash
gh repo sync owner/repo-name
```

同步当前仓库：
```bash
gh repo sync
```

---

## 分支管理

虽然 GitHub CLI 主要关注 GitHub 特性，分支管理仍然使用 Git 命令：

### 创建分支

```bash
git checkout -b feature-branch
```

### 切换分支

```bash
git checkout branch-name
```

### 推送新分支

```bash
git push -u origin feature-branch
```

### 删除本地分支

```bash
git branch -d branch-name
```

### 删除远程分支

```bash
git push origin --delete branch-name
```

### 查看所有分支

```bash
git branch -a
```

---

## Pull Request 操作

### 创建 Pull Request

从当前分支创建 PR：

```bash
gh pr create
```

交互式流程会询问：
- PR 标题
- PR 描述
- 目标分支

**指定标题和描述**：
```bash
gh pr create --title "Add new feature" --body "This PR adds..."
```

**使用编辑器编写描述**：
```bash
gh pr create --title "Add new feature" --body-file description.md
```

**指定目标分支**：
```bash
gh pr create --base main --head feature-branch
```

**创建草稿 PR**：
```bash
gh pr create --draft
```

**指定审阅者**：
```bash
gh pr create --reviewer username1,username2
```

**添加标签**：
```bash
gh pr create --label bug,enhancement
```

### 查看 Pull Request

列出 PR：
```bash
gh pr list
```

查看特定 PR：
```bash
gh pr view 123
```

在浏览器中打开：
```bash
gh pr view 123 --web
```

查看 PR 的 diff：
```bash
gh pr diff 123
```

### 检出 Pull Request

将 PR 检出到本地分支：
```bash
gh pr checkout 123
```

### 审阅 Pull Request

批准 PR：
```bash
gh pr review 123 --approve
```

请求修改：
```bash
gh pr review 123 --request-changes --body "Please fix..."
```

添加评论：
```bash
gh pr review 123 --comment --body "Looks good!"
```

### 合并 Pull Request

合并 PR：
```bash
gh pr merge 123
```

选择合并方式：
```bash
gh pr merge 123 --merge      # 普通合并
gh pr merge 123 --squash     # 压缩合并
gh pr merge 123 --rebase     # 变基合并
```

合并后删除分支：
```bash
gh pr merge 123 --delete-branch
```

### 关闭 Pull Request

```bash
gh pr close 123
```

### 重新打开 Pull Request

```bash
gh pr reopen 123
```

### PR 状态检查

查看 PR 的 CI/CD 状态：
```bash
gh pr checks 123
```

等待检查完成：
```bash
gh pr checks 123 --watch
```

---

## Issue 管理

### 创建 Issue

```bash
gh issue create
```

指定标题和内容：
```bash
gh issue create --title "Bug: Login fails" --body "Description..."
```

添加标签和指派：
```bash
gh issue create --title "Bug" --label bug --assignee username
```

### 查看 Issue

列出 Issue：
```bash
gh issue list
```

查看特定 Issue：
```bash
gh issue view 456
```

在浏览器中打开：
```bash
gh issue view 456 --web
```

### 过滤 Issue

按状态过滤：
```bash
gh issue list --state open
gh issue list --state closed
gh issue list --state all
```

按标签过滤：
```bash
gh issue list --label bug
```

按指派人过滤：
```bash
gh issue list --assignee username
```

### 关闭 Issue

```bash
gh issue close 456
```

### 重新打开 Issue

```bash
gh issue reopen 456
```

### 评论 Issue

```bash
gh issue comment 456 --body "Thanks for reporting!"
```

### 编辑 Issue

```bash
gh issue edit 456 --title "New title" --body "New description"
```

添加标签：
```bash
gh issue edit 456 --add-label enhancement
```

移除标签：
```bash
gh issue edit 456 --remove-label bug
```

---

## 常用命令速查表

### 认证相关
| 命令 | 说明 |
|------|------|
| `gh auth login` | 登录 GitHub |
| `gh auth logout` | 登出 |
| `gh auth status` | 查看认证状态 |
| `gh auth refresh` | 刷新认证 |

### 仓库相关
| 命令 | 说明 |
|------|------|
| `gh repo create <name> --private` | 创建私有仓库 |
| `gh repo create <name> --public` | 创建公开仓库 |
| `gh repo clone <owner>/<repo>` | 克隆仓库 |
| `gh repo view` | 查看仓库信息 |
| `gh repo list` | 列出仓库 |
| `gh repo fork <owner>/<repo>` | Fork 仓库 |
| `gh repo sync` | 同步 Fork |

### Pull Request 相关
| 命令 | 说明 |
|------|------|
| `gh pr create` | 创建 PR |
| `gh pr list` | 列出 PR |
| `gh pr view <number>` | 查看 PR |
| `gh pr checkout <number>` | 检出 PR |
| `gh pr merge <number>` | 合并 PR |
| `gh pr close <number>` | 关闭 PR |
| `gh pr review <number> --approve` | 批准 PR |
| `gh pr diff <number>` | 查看 PR 差异 |
| `gh pr checks <number>` | 查看 PR 检查状态 |

### Issue 相关
| 命令 | 说明 |
|------|------|
| `gh issue create` | 创建 Issue |
| `gh issue list` | 列出 Issue |
| `gh issue view <number>` | 查看 Issue |
| `gh issue close <number>` | 关闭 Issue |
| `gh issue reopen <number>` | 重新打开 Issue |
| `gh issue comment <number>` | 评论 Issue |

### 其他常用命令
| 命令 | 说明 |
|------|------|
| `gh browse` | 在浏览器中打开仓库 |
| `gh gist create <file>` | 创建 Gist |
| `gh release create <tag>` | 创建 Release |
| `gh workflow list` | 列出 GitHub Actions 工作流 |
| `gh run list` | 列出工作流运行记录 |

---

## 高级技巧

### 使用别名

创建命令别名：
```bash
gh alias set pv 'pr view'
```

使用别名：
```bash
gh pv 123
```

查看所有别名：
```bash
gh alias list
```

### 配置默认编辑器

```bash
gh config set editor vim
```

### 配置默认浏览器

```bash
gh config set browser firefox
```

### 使用 JSON 输出

许多命令支持 `--json` 参数：
```bash
gh pr list --json number,title,author
```

配合 `jq` 使用：
```bash
gh pr list --json number,title | jq '.[] | select(.title | contains("bug"))'
```

---

## 故障排除

### 认证问题

如果遇到认证问题，尝试：
1. 重新登录：`gh auth logout && gh auth login`
2. 刷新认证：`gh auth refresh`
3. 检查 token 权限是否足够

### 权限问题

确保你的 Personal Access Token 具有必要的权限：
- `repo`：完整的仓库访问权限
- `workflow`：GitHub Actions 工作流权限
- `admin:org`：组织管理权限（如需要）

### 网络问题

如果在中国大陆使用，可能需要配置代理：
```bash
export HTTPS_PROXY=http://127.0.0.1:7890
export HTTP_PROXY=http://127.0.0.1:7890
```

---

## 参考资源

- [GitHub CLI 官方文档](https://cli.github.com/manual/)
- [GitHub CLI GitHub 仓库](https://github.com/cli/cli)
- [GitHub CLI 社区讨论](https://github.com/cli/cli/discussions)

---

**文档版本**：1.0
**最后更新**：2026-02-27
**适用版本**：gh 2.87.3+
