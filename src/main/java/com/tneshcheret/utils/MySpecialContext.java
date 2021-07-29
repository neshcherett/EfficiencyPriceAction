package com.tneshcheret.utils;

public class MySpecialContext {

    private static ConnectionPool connectionPool;

    public static ConnectionPool get() {
        if (connectionPool == null) {
            connectionPool = new ConnectionPool();
            connectionPool.init();
        }

        return connectionPool;
    }

}
