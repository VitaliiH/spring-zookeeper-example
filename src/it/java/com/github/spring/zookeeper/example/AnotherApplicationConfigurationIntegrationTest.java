package com.github.spring.zookeeper.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.test.TestingServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Pure copy-paste of the {@link ApplicationConfigurationIntegrationTest}. Just to make sure, {@code DirtiesContext} does its work.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnotherApplicationConfigurationIntegrationTest {
    private static TestingServer zkServer;
    private static CuratorFramework client;

    @Autowired
    ZookeeperValueRefreshEventWrapper testEventListenerWrapper;

    @Autowired
    HelloController helloController;

    @BeforeClass
    public static void setup() throws Exception {
        zkServer = new TestingServer(21891, true);
        client = CuratorFrameworkFactory.newClient(zkServer.getConnectString(), new ExponentialBackoffRetry(1000, 3));
        client.start();

        createEmptyNode("/config");
        createEmptyNode("/config/testtesttest");

        createNode("/config/testtesttest/name", "Vitalii");

        createEmptyNode("/config/testtesttest/datasource");

        createNode("/config/testtesttest/datasource/url", "jdbc:h2:mem:test");

        createNode("/config/testtesttest/datasource/driverClassName", "org.h2.Driver");

        createNode("/config/testtesttest/datasource/username", "sa");
        createNode("/config/testtesttest/datasource/password", "1234567");

        createEmptyNode("/config/testtesttest/hibernate");
        createNode("/config/testtesttest/hibernate/dialect", "org.hibernate.dialect.H2Dialect");

        createEmptyNode("/config/testtesttest/hibernate/hbm2ddl");
        createNode("/config/testtesttest/hibernate/hbm2ddl/method", "create-update");
    }

    @AfterClass
    public static void cleaup() throws IOException {
        zkServer.stop();
        client.close();
    }

    @Test
    public void testClientStarted() {
        assertEquals(client.getState(), CuratorFrameworkState.STARTED);
    }

    @Test
    public void testNamePropertyRefreshedFromZookeeper() throws Exception {
        //check default message
        assertEquals(helloController.index(), "Welcome here from author - Vitalii!");

        //update the value and wait until application is updated
        client.setData().forPath("/config/testtesttest/name", "me".getBytes());
        testEventListenerWrapper.getCountDownLatch().await();

        //check the value was updated
        assertEquals(helloController.index(), "Welcome here from author - me!");
    }

    private static void createEmptyNode(String path) throws Exception {
        createNode(path, "");
    }

    private static void createNode(String path, String value) throws Exception {
        client.create().forPath(path, value.getBytes());
    }
}