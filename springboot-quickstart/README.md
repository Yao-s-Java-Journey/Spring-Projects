# 创建 SpringBoot 工程
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
  </dependencies>
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

# IOC&DI ★★★
**控制反转**（Inversion of Control）：对象的创建控制权由程序自身转移到外部容器（不需要我们自己在程序中 new 对象），这种思想称为控制反转；

**依赖注入**（Dependency Injection）：容器为应用程序提供运行时所依赖的资源，称之为依赖注入；

**Bean 对象**：IOC 容器中创建、管理的对象，称之为 Bean。

## 基本步骤
1. 给类添加 `@Component` 注解，程序启动后，会自动创建该类对象，并交由 IOC 容器处理；

```java
@Component
public class DeptServiceImpl implements DeptService {
    // 具体业务逻辑......
}
```

2. 在需要使用的地方声明变量（不需要 new），并添加 `@Autowire` 注解。此时 `@Autowire` 会自动从 IOC 容器中寻找对应的 Bean 对象，为该变量赋值。

```java
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
}
```

## 常用 IOC 注解
| 注解 | 说明 | 位置 |
| --- | --- | --- |
| @Component | 声明 Bean 的基础注解 | 不属于以下三类时，用此注解 |
| @Controller | @Component 的衍生注解 | 标注在控制层类上 |
| [@Service](#qg4ne) | @Component 的衍生注解 | 标注在业务层类上 |
| @Repository | @Component 的衍生注解 | 标注在数据访问层类上（由于与 mybatis 整合，用的少） |


## @ComponentScan 扫描
+ 声明的 Bean 注解想要生效，还需要被组件扫描注解 `@ComponentSacn`扫描；
+ 该注解虽然没有显式配置，但是实际上已经包含在了启动类声明注解 `@SpringBootApplication`中，<font style="color:#DF2A3F;">默认扫描的范围是启动类所在包及其子包</font>（所以请将启动类和子包放在一个目录里，不要随意挪动位置）；
+ 如果想要指定扫描的路径，可以传递 value 值 `@ComponentScan("com.aa")`，多个可以使用数组 `@ComponentScan({"com.aa", "com.bb"})`

## 多个相同类型的 Bean 冲突
`@AutoWired` 注解默认是按照类型进行的，当 Spring 容器中存在多个相同类型的 Bean 时，默认情况下会抛出 `NoUniqueBeanDefinitionException` 错误。

### 方式一：[@Primary](#HjvHc)
### 方式二：[@Qualifier](#RzaOt)
### 方式三：@Resource
+  `@Resource` <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">是 Java EE 标准注解，支持按名称或类型注入；</font>
+ <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">在 Spring 中， </font>`@Autowired`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 更常用，但 </font>`@Resource`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 适用于需要</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">按名称注入</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">的场景；</font>
+ <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">如果 Bean 名称和字段名不一致，建议用 </font>`@Resource(name = "xxx")`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">明确指定；</font>
+ <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">参数 name 默认是 Bean 的首字母小写的名称，如果 </font>`@Service("customName")`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">指定了名字，则参数 name 为 "customName"；</font>
+ `@Resource`等价于 `@Autowired` + `@Qualifier`

```java
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    // @Resource(name = "userServiceImpl")  // 默认按 Bean 首字母小写名称注入
    @Resource(name = "service1")  // 如果 Bean 用 @Service 指定了名字，那么这边也要换
    private UserService userService;

    public void saveUser() {
        userService.save();
    }
}
```

```java
@Service("service1")
public class UserServiceImpl implements UserService {
    public void save() {}
}
```

# 注解手册
## @RestController
请求处理类

## @ResponseBody
**类型：** 方法注解、类注解；

**位置：** Controller 方法上、类上；

**作用：** 将方法返回值直接响应，如果返回值类型是 实体对象/集合，将会转换成 JSON 格式响应；

**说明：** @RestController = @Controller + @ResponseBody

## @RequestMapping
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">提供了多个属性来精确控制请求映射。</font>

```java
@RequestMapping(
    value = "/orders/{orderId}",
    method = RequestMethod.GET,
    params = "confirmed=true",
    headers = "X-Requested-With=XMLHttpRequest"
)
```

GET 请求可以简写为：`@GetMapping("/users")`

## @Component
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">用于标记一个类为 </font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">Spring 容器管理的 Bean</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">，Spring 会自动检测并注册被 </font>`@Component`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 标注的类到 IOC 容器中。</font>

## @Autowired
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">是 </font>**Spring Framework** 提供的依赖注入（Dependency Injection, DI）注解，用于自动装配（自动注入）Bean 之间的依赖关系。它可以应用于 **构造方法、Setter 方法、普通方法或字段**，Spring 会根据类型（默认）或名称（配合 `@Qualifier`）自动匹配并注入所需的 Bean。

## @Controller
`@Component`的衍生注解

## <font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">@Service</font>
`@Component`的衍生注解

可以传递 value 属性，给 Bean 设置名字，默认为类名首字母小写，也可以自定义名字：`@Service("customBeanName")`

## @Repository
`@Component`的衍生注解

## @ComponentScan
<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">用于 </font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">自动扫描并注册 Spring Bean</font>**<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">。它通常与 </font>`@Configuration`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 或 </font>`@SpringBootApplication`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 一起使用，让 Spring 自动发现并管理标注了 </font>`@Component`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">、</font>`@Service`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">、</font>`@Repository`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);">、</font>`@Controller`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 等注解的类。</font>

扫描不到 Bean 时，可以指定包路径。

## @Primary
**指定优先 Bean** ，`@Primary` 标记的 Bean 会被优先注入，除非显式使用 `@Qualifier` 指定其他 Bean。

`@Primary` 优先级低于 `@Qualifier`

```java
@Service // @Component 的衍生注解，标注为一个业务层类
@Primary // 指定该 Bean 优先级最高
public class DeptServiceImpl1 implements DeptService {}
```

## @Qualifier
用于在 **多个相同类型的 Bean** 存在时，**精确指定要注入哪一个 Bean**。它通常与 `@Autowired`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 配合使用，解决 </font>**多Bean 冲突** 问题。

如果业务逻辑需要精确控制注入哪个 Bean，建议使用 `@Qualifier` 而不是 `@Primary`

在需要精确控制时，`@Qualifier`<font style="color:rgba(0, 0, 0, 0.9);background-color:rgb(252, 252, 252);"> 比 </font>`@Primary` 更灵活。

```java
// @Component 的衍生注解，标注为一个业务层类
// 取名 service1
@Service("service1")
public class DeptServiceImpl1 implements DeptService {}

// @Component 的衍生注解，标注为一个业务层类
// 取名 service2
@Service("service2")
public class DeptServiceImpl2 implements DeptService {}
```

```java
@RestController
public class DeptController {
    @Autowired
    @Qualifier("service2") // 指定第二个 DeptService
    private DeptService deptService;
}
```

`@Qualifier`<font style="color:rgba(0, 0, 0, 0.9);"> 优先级高于 </font>`@Primary`

