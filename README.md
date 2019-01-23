# spring-cloud-demo
1. 安装zookeeper

```
docker run --privileged=true -d --name zookeeper --publish 2181:2181  -d zookeeper:3.4.13
```

2. 启动项目

```
cd zookeeper-service
mvn spring-boot:run

cd zookeeper-client
mvn spring-boot:run

```

3. 测试

```
@post http://localhost:8081/api/demo/remote-instance
```



