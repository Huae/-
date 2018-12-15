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

### 二、进阶操作

1. 忽略文件
	<br>编辑`.gitignore`文件，在其中加入不需要版本控制的文件，目录需以`/`结尾.

2. 查看修改内容
	<br>`git status`查看修改状态，使用`git diff [文件名]`查看更改的具体内容.`-`号代表删除的行，`+`号代表增加的行

3. 撤销更改
	+ 未添加
		<br>`git checkout [文件名]`可以撤销所做的更改.
	+ 已添加(已执行`git add`)
		<br>先`git reset HEAD [文件名]`，然后同上.

4. 查看提交记录
	<br>`git log`查看所有记录
	<br>`git log [提交id] -l`查看j具体某次记录.
	<br>`git log [提交id] -l -p`查看某次记录的具体提交内容.