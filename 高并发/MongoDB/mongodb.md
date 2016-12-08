## MongoDB
+ 简介
  >  1. MongoDB 是由C++语言编写的，是一个基于分布式文件存储的开源数据库系统。
   2. 在高负载的情况下，添加更多的节点，可以保证服务器性能。
   3. MongoDB 旨在为WEB应用提供可扩展的高性能数据存储解决方案。
   4. MongoDB 将数据存储为一个文档，数据结构由键值(key=>value)对组成。MongoDB 文档类似于 JSON 对象。字段值可以包含其他文档，数组及文档数组。
+ 主要特点
  - MongoDB的提供了一个面向文档存储，操作起来比较简单和容易。
  - 你可以在MongoDB记录中设置任何属性的索引 (如：FirstName="Sameer",Address="8 Gandhi Road")来实现更快的排序。
	- 你可以通过本地或者网络创建数据镜像，这使得MongoDB有更强的扩展性。
  -	如果负载的增加（需要更多的存储空间和更强的处理能力） ，它可以分布在计算机网络中的其他节点上这就是所谓的分片。
	- Mongo支持丰富的查询表达式。查询指令使用JSON形式的标记，可轻易查询文档中内嵌的对象及数组。
	- MongoDb 使用update()命令可以实现替换完成的文档（数据）或者一些指定的数据字段 。
	- Mongodb中的Map/reduce主要是用来对数据进行批量处理和聚合操作。
	- Map和Reduce。Map函数调用emit(key,value)遍历集合中所有的记录，将key与value传给Reduce函数进行处理。
	- Map函数和Reduce函数是使用Javascript编写的，并可以通过db.runCommand或mapreduce命令来执行MapReduce操作。
	- GridFS是MongoDB中的一个内置功能，可以用于存放大量小文件。
	- MongoDB允许在服务端执行脚本，可以用Javascript编写某个函数，直接在服务端执行，也可以把函数的定义存储在服务端，下次直接调用即可。
	- MongoDB支持各种编程语言:RUBY，PYTHON，JAVA，C++，PHP，C#等多种语言。
	- MongoDB安装简单。

  - ![](/images/mongdb1.jpg)

+ Mongdb安装
    1. 下载
    2. 解压
    3. 设置环境变量,bin目录
    4. 启动,配置文件启动或者命令启动
            ./mongod --dbpath /home/montest/node/data/db --logpath /home/montest/node/log/mongodb.log --fork --bind_ip 120.77.22.187 --port 27000
            ./mongod  --config mongodb.conf
            ./mongo –host ip –port port

+ Mongodb配置文件
  - 日志文件位置
  logpath=/var/log/mongo/mongod.log
  - 以追加方式写入日志
    - logappend=true
  - 是否以守护进程方式运行
    - fork = true
  - 默认27017
    - port = 27017
  - 数据库文件位置
    - dbpath=/var/lib/mongo
  - 启用定期记录CPU利用率和 I/O 等待
    - cpu = true
  - 是否以安全认证方式运行，默认是不认证的非安全方式
    - noauth = true
    - auth = true
  - 详细记录输出
    - verbose = true
  - Inspect all client data for validity on receipt (useful for developing drivers)用于开发驱动程序时验证客户端请求
    - objcheck = true
  - 启用数据库配额管理
    - quota = true
  - 设置oplog记录等级
  - Set oplogging level where n is
    -   0=off (default)
    -   1=W
    -   2=R
    -   3=both
    -   7=W+some reads
    - diaglog=0
  - Diagnostic/debugging option 动态调试项
    - nocursors = true
  - Ignore query hints 忽略查询提示
    - nohints = true
  - 禁用http界面，默认为localhost：28017
    - nohttpinterface = true
  - 关闭服务器端脚本，这将极大的限制功能
    - noscripting = true
  - 关闭扫描表，任何查询将会是扫描失败
    - notablescan = true
  - 关闭数据文件预分配
    - noprealloc = true
  - 为新数据库指定.ns文件的大小，单位:MB
    - nssize =
  - Replication Options 复制选项
    - replSet=setname
  - maximum size in megabytes for replication    operation log
    - oplogSize=1024
  - 指定存储身份验证信息的密钥文件的路径
    - keyFile=/path/to/keyfile

