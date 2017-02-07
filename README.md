#基于SSM-EasyUI的权限管理系统

后台采用Spring+SpringMvc+MyBatis，前端使用Easyui，数据库采用MySql实现的一套权限管理系统。

项目地址：[https://github.com/cskun/rds-sys](https://github.com/cskun/rds-sys "https://github.com/cskun/rds-sys")

## 项目页面预览

![登录页](http://upload-images.jianshu.io/upload_images/1185917-cdfeb1c70ccabb9a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![主页](http://upload-images.jianshu.io/upload_images/1185917-b163ba70a43e0c59.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![资源列表](http://upload-images.jianshu.io/upload_images/1185917-6117fd52e9b8175f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![添加资源](http://upload-images.jianshu.io/upload_images/1185917-bac93a71bcfef99d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![角色列表](http://upload-images.jianshu.io/upload_images/1185917-66e8df99436c90f5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![机构列表](http://upload-images.jianshu.io/upload_images/1185917-427d520f2d49d6d6.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![添加机构](http://upload-images.jianshu.io/upload_images/1185917-c59c096aef22e41e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![用户列表](http://upload-images.jianshu.io/upload_images/1185917-81c81a3351d51b3b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 项目源码预览

工程是采用IDEA开发，基于MAVEN构建的，结构如下：

![目录结构](http://upload-images.jianshu.io/upload_images/1185917-122b02425e6f2f83.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

java: 对应Java源码

resources:对应的配置文件

webapp/jsp:对应模块的jsp文件

webapp/resources:对应的js/css等文件


## 项目部署
1、创建数据库
> 执行doc/rds-sys.sql脚本（可以修改sql中的数据库名，同时得修改resources/properties/db.properties，两者保持一致）

2、在本地用IDEA新建一个rds-sys（也可是其它名字，之后要修改pom.xml中对应的 finalName等名）的MAVEN工程

3、下载源码拷贝到自己新建的MAVEN工程替换即可

> git clone https://github.com/cskun/rds-sys.git

4、执行方式

1、在rds工程下创建rds-sys-live Module

![创建Maven工程](http://upload-images.jianshu.io/upload_images/1185917-d00d1f95aefc49a6.gif?imageMogr2/auto-orient/strip)

2、将下载的源码拷贝到rds-sys-live工程下

![拷贝src/和pom.xml到工程下](http://upload-images.jianshu.io/upload_images/1185917-b0ec40782093a4a0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

3、修改pom.xml替换rds-sys为rds-sys-live（我们的工程名）

![修改pom.xml替换rds-sys为rds-sys-live](http://upload-images.jianshu.io/upload_images/1185917-430cbfcb507498a7.gif?imageMogr2/auto-orient/strip)

4、使用Maven插件运行项目

![1.gif](http://upload-images.jianshu.io/upload_images/1185917-1a871b7d33b8ded3.gif?imageMogr2/auto-orient/strip)

5、运行即可

![运行即可](http://upload-images.jianshu.io/upload_images/1185917-238740bd36aa862a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

默认的端口在pom.xml的plugins下配置

![配置默认访问路径和端口](http://upload-images.jianshu.io/upload_images/1185917-8764ab339591d310.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

如果部署没有出现错误，那么访问[http://localhost:8084/](http://localhost:8084/ "http://localhost:8084/")即可跳转到登录页面
