这个是仿造饿了么，搭建的一个分布式项目。

唯一的区别是，饿了么的RPC框架是内部开发的一套Pylon，我们这里用Dubbo来代替。

1. eleme-user-all
2. eleme-user
3. eleme-user-startup
4. eleme-user-service
5. eleme-user-controller

搭建完成后，先启动eleme-user-startup的Bootstrap类，然后在启动eleme-user-controller web服务。