+ 数据库
      一个mongodb中可以建立多个数据库。
      MongoDB的默认数据库为"db"，该数据库存储在data目录中。
      MongoDB的单个实例可以容纳多个独立的数据库，每一个都有自己的集合和权限，不同的数据库也放置在不同的文件中。
      "show dbs" 命令可以显示所有数据的列表。
      运行"use"命令，可以连接到一个指定的数据库。

+ 文档
      文档是一个键值(key-value)对(即BSON)。MongoDB 的文档不需要设置相同的字段，并且相同的字段不需要相同的数据类型，这与关系型数据库有很大的区别，也是 MongoDB 非常突出的特点。
      一个简单的文档例子如下：
      {“name”:”jack”,”sex”:”man”}
      需要注意的是：
      1、文档中的键/值对是有序的。
      2、文档中的值不仅可以是在双引号里面的字符串，还可以是其他几种数据类型（甚至可以是整个嵌入的文档)。
      3、MongoDB区分类型和大小写。
      4、MongoDB的文档不能有重复的键。
      5、文档的键是字符串。除了少数例外情况，键可以使用任意UTF-8字符。

    **BSON（）是一种类json的一种二进制形式的存储格式**

+ 集合
      集合就是 MongoDB 文档组，类似于 RDBMS （关系数据库管理系统：Relational Database Management System)中的表格。
      集合存在于数据库中，集合没有固定的结构，这意味着你在对集合可以插入不同格式和类型的数据，但通常情况下我们插入集合的数据都会有一定的关联性。
      比如，我们可以将以下不同数据结构的文档插入到集合中

+ Mongodb索引
      索引通常能够极大的提高查询的效率，如果没有索引，MongoDB在读取数据时必须扫描集合中的每个文件并选取那些符合查询条件的记录。
      这种扫描全集合的查询效率是非常低的，特别在处理大量的数据时，查询可以要花费几十秒甚至几分钟，这对网站的性能是非常致命的。
      索引是特殊的数据结构，索引存储在一个易于遍历读取的数据集合中，索引是对数据库表中一列或多列的值进行排序的一种结构
      建索引过程会阻塞其它数据库操作，background可指定以后台方式创建索引，即增加 "background" 可选参数。 "background" 默认值为false。
      db.connection1.ensureIndex({name:1},{background:true})

+ Mongodb副本集
  ![](/images/mongodb2.jpg)
  > Mongodb(M)表示主节点，Mongodb(S)表示备节点，Mongodb(A)表示仲裁节点。

  > 主备节点存储数据，仲裁节点不存储数据。客户端同时连接主节点与备节点，不连接仲裁节点。

  > 仲裁节点是一种特殊的节点，它本身并不存储数据，主要的作用是决定哪一个备节点在主节点挂掉之后提升为主节点，所以客户端不需要连接此节点。

  > 在MongoDB副本集中，主节点负责处理客户端的读写请求，备份节点则负责映射主节点的数据。

  > 备份节点的工作原理过程可以大致描述为，备份节点定期轮询主节点上的数据操作，然后对自己的数据副本进行这些操作，从而保证跟主节点的数据同步。
  > 至于主节点上的所有 数据库状态改变 的操作，都会存放在一张特定的系统表中。备份节点则是根据这些数据进行自己的数据更新。
  > oplog,上面提到的 数据库状态改变 的操作，称为oplog（operation log，主节点操作记录）。oplog存储在local数据库的" oplog.rs"表中。副本集中备份节点异步的从主节点同步oplog，然后重新执行它记录的操作，以此达到了数据同步的作用。
  > Oplog注意点：
  - Oplog的大小是固定的，当集合被填满的时候，新的插入的文档会覆盖老的文档。
  - Oplog同步数据
  	- 初始化：这个过程发生在当副本集中创建一个新的数据库或其中某个节点刚从宕机中恢复，或者向副本集中添加新的成员的时候，默认的，副本集中的节点会从离它最近的节点复制oplog来同步数据，这个最近的节点可以是primary也可以是拥有最新oplog副本的secondary节点。
  	- 复制：在初始化后这个操作会一直持续的进行着,以保持各个secondary节点之间的数据同步。

  > 启动node1、node2、node3

      初始化副本集配置—仲裁节点配置模式
      rs.initiate({"_id":"jackSet",members:[{"_id":1,"host":"120.77.22.187:27001",priority:2},{"_id":2,"host":"120.77.22.187:27002", priority:1},{"_id":3,"host":"120.77.22.187:27003",arbiterOnly:true}]})

      初始化副本集配置—无仲裁节点配置模式
      rs.initiate({"_id":"jackSet",members:[{"_id":1,"host":"120.77.22.187:27001"},{"_id":2,"host":"120.77.22.187:27002"},{"_id":3,"host":"120.77.22.187:27003" }]})

