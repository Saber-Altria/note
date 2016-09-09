bash基本语法
========

### 重要的tips：
* bash里面没事不要乱加空格

------

## 1、变量

bash无需声明变量，*变量名只能包含字母和数字，不能以数字开头*

### 1.1 初始化/赋值

* 可以直接赋值
* 利用系统命令赋值

#### 直接赋值

    currentFolder=/home/media-sns/script
    str1=aaaa
    str2="aaa"
    str3='aaaaa'

#### 利用系统命令赋值

将系统命令的值赋给变量有两种方式：1、被称作`back-quote`的反引号\`\`；2、被称作`subshell`的$()

    currentFolder=`pwd`
    currentFolder=$(pwd)
    
### 1.2 引用

使用$引用一个变量
    
    $currentFolder

## 2、运算

### 2.1 数学运算

bash不支持小数运算,可以使用三种方式实现数学运算：

* $[ ]
* 利用expr命令的反引用\`\`
* $(())

#### $[ ]

    a=1
    b=2
    c=$[a+b]
    d=$[a-b]
    e=$[a*b]
    f=$[a/b]

#### 利用expr命令的反引用\`\`

    a=2
    b=3
    #运算符两边必须加上空格,*要加上转义字符，因为*在正则中表示全匹配
    c=`expr $a \* $b`
    d=`expr $a + $b`

#### $(())

    a=2
    b=3
    c=$(($a+$b))

### 2.2 自增、自减

bash的自增自减功能需要依赖`let`工具实现

    i=1
    let i++
    let i+=2
    
### 2.3 字符串运算

#### 计算字符串长度

    str1="string 1"
    len1=${#str1}
    
    