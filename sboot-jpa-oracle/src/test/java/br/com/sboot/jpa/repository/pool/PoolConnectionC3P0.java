package br.com.sboot.jpa.repository.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.SQLException;

@SpringBootTest
public class PoolConnectionC3P0 {

    @Autowired
    ComboPooledDataSource dataSource;

    @Test
    public void testeC3P0_variasConexoes() throws SQLException, InterruptedException {

        for(int i = 1; i <= 50; i ++) {

            Thread.sleep(1000);

            dataSource.getConnection();
            System.out.println(i + " - Conexões existentes: " + dataSource.getNumConnections());
            System.out.println(i + " - Conexões ocupadas: " + dataSource.getNumBusyConnections());
            System.out.println(i + " - Conexões ociosas: " + dataSource.getNumIdleConnections());

            System.out.println("");
        }
    }

    @Test
    public void testeC3P0_conexoesOciosas() throws SQLException, InterruptedException {

        for(int i = 1; i <= 100; i ++) {
            dataSource.getConnection();

            Thread.sleep(1000);

            System.out.println(i + " - Conexões existentes: " + dataSource.getNumConnections());
            System.out.println(i + " - Conexões ocupadas: " + dataSource.getNumBusyConnections());
            System.out.println(i + " - Conexões ociosas: " + dataSource.getNumIdleConnections());

            System.out.println("");
        }
    }

}
