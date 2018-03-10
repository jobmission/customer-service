package com.revengemission.customerservice;

import com.revengemission.customerservice.domain.ApplicationData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceApplicationTests {

    @Autowired
    ApplicationData applicationData;

    @Test
    @Ignore
    public void contextLoads() {
        applicationData.openAConversation();

        applicationData.onlineStaff(3l);
        applicationData.onlineStaff(1l);
        applicationData.onlineStaff(2l);

        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.openAConversation();
        applicationData.setEnableNewConversation(false);
        applicationData.offlineStaff(1l);
        applicationData.closeAConversation(3l);

        Map<Long, Integer> result = applicationData.sortMapByValueAsc();
        result.forEach((k, v) -> {
            System.out.println("排序完后返回 map: " + k + "->" + v);
        });

        if (result.size() > 0) {
            System.out.println("K =" + applicationData.getFirstElement(result).getKey());
            System.out.println("V =" + applicationData.getFirstElement(result).getValue());
        }
    }

}
