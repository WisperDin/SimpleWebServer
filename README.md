# SimpleWebServer
A Simple Web Server



一个简单的用Java实现的web http服务器

因为第一次使用Java试着实现,没有使用任何框架



## 目录解释

#### 1. config:配置文件模块--读取于根目录下的app.properties,通过反射到Config类中的变量中来使用

app.properties 实例

```
ServerPort=9999

DB_NAME=db
DB_HOST=localhost
DB_PORT=1234
DB_USER=root
DB_PWD=xxxx
```



#### 2. controller 放api的

####　3. customException 一些自定义异常

#### 4. db 数据库连接接口

#### 5. model 以ORM思想设计的数据库访问接口 

#### 6. test 单元测试

#### 7. utils 工具函数　



## 用到的包

#### 1.阿里的fastjson 用于json与java中class的互相映射

#### 2.JUnit4 单元测试

#### 3.mysql 驱动

---------

目前实现的功能为:

2017-11-4	用户注册的api实现
