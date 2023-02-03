package by.ginel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class ConnectionHandler {
    @Autowired
    private DataSource dataSource;
    private LinkedBlockingDeque<Connection> connections;
    private final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    @PostConstruct
    public void init() throws SQLException {
        connections = new LinkedBlockingDeque<>(10);
        for (int i = 0; i < 10; i++) {
            connections.offer(dataSource.getConnection());
        }
    }

    @PreDestroy
    public void destroy() {
        connections.forEach(connection -> {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    public Connection getConnection() throws InterruptedException {
        if(Objects.isNull(connectionThreadLocal.get())){
            Connection connection = connections.take();
            connectionThreadLocal.set(connection);
        }
        return connectionThreadLocal.get();
    }

    public void releaseConnection() throws InterruptedException {
        Connection connection = connectionThreadLocal.get();
        connectionThreadLocal.remove();
        connections.put(connection);
    }
}
