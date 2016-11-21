### 安装命令(简单Linux学习的最后一章)

+ 远程登录
	- SSH:
		> 对称加密算法:采用单钥密码系统的加密方法,同一个密钥可以同时用作信息的加密和解密,这种加密方法为对称加密,也成为单密钥加密.

		> 非对称加密算法,又名"公开密钥加密算法",非对称加密算法需要两个密钥:
		公开密钥和私有密钥.

	- ssh命令
	 - ssh 用户名@ip :远程管理指定Linux 服务器
	 - scp [-r] 用户名@ip:文件路径 本地路径 : 下载文件 (sshcopy)
	 - scp [-r] 文帝文件 用户名@ip:上传路径  上传文件

+ Linux软件安装
   - 软件包分类
   	- 源码包
   	  - 脚本安装包
       >	所谓脚本安装包,就是把复杂的软件安装过程写成了程序脚本,初学者可以执行程序脚本实现一键安装.但实际安装的还是源码包和二进制包
   		 - 优点: 安装简单,快捷
   	 	- 缺点: 完全丧失了自定义性

     - 优点:
   	- 开源,如果有足够的能力,可以修改源代码
   	 - 可以自由选择所需的功能
    	- 软件是编译安装,所以更加适合自己的系统,更加稳定也效率更高
    	- 卸载方便
    - 缺点:
      - 安装过程步骤较多,尤其安装较大的软件集合时,容易出现拼写错误
   	- 编译过程时间较长,安装比二进制安装时间长
  - 二进制包(RPM包 ,系统默认包)
   	- 优点:
   		- 包管理系统简单,只通过几个命令就可以实现包的安装,升级,查询,卸载
   		- 安装速度比源码包安装快很多
   	- 缺点:
   		- 经过编译,不能看到源代码
   		- 功能选择不如源码包灵活
   		- 依赖性
+ rpm命令安装(依赖性很高,安装会安装在系统默认位置)
    - rpm包的命名规则
    	- 包名称 +版本  +  版本发布次数 + 适合平台 +适合硬件平台 +扩展名
    - rpm包在光盘中  	
     - rpm安装命令(yum装的就是rpm包,不能查询和校验)
     - 包全名 :操作的包是没有安装的软件包时,使用包全名,而且要注意路径
     - 包名 :操作的是已经安装的软件包时,使用包名,是搜索/var/lib/rpm/中的数据库
    		- rpm -ivh 包全名
      			选项:
      				-i 安装 install
      				-v 显示详细信息 verbose
      				-h 显示进度 hash
      				--nodeps 不检测依赖性

      		- rpm -Uvh 包全名
      			选项:
      				-U 升级 upgrade

      		- rpm -e 包名称
      			选项:
      				-e 卸载 erase
      				- --nodeps 不检查依赖性
      - rpm包查询
        		rpm -q 包名 查询包是否安装
        			-q 查询 query
        		rpm -qa 查询所有已经安装的rpm包
        			- -a 所有 all
        		rpm -qi 包名
      			-i 查询软件信息 information
      			-p 查询未安装包信息
        		rpm -ql 包名  查询包的安装位置
        			-l 列表 list
        			-p 查询未安装包信息
        		rpm -qf 查询这个文件属于哪个包
        			-f 文件 file

        		rpm -qR 包名 查询软件包的依赖性
        			-R 查询软件包的依赖性 requires
        			-p 查询未安装包信息 package
      - rpm包校验
      		rpm -V 已安装的包名
      		 -V 校验指定rpm包中的文件verify
      		 - 验证内容中的8个信息的具体内容如下:
      			S 文件大小是否改变
      			M 文件的类型或文件的权限(rwx)是否被改变
      			5 文件MD5校验和是否改变,可以看成文件内容是否改变
      			D 设备的主从代码是否改变
      			L 文件路径是否改变
      			U 文件的主(所有者)是否改变
      			G 文件的属组是否改变
      			T 文件的修改是否改变

      			[文件类型]/root/ss.conf

      			c 配置文件config file
      			d 普通文档 documentation
      			g "鬼文件"(ghost file),很少见,就是该文件不应该被这个rpm包包含
      			L 授权文件license file
      			r 描述文件 read me
      -	rpm包中文件提取
      		rpm2cpio包全名 | cpio -idv .文件绝对路径
      			-rpm2cpio 将rpm包转换为cpio格式的命令
      			-cpio   <  [文件|设备]是一个标准工具,它用于创建软件档案文件
      			和从档案文件中提取文件
      				-i :copy -in模式,还原
      				-d:还原时自动新建目录
      				-v:显示还原过程

      - yum在线安装(查询需要用rpm包中的查询命令)
      		优点:将所有软件包放在官方服务器上,当进行yum在线安装时,可以自动解决依赖性问题
      		常用命令:
      		yum list 查询所有可用软件包
      		yum search 关键字 搜索服务器上所有和关键字相关的包
      		yum -y install 包名称
      			-install 安装
      			-y 自动回答yes
      		yum -y update 包名   升级
      		yum -y remove 包名(都不需要全包名)  卸载

      -	yum软件组管理命令
        		yum grouplist  列出所有可用的软件组列表
        		yum groupinstall 软件组名  安装指定软件组,组名可以由grouplist查询出来
        		yum groupremove 软件组名  卸载指定软件组

      - 源码包和RPM包的区别
          	RPM包安装位置(安装中的默认位置)
          	/etc/ 配置文件安装目录
          	/usr/bin/可执行的命令安装目录
          	/usr/lib/程序所使用的函数库保存位置
          	/usr/share/doc/基本的软件使用手册保存位置
          	/usr/share/man/帮助文件保存位置
          	可以使用 service启动

      	> 源码包安装位置要自己指定(因为源码包没有卸载命令)
      	一般安装在usr/local/软件名/
      	不可以使用 service 启动(修改位置可以启动)
      	源代码保存位置:/usr/local/src/
      	软件安装位置:/usr/local/
      	如何确定安装过程报错:
      		安装过程停止
      		出现error,warning或no的提示
      		安装过程:
      			- 下载源码包
      			- 解压缩下载的源码包
      			- 进入解压缩目录
      			./configure 命令  --prefix=安装位置(软件配置与检查)
      			 - 定义需要的功能选项
      			 - 检测系统环境是否符合安装要求
      			 - 把定义好的功能选项和检测系统环境的信息都写入Markfile文件
      			 ,用于后续的编辑
      		make 命令 编译
      		make clean 清除缓存,临时文件(报错情况下)
      		make install  安装命令

      - 脚本安装包
        > 下面是下载jdk安装包的方法
        	wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u111-b14/jdk-8u111-linux-x64.tar.gz -P/usr/downlife
