### Git
***
+ Git介绍
  > Git是一个开源的分布式版本控制系统，用于敏捷高效地处理任何或小或大的项目。
Git 是 Linus Torvalds 为了帮助管理 Linux 内核开发而开发的一个开放源码的版本控制软件。
Git 与常用的版本控制工具 CVS, Subversion 等不同，它采用了分布式版本库的方式，不必服务器端软件支持。
git是一个版本控制工具. 跟svn、cvs是同级的概念
github是一个用git做版本控制的项目托管平台。

***
+ Git和SVN的区别
> GIT不仅仅是个版本控制系统，它也是个内容管理系统(CMS),工作管理系统等。
如果你是一个具有使用SVN背景的人，你需要做一定的思想转换，来适应GIT提供的一些概念和特征。

  > Git 与 SVN 区别点：
  1. GIT是分布式的，SVN不是：这是GIT和其它非分布式的版本控制系统，例如SVN，CVS等，最核心的区别。
  2. GIT把内容按元数据方式存储，而SVN是按文件：所有的资源控制系统都是把文件的元信息隐藏在一个类似.svn,.cvs等的文件夹里。
  3. GIT分支和SVN的分支不同：分支在SVN中一点不特别，就是版本库中的另外的一个目录。
  4. GIT没有一个全局的版本号，而SVN有：目前为止这是跟SVN相比GIT缺少的最大的一个特征。
  5. GIT的内容完整性要优于SVN：GIT的内容存储使用的是SHA-1哈希算法。这能确保代码内容的完整性，确保在遇到磁盘故障和网络问题时降低对版本库的破坏。

***
+ Git 安装配置
> Git 各平台安装包下载地址为：http://git-scm.com/downloads
      Redhat安装
      Yum install git
      Git –help
      Git –version查看版本

  > 设置用户名和email
      git config --global user.name "47"
      git config --global user.email 136698493@qq.com
      在根目录下查看.gitconfig文件内容 ls -al
      git config --list 查看配置信息

***
+ Git 工作区,暂存区和版本库
  - 工作区:就是你在电脑里面能看到的目录
  - 暂存区:英文名叫stage,或者index,一般放在.git目录下的index文件中,所以我们把暂存区有时候也叫做索引(index)
  - 脚本库:工作区有一个隐藏目录.git,这个不算工作区,而是Git的版本库

***
+ Git 创建仓库
      Git init， Git 使用 git init 命令来初始化一个 Git 仓库，在执行完成 git init 命令后，Git 仓库会生成一个 .git 目录，该目录包含了资源的所有元数据，其他的项目目录保持不变（不像 SVN 会在每个子目录生成 .svn 目录，Git 只在仓库的根目录生成 .git 目录）。

      git add filename  把文件加入git版本控制
      使用 git add 命令将想要快照的内容写入缓存区，这时候实际上只是将内容写入到缓冲区
      git commit
      执行 git commit 将缓存区内容添加到仓库中
      git commit –m “描述”
      git commit –a 省去麻烦，自动把修改内容添加到缓存区，然后打开编辑器
      git commit –am “修改描述”

      git reset HEAD filename
      执行 git reset HEAD 以取消之前 git add 添加，但不希望包含在下一提交快照中的缓存，不希望被提交的缓存内存，可以通过该命令去除，但是修改的内容依然在缓存中

      git rm filename
      删除文件
      git rm –cached filename
      从缓冲区移除

      git mv
      git mv filename1 filename2 必须向把filename加入到缓冲区
      git clone
      我们使用 git clone 从现有 Git 仓库中拷贝项目（类似 svn checkout）
      git clone repo directory
***
+ Git分支管理
      查看分支
      git branch
      创建分支
      git branch name
      切换分支
      git checkout name
      删除分支
      git branch –d name
      合并分支
      git merge name  必须在主分支执行
      合并冲突演示
***
+ Git 查看提交历史
      git log
      git log –oneline

***

+ Git 标签
  > 如果你达到一个重要的阶段，并希望永远记住那个特别的提交快照，你可以使用 git tag 给它打上标签。
比如说，我们想为我们的 w3cschoolcc 项目发布一个"1.0"版本。 我们可以用 git tag -a v1.0 命令给最新一次提交打上（HEAD）"v1.0"的标签。

  > -a 选项意为"创建一个带注解的标签"。 不用 -a 选项也可以执行的，但它不会记录这标签是啥时候打的，谁打的，也不会让你添加个标签的注解。 我推荐一直创建带注解的标签。

      git tag –a v1.0 最后一次提交的标签
      git log --oneline –decorate  查看标签信息
      git tag
      git show v1.0 查看标签信息
      git reset –hard 457512  commit id取6位就可以了
***
+ Git远程仓库
      如果你没有Github可以在官网https://github.com/注册。
      由于你的本地Git仓库和GitHub仓库之间的传输是通过SSH加密的，所以我们需要配置验证信息：
      使用以下命令生成SSH Key：
      ssh-keygen -t rsa -C "jack@dongnaoedu.com "
      后面的jack@dongnaoedu.com改为你在github上注册的邮箱，之后会要求确认路径和输入密码，我们这使用默认的一路回车就行。成功的话会在~/下生成.ssh文件夹，进去，打开id_rsa.pub，复制里面的key。回到github上，进入 Account Settings（账户配置），左边选择SSH Keys，Add SSH Key,title随便填，粘贴在你电脑上生成的key。
      验证是否配置成功：
      ssh -T git@github.com

      把本地仓库的项目push到GitHub中
      git remote add origin https://github.com/dn-jack/dn-jack-test-repository.git
      git push -u origin master

      更新远程仓库的修改内容到本地缓存
      git fetch origin
      把修改内容合并到分支
      git merge origin/master

      删除远程仓库
      git remote rm origin
