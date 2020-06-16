package org.example.txfactory;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * 实现自定义的事物工厂
 * @author chenj
 */
public class CustomTransactionFactory implements TransactionFactory {
    @Override
    public void setProperties(Properties props) {

    }

    @Override
    public Transaction newTransaction(Connection conn) {
        return new CustomTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new CustomTransaction(dataSource,level,autoCommit);
    }
}
