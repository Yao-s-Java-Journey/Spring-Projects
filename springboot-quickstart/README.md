# 快速创建 SpringBoot 工程
## 方式一：IDEA 面板
![](https://cdn.nlark.com/yuque/0/2025/png/29092218/1746781231612-828c5890-7c24-422d-a2d1-1e515325bc67.png)

## 方式二：手动配置 pom.xml 文件
用方式一创建，只能选择较高版本的 JDK 和 SpringBoot。如果想安装低版本，需要自己手动配置 pom.xml 文件。具体步骤如下：

1. 正常创建 Maven Module；
2. 手动配置 pom.xml 文件，继承 SpringBoot 父工程，添加 web 依赖；
3. 编写启动类；

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<!-- 继承 SpringBoot 父工程-->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.4.5</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<groupId>com.spring</groupId>
	<artifactId>springboot-quickstart</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<properties>
		<java.version>17</java.version>
	</properties>

	<dependencies>
		<!-- web 起步依赖 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
</project>

```

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main() {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
```

# 配置端口
在 `src/main/resources/application.properties`配置文件中可以修改端口号。

```properties
spring.application.name=springboot-quickstart

# 端口配置
server.port=8888
```

# Maven 的 package 和 install
## package
pom.xml 添加了 SpringBoot 的打包插件后，package 打包出来的 jar 包可以直接运行。

```powershell
java -jar 包名.jar
```

## install
install 会在 Maven 的本地仓库安装当前的项目。安装后可以提供其他项目使用。
