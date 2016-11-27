### Redis
+ 简介
  > Redis 是完全开源免费的，是一个高性能的key-value数据库。
Redis 与其他 key - value 缓存产品有以下三个特点：

  > Redis支持数据的持久化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用。

  > Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储。

  > Redis支持数据的备份，即master-slave模式的数据备份。
性能极高 – Redis能读的速度是110000次/s,写的速度是81000次/s

+ Redis安装
    - 前提条件:gcc编译工具
          make 编译
          编译成功后，进入src文件夹，执行make install进行Redis安装
          make install  
          /usr/local/bin目录下有redis-server,redis-cli,redis-check-aof,redis-check-dump
          redis-server –v   检查是否安装成功
          启动redis，进入到redis.conf目录，执行
          redis-server redis.conf.如果后面没有跟redis.conf则按照默认配置启动

    - redis配置文件
          1. Redis默认不是以守护进程的方式运行，可以通过该配置项修改，使用yes启用守护进程
            daemonize yes
          2. 当Redis以守护进程方式运行时，Redis默认会把pid写入/var/run/redis.pid文件，可以通过pidfile指定
            pidfile /var/run/redis.pid
          3. 指定Redis监听端口，默认端口为6379，作者在自己的一篇博文中解释了为什么选用6379作为默认端口，因为6379在手机按键上MERZ对应的号码，而MERZ取自意大利歌女Alessia Merz的名字
            port 6379
          4. 绑定的主机地址
            bind 127.0.0.1   这个Ip要设置成你服务器的Ip
          5.当 客户端闲置多长时间后关闭连接，如果指定为0，表示关闭该功能
            timeout 300
          6. 指定日志记录级别，Redis总共支持四个级别：debug、verbose、notice、warning，默认为verbose
            loglevel verbose
          7. 日志记录方式，默认为标准输出，如果配置Redis为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给/dev/null
            logfile stdout
          8. 设置数据库的数量，默认数据库为0，可以使用SELECT <dbid>命令在连接上指定数据库id
            databases 16
          9. 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合
            save <seconds> <changes>
            Redis默认配置文件中提供了三个条件：
            save 900 1
            save 300 10
            save 60 10000
            分别表示900秒（15分钟）内有1个更改，300秒（5分钟）内有10个更改以及60秒内有10000个更改。

          10. 指定存储至本地数据库时是否压缩数据，默认为yes，Redis采用LZF压缩，如果为了节省CPU时间，可以关闭该选项，但会导致数据库文件变的巨大
            rdbcompression yes
          11. 指定本地数据库文件名，默认值为dump.rdb
            dbfilename dump.rdb
          12. 指定本地数据库存放目录
            dir ./
          13. 设置当本机为slav服务时，设置master服务的IP地址及端口，在Redis启动时，它会自动从master进行数据同步
            slaveof <masterip> <masterport>
          14. 当master服务设置了密码保护时，slav服务连接master的密码
            masterauth <master-password>
          15. 设置Redis连接密码，如果配置了连接密码，客户端在连接Redis时需要通过AUTH <password>命令提供密码，默认关闭
            requirepass foobared
          16. 设置同一时间最大客户端连接数，默认无限制，Redis可以同时打开的客户端连接数为Redis进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis会关闭新的连接并向客户端返回max number of clients reached错误信息
            maxclients 128
          17. 指定Redis最大内存限制，Redis在启动时会把数据加载到内存中，达到最大内存后，Redis会先尝试清除已到期或即将到期的Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis新的vm机制，会把Key存放内存，Value会存放在swap区
            maxmemory <bytes>
          18. 指定是否在每次更新操作后进行日志记录，Redis在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis本身同步数据文件是按上面save条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为no
            appendonly no
          19. 指定更新日志文件名，默认为appendonly.aof
             appendfilename appendonly.aof
          20. 指定更新日志条件，共有3个可选值：
            no：表示等操作系统进行数据缓存同步到磁盘（快）
            always：表示每次更新操作后手动调用fsync()将数据写到磁盘（慢，安全）
            everysec：表示每秒同步一次（折衷，默认值）
            appendfsync everysec

          21. 指定是否启用虚拟内存机制，默认值为no，简单的介绍一下，VM机制将数据分页存放，由Redis将访问量较少的页即冷数据swap到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析Redis的VM机制）
             vm-enabled no
          22. 虚拟内存文件路径，默认值为/tmp/redis.swap，不可多个Redis实例共享
             vm-swap-file /tmp/redis.swap
          23. 将所有大于vm-max-memory的数据存入虚拟内存,无论vm-max-memory设置多小,所有索引数据都是内存存储的(Redis的索引数据 就是keys),也就是说,当vm-max-memory设置为0的时候,其实是所有value都存在于磁盘。默认值为0
             vm-max-memory 0
          24. Redis swap文件分成了很多的page，一个对象可以保存在多个page上面，但一个page上不能被多个对象共享，vm-page-size是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page大小最好设置为32或者64bytes；如果存储很大大对象，则可以使用更大的page，如果不 确定，就使用默认值
             vm-page-size 32
          25. 设置swap文件中的page数量，由于页表（一种表示页面空闲或使用的bitmap）是在放在内存中的，，在磁盘上每8个pages将消耗1byte的内存。
             vm-pages 134217728
          26. 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4
             vm-max-threads 4
          27. 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启
            glueoutputbuf yes
          28. 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法
            hash-max-zipmap-entries 64
            hash-max-zipmap-value 512
          29. 指定是否激活重置哈希，默认为开启（后面在介绍Redis的哈希算法时具体介绍）
            activerehashing yes
          30. 指定包含其它的配置文件，可以在同一主机上多个Redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件
            include /path/to/local.conf

    - redis数据类型
      > Redis支持五种数据类型：string（字符串），hash（哈希），list（列表），set（集合）及zset(sorted set：有序集合)。

    - redis发布订阅
     - subscribe dongnao  客户端订阅消息，dongnao为相应的频道
     - publish dongnao message  发布消息，同时订阅该频道的客户端能收到该消息

    - redis事务
       - Redis事务可以一次执行多个命令,并且带有以下两个重要的保证:
          - 事物是一个单独的隔离操作.事务中的所有命令都会序列化,按顺序地执行.事务在执行的过程中,不会被其他客户端发来的命令请求所打断.
          - 事务是一个原子操作:事务中的命令要么全部被执行,要么全部都不执行.
          - 一个事务从开始到执行会经历以下三个阶段:
            1. 开始事务
            2. 命令入队
            3. 执行事务
          - MULTI  开始一个事物
                set dongnao jack
                get dongnao
                Hmset  map key1 value1 key2 value2
                Hmget map key1
                exec 执行事物

    - redis 数据备份与恢复
      > 默认情况下 每隔一段时间redis服务器程序会自动对数据库做一次遍历，把内存快照写在一个叫做“dump.rdb”的文件里，这个持久化机制叫做SNAPSHOT。有了SNAPSHOT后，如果服务器宕机，重新启动redis服务器程序时redis会自动加载dump.rdb，将数据库状态恢复到上一次做SNAPSHOT时的状态。

      > 手动进行内存备份： Save

    - redis 安全
          config get requirepass  获取密码
          config set requirepass 123
          config get requirepass
          auth password 密码验证

    - redis持久化
      - Snapshotting快照持久化
      - Append-only
            AOF
            redis会将每一个收到的写命令都通过write函数追加到文件中(默认是appendonly.aof)。当redis重启时会通过重新执行文件中保存的写命令来在内存中重建整个数据库的内容。
            appendonly yes           启用aof持久化方式
            appendfsync always   每次收到写命令就立即强制写入磁盘，最慢的，但是保证完全的持久化，不推荐使用
            appendfsync everysec     #每秒钟强制写入磁盘一次，在性能和持久化方面做了很好的折中，推荐
            appendfsync no    完全依赖os，性能最好,持久化没保证

    - redis主从
      >  redis主从复制过程： 当配置好slave后，slave与master建立连接，然后发送sync命令。无论是第一次连接还是重新连接，master都会启动一个后台进程，将 数据库快照保存到文件中，同时master主进程会开始收集新的写命令并缓存。后台进程完成写文件后，master就发送文件给slave，slave将 文件保存到硬盘上，再加载到内存中，接着master就会把缓存的命令转发给slave，后续master将收到的写命令发送给slave。如果master同时收到多个slave发来的同步连接命令，master只会启动一个进程来写数据库镜像，然后发送给所有的slave。master同步数据时是非阻塞式的，可以接收用户的读写请求。然而在slave端是阻塞模式的，slave在同步master数据时，并不能够响应客户端的查询。

      > 可以在master禁用数据持久化，只需要注释掉master 配置文件中的所有save配置，然后只在slave上配置数据持久化

    - redis集群
      > Redis 集群中内置了 16384 个哈希槽，当需要在 Redis 集群中放置一个 key-value时，redis 先对 key 使用 crc16 算法算出一个结果，然后把结果对 16384 求余数，这样每个 key 都会对应一个编号在 0-16383 之间的哈希槽，redis 会根据节点数量大致均等的将哈希槽映射到不同的节点。集群的每个节点负责一部分hash槽。这种结构很容易添加或者删除节点，并且无论是添加删除或者修改某一个节点，都不会造成集群不可用的状态。使用哈希槽的好处就在于可以方便的添加或移除节点。当需要增加节点时，只需要把其他节点的某些哈希槽挪到新节点就可以了；当需要移除节点时，只需要把移除节点上的哈希槽挪到其他节点就行了；在这一点上，我们以后新增或移除节点的时候不用先停掉所有的 redis 服务。Hash slots

      - 基于centos的集群
        1. 集群至少要有三个节点,每个节点都有主从结构
          > 新建三个文件夹,把redis.conf cp进去,然后修改配置文件端口,把支持集群配置放开,启动该节点

                cluster-enabled yes
                cluster-config-file nodes.conf
                cluster-node-timeout 5000
        2. 安装ruby
          - 依赖项
            > yum -y install zlib-devel curl-devel openssl-devel httpd-devel apr-devel apr-util-devel mysql-devel

        3. gem命令安装redis包,增加redis-trib.rb调用redis的接口包
          - gem install redis

                  gem换源
                  $ gem sources --add https://gems.ruby-china.org/ --remove https://rubygems.org/
                  $ gem sources -l
                  https://gems.ruby-china.org
                  确保只有 gems.ruby-china.org
                  gem更新
                  $ gem update --system # 这里请翻墙一下
                  $ gem -v
                  2.6.3

        4. 用./redis-trib.rb集群管工具管理redis集群
                ./redis-trib.rb  create --replicas 1 120.77.22.187:7000 120.77.22.187:8000 120.77.22.187:9000 120.77.22.187:7010 120.77.22.187:8010 120.77.22.187:9010
                --replicas 1 设置从节点个数
                进入集群模式去操作redis命令,-c集群模式
                redis-cli -c  -h –p

        5. redis集群添加节点
                增加节点
                ./redis-trib.rb add-node 127.0.0.1:7006 127.0.0.1:7000
                查看节点情况
                cluster nodes
                移动哈希槽，哈希槽重新分配
                ./redis-trib.rb reshard 127.0.0.1:7000
                输入all 表示从所有的主节点中随机转移，凑够xx个哈希槽

        6. redis集群删除节点
                把hash slots转出来，转到其他master节点上
                ./redis-trib.rb reshard 127.0.0.1:7000
                然后删除节点
                ./redis-trib.rb del-node 120.77.22.187:9020 94ee1415a16076f4070c5dd4b94defc14618dbb8

        7. redis-sentinel高可用
          > Redis-Sentinel是Redis官方推荐的高可用性(HA)解决方案，当用Redis做Master-slave的高可用方案时，假如master宕机了，Redis本身(包括它的很多客户端)都没有实现自动进行主备切换，而Redis-sentinel本身也是一个独立运行的进程，它能监控多个master-slave集群，发现master宕机后能进行自动切换。

          - 配置sentinel.conf文件
          > sentinel monitor mymaster 127.0.0.1 6379 1
            sentinel down-after-milliseconds mymaster 60000
            sentinel can-failover mymaster yes
            sentinel failover-timeout mymaster 180000
            sentinel parallel-syncs mymaster 1

          **需要注意的是，配置文件在sentinel运行期间是会被动态修改的，例如当发生主备切换时候，配置文件中的master会被修改为另外一个slave。这样，之后sentinel如果重启时，就可以根据这个配置来恢复其之前所监控的redis集群的状态。**
