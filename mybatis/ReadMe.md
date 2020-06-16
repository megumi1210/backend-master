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

###6.cascade(级联)
MyBatis中级联分为三种  
1.鉴别器(discriminator) 它是一个根据一些条件采用具体的实现类的级联方案  
2.一对一(association) 一对一的级联  
3.一对多(collection)  
4.多对多 由两个一对多实现  
具体使用查看次项目中的案例，本项目中未实现另一种级联方式，另一种需要通过Sql语句的连接语句
来实现查询数据，每个数据下实现association 和collection 的开口标签内容，这种方式能看懂就好，
因为写出的Sql会难以维护，难以读懂，但是减少了N+1问题，而项目中使用的方式会有N+1问题，所以要
通过全局设置缓存或者单映射文件中使用 FetchType来细控制
  
###7.缓存(cache)
缓存一般放置在可以告诉读写的存储器上,比如服务器的内存。MyBatis分为一级缓存和
二级缓存。一级缓存是在SqlSession上的缓存，默认情况下，在没有开启任何配置的
情况下，Mybatis 系统会自动开启一级缓存，也就是在SqlSession的层面缓存,这个缓存
不需要POJO对象可序列化. 二级缓存是在SqlSessionFactory中。开启二级缓存只需要在
相应的映射文件中添加\<cache/>标签，这个时候MyBatis会序列化和反序列化对应的POJO,
POJO就必须要实现Serializable接口

###8.动态SQL