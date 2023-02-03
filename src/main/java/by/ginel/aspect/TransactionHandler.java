package by.ginel.aspect;

import by.ginel.config.ConnectionHandler;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Aspect
@Component
public class TransactionHandler {
    @Autowired
    private ConnectionHandler connectionHandler;

    @Before(value = "@annotation(Transaction)")
    public void beforeExecute() throws InterruptedException, SQLException {
        Connection connection = connectionHandler.getConnection();
        connection.setAutoCommit(false);
        connection.setSavepoint();
    }

    @After(value = "@annotation(Transaction)")
    public void afterExecute() throws InterruptedException, SQLException {
        Connection connection = connectionHandler.getConnection();
        connection.commit();
        connectionHandler.releaseConnection();
    }

    @AfterThrowing(value = "@annotation(Transaction)", throwing = "ex")
    public void afterException(RuntimeException ex) throws SQLException, InterruptedException {
        Connection connection = connectionHandler.getConnection();
        connection.rollback();
        connectionHandler.releaseConnection();
    }
}
