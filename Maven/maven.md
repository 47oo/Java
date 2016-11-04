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
    - 如同类的继承关系,就近原则,就近依赖
    - 配置文件中的scope标签
