### Zookeeper
+ 配置管理
  ![](/images/zookeeper.png)

+ 集群管理
  ![](/images/zookeeper2.png)

+ 简介
  > zkClient主要做了两件事情.一件是在session loss和session expire时自动创建新的zookeeper实例进行重连.另一件是将一次性的watcher包装为持久watcher.后者的具体做法是简单的在watcher中毁掉,重新读取数据的同时再注册相同的watcher实例

+ 分布式协调服务
  - 顺序一致性 简单说就是控制请求顺序
  - 原子性 节点数据要么成功要么失败
  - 单一视图 拿到的数据是一致的
  - 可靠性 状态在服务端都有保存
  - 实时性 有数据同步时间

 + 高性能
 + 简单api 来实现复杂功能

 + 名词解释
  - 版本号
    - cversion  子节点版本号
    - dataversion  当前节点的版本号
    - aclversion 节点权限发生变化
+ 服务命令
  ``` shell
  1. ./zkServer.sh start|stop|status|restart
  2. ./zkCli.sh [-timeout 0 -r]-server ip:port
  ```

+ 集群配置
  ``` shell
  server.id=host:port:port
  服务器地址:leader与follow数据同步和通信地址:leader选举

  另外,为了区分是不同的服务器,需要在dataDir下面下一个myid文件          
  ```
+ 节点类型
  - 持久化节点
  - 持久化有序节点 -s(sort)
  - 临时节点 -e(extemporaneous)
  - 临时有序节点 -se

+ 权限控制
  - 三种权限范围:
  ``` Java
    OPEN_ACL_UNSAFE ： 对所有用户开放

    READ_ACL_UNSAFE ： 只读

    CREATOR_ALL_ACL： 创建者可以做任何操作
  ```
