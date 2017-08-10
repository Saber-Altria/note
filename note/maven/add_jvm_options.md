
#### 在mvn命令中增加jvm参数

解决办法一：

`vim ~/.bashrc`

插入下述行

`export MAVEN_OPTS="-Xms256m -Xmx512m"`

重启终端即可

影响当前用户

解决办法二：

或者在mvn脚本的前面加上

`MAVEN_OPTS="-Xms256m -Xmx512m"`

影响所有使用此mvn命令的用户
