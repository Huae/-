## git的使用
### 一、基本操作

1. 安装git<br>
	`sudo apt-get install git-core`
2. 配置身份信息<br>
	`git config --global user.name "xxx"`<br>
	`git config --global user.email "xxx"`<br>
	验证是否配置成功<br>
	`git config --global user.name`<br>
	`git config --global user.email`<br>
3. 创建代码仓库<br>
	进入到相应代码目录 执行 `git init`
4. 代码库相关操作<br>
		a.添加文件 `git add 文件名(包含相对路径)` 或者使用 `git add .`直接添加所有文件<br>
		b.提交更改 `git commit -m "说明内容" `