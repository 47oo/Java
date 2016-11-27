### Maven
+ 第一次使用maven
  > 第一次使用maven的时候会自动从中央仓库下载所需的依赖jar包,这里需要耐心等待

+ maven项目的目录约定
  ![](/images/Maven目录.png)

+ maven三大生命周期
  > 生命周期maven有三套相互独立的声明周期

    - Clean 在进行真正的构建之前进行一些清理工作

    - Default 构建的核心部分,编译,测试,打包,部署

    - Site 生成项目报告,站点,发布站点

+  maven命令
  -  基础命令mvn [子命令]
  - mvn compile 对项目进行编译命令
  - mvn test 运行项目下test包下的测试类
  - mvn package 项目打包生成jar包+版本号(包括,编译测试打包,但不会同步到仓库)
  - mvn clean 会删除target包(文件夹)
  - mvn install  编译,测试,打包,放到本地仓库(用户/.m2/repository/com)
  - mvn site 生成项目站点信息(site文件夹)

+ pom配置文件
  - 如同类的继承关系,就近原则,就近依赖,声明优先
  - 配置文件中的**scope**标签
   > 1.compile:默认编译依赖范围.对于编译,测试,运行三种classpath都有效

    > 2.test:测试依赖范围.只对于测试classpath有效,只对测试代码有效

    > 3.provided:已提供依赖范围.对于编译,测试的classpath的时候都有效,但对于运行无效.因为已经由容器提供.例如servlet-api,打包的时候这个包不会打进去.(tomcat容器为例)

   > 4.runtime: 运行时提供.例如jdbc驱动.

   > compile,runtime打包会搭进去,test,provided打包不会打进去

+ Maven聚合和继承
    > 主要是可以起到公共配置文件的抽取作用,jar包的统一管理

    - 1.dependencyManagement中定义的依赖子module不会共享(只有子模块配置的时候才会被引用)
    - 2.depnedencies中定义的依赖子module可以共享
    - 3.jar包的统一管理,把jar抽取到parent中
      - 继承:
        > 子module(模组)继承父类(其实就是子pom.xml配置文件继承父pom.xml)在子pom的配置
              <parent>
              <groupId>com.cqupt.hh</groupId>
              <artifactId>parent</artifactId>
              <version>0.0.1-SNAPSHOT</version>
              <relativePath>../parent</relativePath>
              </parent>

      - 聚合
          > 父module将子module进行组合
              <modules>
            		<module>hello1Maven</module><--填子项目pom文件的路径-->
            		<module>maven1</module>
            	</modules>

+ Maven配置文件动态切换
      <build>
        <finalName>web</finalName>
        <resources>
          <resource>
            <directory>src/main/resources</directory>
            <includes>
              <include>配置文件的位置</include>
            </includes>
          </resource>
          <!-- 设置对auto-config.properties，jdbc.properties进行过虑，即这些文件中的${key}会被替换掉为真正的值 -->
          <resource>
            <directory>src/main/resources</directory>
            <includes>
              <include>jdbc.properties</include>
            </includes>
            <filtering>true</filtering>
          </resource>
        </resources>
      </build>
      <profiles>
        <profile>
          <id>test</id>
          <activation>
            <activeByDefault>false</activeByDefault>
          </activation>
          <properties>
            <jdbc.driver>test.mysql</jdbc.driver>
            <jdbc.url>test.url</jdbc.url>
            <jdbc.username>test.username</jdbc.username>
            <jdbc.password>test.password</jdbc.password>
          </properties>
        </profile>
        <profile>
          <id>dev</id>
          <activation>
            <activeByDefault>false</activeByDefault>
          </activation>
          <properties>
            <jdbc.driver>dev.mysql</jdbc.driver>
            <jdbc.url>dev.url</jdbc.url>
            <jdbc.username>dev.username</jdbc.username>
            <jdbc.password>dev.password</jdbc.password>
          </properties>
        </profile>
        <profile>
          <id>product</id>
          <activation>
            <activeByDefault>true</activeByDefault>
          </activation>
          <properties>
            <jdbc.driver>product.mysql</jdbc.driver>
            <jdbc.url>product.url</jdbc.url>
            <jdbc.username>product.username</jdbc.username>
            <jdbc.password>product.password</jdbc.password>
          </properties>
        </profile>
      </profiles>

