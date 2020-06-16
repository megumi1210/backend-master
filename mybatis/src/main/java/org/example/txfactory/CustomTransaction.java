package org.example.txfactory;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**自定义事务类，可以自定义事务规则，满足特殊的需求
 * @author chenj
 */
public class CustomTransaction extends JdbcTransaction implements Transaction {

    public CustomTransaction(DataSource ds, TransactionIsolationLevel desiredLevel, boolean desiredAutoCommit) {
        super(ds, desiredLevel, desiredAutoCommit);
    }

    public CustomTransaction(Connection connection) {
        super(connection);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void commit() throws SQLException {
        super.commit();
    }

    @Override
    public void rollback() throws SQLException {
        super.rollback();
    }

    @Override
    public void close() throws SQLException {
        super.close();
    }

    @Override
    public Integer getTimeout() throws SQLException {
        return super.getTimeout();
    }
}
