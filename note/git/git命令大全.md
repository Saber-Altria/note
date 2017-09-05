#### 别名相关

配置一个别名

```
git config --global alias.ci commit
```

获取所有别名

```
git config --global -l | grep alias
```

#### git diff

比较工作空间和暂存区的差异

```
git diff
```

比较工作区和本地库(HEAD)的差异

```
git diff HEAD
```

比较暂存区和本地库的差异

```
git diff --cached
```

或者

```
git diff --staged
```

#### git status

当前工作空间的状态

```
git status
```

简化状态，加粗的M表示暂存区和HEAD的差异，轻M表示工作空间和暂存区的差异

```
git status -s
```


#### git log

只输出commit和commit msg

```
git log --pretty=oneline
```

#### .git目录说明

![.git目录说明](https://github.com/Saber-Altria/note/blob/master/images/git/git%E7%9B%AE%E5%BD%95%E6%96%87%E4%BB%B6.png)

```
HEAD					
config
description
hooks
index					暂存区；工作空间文件的索引，记录了每个文件的大小和修改时间等状态信息，用来快速判断文件是否修改
info
logs
objects
packed-refs
refs
```

#### git 撤销相关

![工作区，暂存区，版本库原理图](https://github.com/Saber-Altria/note/blob/master/images/git/%E4%B8%89%E4%B8%AA%E5%8C%BA%E7%9A%84%E5%AE%9E%E4%BD%93.png)

撤销到某个历史commit,并重新提交,不删除中间的commits

```
 git checkout -f A -- .
```





