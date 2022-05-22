# 第3周作业

## 作业内容

1.（必做）整合你上次作业的 httpclient/okhttp。
2.（选做）使用 Netty 实现后端 HTTP 访问（代替上一步骤）。
3.（必做）实现过滤器。
4.（选做）实现路由。
5.（选做）跑一跑课上的各个例子，加深对多线程的理解。
6.（选做）完善网关的例子，试着调整其中的线程池参数。

## 操作步骤

### 1.（必做）整合你上次作业的 httpclient/okhttp

1. 下载老师的项目: https://github.com/JavaCourse00/JavaCourseCodes
2. 解压, 拷贝其中 `02nio` 路径下的 `nio02` 目录到第三周的作业目录下。
3. Idea或者Eclipse打开这个Maven项目。
4. 增加自己的包名, 以做标识。
5. 将第二周的作业代码整合进来: [homework02](../Week_02/homework02/) 中的代码和pom.xml依赖。
6. 将 nio01 中的 HttpServer03 代码整合进来作为后端服务，改名为 [BackendServer](https://github.com/renfufei/JAVA-000/blob/main/Week_03/nio02/src/main/java/com/renfufei/homework03/BackendServer.java), 监听 8088 端口。
7. 找到Netty官网: https://netty.io/wiki/user-guide-for-4.x.html
8. 参照官方示例, 编写自己的过滤器 [ProxyBizFilter](https://github.com/renfufei/JAVA-000/blob/main/Week_03/nio02/src/main/java/com/renfufei/homework03/ProxyBizFilter.java);
9. 可以加入到 [HttpInboundHandler.java](https://github.com/renfufei/JAVA-000/blob/main/Week_03/nio02/src/main/java/io/github/kimmking/gateway/inbound/); 【实际上应该加入到 [HttpInboundInitializer](./nio02/src/main/java/io/github/kimmking/gateway/inbound/HttpInboundInitializer.java) 的初始化方法中】。
10. 修改 [HttpOutboundHandler](https://github.com/renfufei/JAVA-000/blob/main/Week_03/nio02/src/main/java/io/github/kimmking/gateway/outbound/httpclient4/HttpOutboundHandler.java) 类，集成自己写的第二周的作业代码；