+ Nexus私服
  - 说明:
    > Nexus 是Maven仓库管理器，如果你使用**Maven**，你可以从Maven中央仓库 下载所需要的构件（artifact），但这通常不是一个好的做法，你应该在本地架设一个Maven仓库服务器，在**代理远程仓库的**同时维护本地仓库，以节省带宽和时间，Nexus就可以满足这样的需要。此外，他还提供了强大的仓库管理功能，构件搜索功能，它基于REST，友好的UI是一个extjs的REST客户端，它占用较少的内存，基于简单文件系统而非数据库。这些优点使其日趋成为最流行的Maven仓库管理器。

  - Nexus内置仓库说明
    - (1) maven central:该仓库代理maven钟祥仓库,其策略为release,因此只会下载和缓存中央仓库中的发布版本构件

    - (2) releases:这是一种策略为release的宿主类型仓库,用来部署阻止内部的发布版本

    - (3) Snapshots:这是一种策略为Snapshot的宿主类型仓库,用来部署组织内部的快照版本构件

    - (4) 3rd party：这是一个策略为Release的宿主类型仓库，用来部署无法从公共仓库获得的第三方发布版本构件

    - (5) Public Repositories：该仓库组将上述所有策略为Release的仓库聚合并通过一致的地址提供服务。**可以理解为其他仓库的整合**

  - Maven配置来使用Nexus

    **通过配置maven中的setting.xml文件来指定请求时访问的路径**

    - setting.xml配置--mirror镜像

      mirror相当于一个**拦截器**，它会拦截maven对remote repository的相关请求，把请求里的remote repository地址，重定向到mirror里配置的地址
      ![](/images/mirror.png)
      ![](/images/mirror2.png)

  - Pom.xml文件配置
  Pom.xml中的配置

    **这一块可以不配置**
    	<repositories>
    			 <repository>
    				 <id>central</id>
    				 <url>http://localhost:8000/nexus/content/groups/public</url>
    				 <releases><enabled>true</enabled></releases>
    			 	 <snapshots><enabled>true</enabled></snapshots>
    			 </repository>
    		 </repositories>
    		 <pluginRepositories>
    			 <pluginRepository>
    				 <id>central</id>
    				 <url>http://localhost:8000/nexus/content/groups/public</url>
    				 <releases><enabled>true</enabled></releases>
    				 <snapshots><enabled>true</enabled></snapshots>
    			 </pluginRepository>
    		 </pluginRepositories>
      配置上传到私服：
    	<distributionManagement>
    	<repository>
    	    <id>releases</id>
    	    <name>Internal Releases</name>
    	    <url>http://localhost:8000/nexus/content/repositories/releases/</url>
    	</repository>
    	<snapshotRepository>
    	    <id>snapshots</id>
    	    <name>Internal Snapshots</name>
    	    <url>http://localhost:8000/nexus/content/repositories/snapshots/</url>
    	</snapshotRepository>
      </distributionManagement>

   **setting.xml配置**
          <servers>
           	<server>
           		<id> releases </id>
           <username>admin</username>
           <password>admin123</password>
           </server>
            <server>
            <id> snapshots </id>
            <username>admin</username>
            <password>admin123</password>
            </server>
          </servers>

- 更新遇到错误
  > was cached in the local repository, resolution will not be reattempted until the update interval of nexus has elapsed or updates are forced

  **将自己仓库下面的文件夹把xxx.last
  updated文件删掉即可,当然也有可能是中央仓库没有此文件,需要自己上传到nexus上面**
