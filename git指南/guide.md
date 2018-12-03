## git的使用
### 一、基本操作

1. 安装git
	`sudo apt-get install git-core`
<br>
2. 配置身份信息
		git config --global user.name "xxx"
		git config --global user.email "xxx"
	验证是否配置成功
		git config --global user.name
		git config --global user.email
3. 创建代码仓库
		进入到相应代码目录 执行 "git init"
4. 代码库相关操作
		a.添加文件 "git add 文件名(包含相对路径)" 或者使用 "git add ."直接添加所有文件
		b.提交更改 'git commit -m "说明内容" '