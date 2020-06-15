# 深入理解 Mybatis 配置文件

###1.properties
可以减少硬编码，给系统配置一些运行参数

###2.settings(全局配置)
 具体配置可以查看官方文档 [MyBatis官方文档](https://mybatis.org/mybatis-3/zh/configuration.html)

###3.typeHandler(类型转换器)  
在 typeHandler 中，分为 jdbcType 和 javaType,其中jdbcType用于定于数据库类型，而
javaType 用于定义Java类型，那么typeHandler的作用是承担jdbcType和javaType的相互
转换，对于使用自定义枚举类型或者数据库使用特殊数据的时候可以使用自定义的TypeHandler
处理,具体细节查看BaseTypeHandler源码 


###4.ObjectFactory(对象工厂)
 当创建结果集时，MyBatis 会使用一个对象工厂来完成这个结果集实例。默认情况下会使用其自定义的对象工厂——
 DefaultObjectFactory,查看objectFactory的自定义案例