+ MongoDB Sharding集群
  > sharding通过将数据集分布于多个也称作分片(shard)的节点上来降低单节点的访问压力。每个分片都是一个独立的数据库，所有的分片组合起来构成一个逻辑上的完整意义的数据库。因此，分片机制降低了每个分片的数据操作量及需要存储的数据量

  ![](/images/mongodb3.jpg)
        A。shards:分片，即数据结点，存储数据和执行计算。为了保证高可用和数据一致性，生产环境中shards应该做成
        replicasets(防止丢失数据)。集群中有一个primary shards，执行非分片的任务。
        B。mongos(query routers):查询路由，负责client的连接，并把任务分给shards，然后收集结果。一个集群中可以有多个query routers(replica sets)，以分担客户端请求(负载均衡)。
        C。config server:配置服务器。保存了集群的元数据(比如数据放在哪个shards上)，query router通过config server中的配置信 息决定把任务分配到哪个shards上。从3.2开始，config servers可以做成replica sets

        mongodb的shard功能实现于collection级别，但若要在collection上启动shard，还需要事先其相关的数据库上启用之。在数据库上启用shard功能后，MongoDB会为其指定一个主shard。

    - 配置config Server
      - 采用副本集的形式
      config1
              mongod --dbpath /home/mongodb/shares/configsvr/config1/data/db --configsvr --replSet jackshare --port 28000 --fork --logpath /home/mongodb/shares/configsvr/config1/log/configsvr.log --bind_ip 120.77.22.187
              config2
              mongod --dbpath /home/mongodb/shares/configsvr/config2/data/db --configsvr --replSet jackshare --port 28001 --fork --logpath /home/mongodb/shares/configsvr/config2/log/configsvr.log --bind_ip 120.77.22.187
              config3
              mongod --dbpath /home/mongodb/shares/configsvr/config3/data/db --configsvr --replSet jackshare --port 28002 --fork --logpath /home/mongodb/shares/configsvr/config3/log/configsvr.log --bind_ip 120.77.22.187

    - 副本集初始化:
            rs.initiate({"_id":"jackshare",configsvr:true,members:[{"_id":1,host:"120.77.22.187:28000"},{"_id":2,host:"120.77.22.187:28001"},{"_id":3,host:"120.77.22.187:28002"}]})
            rs.status()

    - 配置mongos路由
            mongos --configdb jackshare/120.77.22.187:28000,120.77.22.187:28001,120.77.22.187:28002 --fork --logpath /home/mongodb/shares/mongos/log/mongos.log --bind_ip 120.77.22.187 --port 30000

    - 添加分片到集群
            sh.addShard("mongodbSet-jack/120.77.22.187:27006")
            sh.status()观察分片状态
    - 设置数据库分片
      > 在设置集合分片之前,必须设置分片的数据库.链接mongos:
            mongo --host <hostname of machine running mongos> --port <port mongos listens on>
            执行：
            use daetabase
            sh.enableSharding("<database>")或者db.runCommand( { enableSharding: <database> } )

      - 设置集合分片
              1)	确定集合的shard key。如果集合已经有数据，那么在shard key上创建index。如果没有数据，集群会自动为shard key创建索引。
              用字段索引分键
              用字段hash分键
              2)将集合加入分片
              进入到admin库，use admin
              sh.shardCollection("<database>.<collection>", shard-key-pattern)
              如：
              sh.shardCollection("records.people", { "zipcode": 1, "name": 1 } )  shard key 为zipcode，如果有相同的zipcode再根据name来分
              sh.shardCollection("people.addresses", { "state": 1, "_id": 1 } )      同上
              sh.shardCollection("assets.chairs", { "type": 1, "_id": 1 } )       同上
              sh.shardCollection("events.alerts", { "_id": "hashed" } )           hash分片
      - 观察chunk的平衡
              Chunks：理解MongoDB分片机制的关键是理解Chunks。mongodb不是一个分片上存储一个区间，而是每个分片包含多个区间，这每个区间就是一个块。
