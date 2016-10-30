### IDUS
+ 结构化查询语言(Structured Query Language)简称SQL
> 是操作和检索关系型数据库的标准语言,结构化查询语言可分为5类.

  - 数据查询语言(DQL:DataQuery Language):
  > 语句主要包括Seclet,用于从表中检索数据

  - 数据操作语言(DML:Data Manipulation Language):
  > 语句主要包括insert,update和delete,用于田间,修改和删除表中的行数据

  - 事务处理语言(TPL:Transaction Process Language):
  > 语句主要包括Commit和RollBack,用于提交和回滚操作

  - 数据控制语言(DCL:Data Control Language):
  > 语句主要包括grant和revoke,用于进行授权和收回权限

  - 数据定义语言(DDL:Data Definition Language):
  > 语句主要包括create,drop,alter,用于定义,销毁和修改数据库对象

+ 算术运算符

  ![算数运算符](/images/算数运算符.png)
  > 优先级同Java

+ 空值NULL
  - 空值是指一种无效的,<font color="red">未赋值</font>,未知的或不可用的值.
  - 空值不同于零或者空格
  - 任何包含空值的算数表达式运算后的结果都为空值NULL;

+ 连接操作符
 - 用于连接列与列,列和字符
 - 形式上是 ||
 - 用于创建字符表达式的结果列

 ![连接符](/images/连接符.png)

+ 原义字符串
  - 原义字符串是包含在Select列表中的一个字符,一个数字或者一个日期
  - 日期和字符面值必须用单引号引起来
  - 每个原义字符串都会在每个数据行中输出中出现
  ![原义字符串](/images/原义字符串.png)
