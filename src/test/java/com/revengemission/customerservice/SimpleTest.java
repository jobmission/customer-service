package com.revengemission.customerservice;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.revengemission.customerservice.domain.UserAccount;
import com.revengemission.customerservice.utils.IdWorker;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhang wanchao on 17-2-15.
 */
public class SimpleTest {

    @Test
    @Ignore
    public void testCaffeine() throws Exception {

        Cache<String, Object> objectCache = Caffeine.newBuilder()
                .maximumSize(10000)
                .expireAfterWrite(5, TimeUnit.MINUTES).build();

        UserAccount userAccount = new UserAccount();
        userAccount.setId(IdWorker.getSingleton(1, 1).nextId() + "");
        userAccount.setNickName(RandomStringUtils.randomAlphabetic(5, 20));

        objectCache.put("abc", userAccount);
        objectCache.invalidate("abc");
        if (objectCache.getIfPresent("abc") != null) {
            System.out.println("abc=" + objectCache.getIfPresent("abc"));
        } else {
            userAccount.setId(IdWorker.getSingleton(1, 1).nextId() + "");
            objectCache.put("abc", userAccount);
            System.out.println("abc=" + objectCache.getIfPresent("abc"));
        }
    }
}
