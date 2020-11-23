package com.tyler.classroom;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * Redis helper class
 */
public class Redis {

    private String host; // host address of redis
    private String port; // redis port
    private RedisClient client;
    private StatefulRedisConnection <String, String> connection;
    private RedisCommands<String, String> commands;

    // Default Constructor
    public Redis() {

        //Try to find the environment variables for the host and the port for redis
        //If we can, use them
        //If we can't. default to localhost:6379
        host = System.getenv("REDIS_DB");
        port = System.getenv("REDIS_PORT");

        if (host == null) {
            System.out.println("HOST IS NULL???");
            host = "localhost";
        }

        if (port == null) {
            System.out.println("PORT IS NULL???");
            port = "6379";
        }
    }

    /**
     * Set
     * Set the key value pair in redis
     *
     * @param key String
     * @param val String
     */
    public void set(String key, String val) {
        client = RedisClient.create("redis://" + host + ":" + port);
        connection = client.connect();
        commands = connection.sync();

        commands.set(key, val);

        connection.close();
        client.shutdown();
    }

}
