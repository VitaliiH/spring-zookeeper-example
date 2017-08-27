package com.github.spring.zookeeper.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.CountDownLatch;

/**
 * An example class, that helps to react on changes in Zookeeper storage. Is suitable for example only,
 * because mostly contains hardcoded values, and works good only if target test is covered with {@link @DirtiesContext}
 * annotation.
 * Performs a {@code countDownLatch#countDown()} on {@code RefreshScopeRefreshedEvent}. The event ensures that all
 * properties were successfully fetched from Zookeeper.
 *
 * To ensure that only exact path was updated, additional event listener might be added:
 * {@code
 *   @EventListener
 *   void handle(RefreshEvent event) {
 *       TreeCacheEvent treeEvent = (TreeCacheEvent) event.getEvent();
 *       if (treeEvent.getType().equals(TreeCacheEvent.Type.NODE_UPDATED) && treeEvent.getData().getPath().equals("/config/myapplication/name")) {
 *       log.debug("latch updated: " + countDownLatch);
 *   }
 * }
 * }
 * Having this, the {@code countDownLatch} instance should be instantiated with value 2, because now 2 events must causes
 * successful update of the application. The {@link RefreshEvent} is fired too early and can't be used by itself.
 */
public class ZookeeperValueRefreshEventWrapper {
    private static final Logger log = LoggerFactory.getLogger(ZookeeperValueRefreshEventWrapper.class);

    private CountDownLatch countDownLatch;

    public ZookeeperValueRefreshEventWrapper() {
        countDownLatch = new CountDownLatch(1);
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    @EventListener
    private void handle(RefreshScopeRefreshedEvent event) {
        countDownLatch.countDown();
        log.debug("latch updated: " + countDownLatch);
    }
}
