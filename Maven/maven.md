### Maven
+ 第一次使用maven
  > 第一次使用maven的时候会自动从中央仓库下载所需的依赖jar包,这里需要耐心等待

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
    - 配置文件中的scope标签
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
