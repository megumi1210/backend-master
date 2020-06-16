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
 
 
 ###5.plugins(插件)
 插件是MyBatis 中最强大灵活的组件，同时也是最复杂和最难以使用的组件，而且十分危险，
 因为它覆盖了MyBatis底层对象的核心方法和属性，如果操作不当将产生严重后果
 
 
 
 ###5.environment(运行环境)
 运行环境主要的作用是配置数据库信息，它可以配置多个数据库，一般而言只需要配置其中一个就可以了
 它的下面又可以分为两个可以配置的元素：事务管理器(transactionManager)、数据源(dataSource)

