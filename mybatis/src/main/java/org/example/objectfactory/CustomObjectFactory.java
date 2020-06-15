package org.example.objectfactory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.log4j.Logger;

import java.util.Properties;


/**
 * @author chenj
 */
public class CustomObjectFactory extends DefaultObjectFactory {

    Logger logger = Logger.getLogger(CustomObjectFactory.class);

    private Object temp = null;

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    logger.info("初始化参数：+【" + properties.toString() + "】");
    }

    @Override
    public <T> T create(Class<T> type) {
       T result =  super.create(type);
       logger.info("创建对象：" + result.toString());
       logger.info("和上次创建的是否是同一个对象:" + temp == result);
       return  result;
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
