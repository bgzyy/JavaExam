## Java Exam
1. Exam1 
   - 随机数代码，启动 Main.java 即可
2. Exam2 
   - 使用 db.properties 配置数据库连接属性，使用 JDBC 连接数据库
   - 使用 config.properties 配置 CountryId 和 CustomerId
   - 可以更改 config.properties 中的属性值去查询不同的结果

## 环境搭建
1. Exam1
   - IDEA
   - 执行 Main.java
2. Exam2
   - 启动之前执行 parent-project\SQL 下的 sakila-data.sql 和 sakila-schema.sql 文件，创建好数据库和表以及对应的数据
   - 修改 db.properties 文件中的 mysql 服务的 ip
   - 修改 config.properties 中的属性值为你想要查询的值
   - 执行 Main.java