package org.example;

import org.apache.ibatis.session.SqlSession;

@FunctionalInterface
public interface SessionHandler {
    void   handler(SqlSession session);
}
