### 网络基础知识
+ TCP/IP 四层协议
  > 网络接口层与OSI参考模型中的物理层和数据链路层相对应.他负责监视数据在主机和网络之间的交换.事实上,TCP/IP本身并未敬意该层的协议,而是由参与互连的两个网络使用自己的物理层和数据链路层协议.然后TCP的网络接入层进行链接.地址解析协议(ARP)工作在此层,即OSI参考模型的数据链路层.

  > 网际互联层对应的OSI参考模型的网络层,主要解决主机到主机的通信问题.它所包含的协议设计数据包在整个网络上的逻辑传递.该层有三个主要协议:网际协议(IP),互联网组管理协议(IGMP)和互联网控制报文协议(ICMP)

  > 传输层对应与OSI参考模型的传输层,为应用层实体提供端到端的通信功能,保证了数据包的顺序传送及数据的完整性.该层定义了两个主要的协议:传输空值协议(TCP)和用户数据报协议(UDP)

  > 应用层对应于OSI参考模型的高层,为用户提供所需要的各种服务.例如:FTO,Telnet,DNF,SMTP

+ 子网掩码:
  > ip和子网掩码不能单独使用

  > 广播地址是专门用于同时向网络中所有工作站进行发送的一个地址

+ 端口作用
 > IP地址就相当于写信的地址,端口号就相当于收件人

  - 常见端口号:
	  - FTP(文件传输协议): 20 21
	  - SSH(安全shell协议): 22
	  - telnet(远程登录协议): 23
	  - DNS(域名系统) :53
	  - http(超文本传输协议) :80
	  - SMTP(简单邮件传输协议):25
	  - POP3(邮件协议3代) : 110

  > 发起端口是随机端口,接收端口是确定的

+ DNS作用
  - 名称解析概述
    - 在互联网中,通过IP地址来进行通信
    - IP地址用数字表示,记忆起来困难
    - 人对域名更加敏感,如http://www.baidu.com
  > DNS就是Domain Name System  就是域名系统也叫名称解析
  hosts文件的优先级是高于DNS解析的

  - 早期hosts文件解析域名
    - 名称解析效能下降
    - 主机维护困难
  - DNS 服务
    - 层次性
    - 分布式

  - 将域名解析为IP地址
    - 客户机向DNF服务器发送域名查询请求
    - DNS服务器告知客户机Web服务器的IP地址
    - 客户机与Web服务器通信
  - 域名介绍
    - 根域  就是 .
    - 顶级域
      - 组织域
      - 国家或地区域
    - 二级域名
    - 主机名
  > 主机名 . 二级域 .顶级域 组成全世界唯一的域名(主机域名可以自己起)

  - DNS查询类型
  	- 从查询方式上分
  		- 递归查询
  			> 要么做出查询成功的响应,要么做出查询失败的响应.一般客户机和	服务器之间属递归查询,即当客户机向DNS服务器发出请求后,若DNS服务器本身不能解析,则会向另外的DNS服务器发起查询请求,得到结果后转交给客户机

       - 迭代查询
  		   > 服务器收到一次迭代查询回复一次结果,这个结果不一定是目标IP与域名的映射关系,也可以是其他DNS服务器的地址

	- 从查询内容上分
		- 正向查询由域名查找IP地址
		- 反向查询由IP地址查找域名

+ 网关作用
  - 网关在所有内网计算机访问的不是本网段的数据报时使用.
  - 网关负责将内网ip转化为公网ip,公网ip转化为内网ip
  进入公网必须要有ip,子网掩码,网关,DNS ,但可以在局域网通信
