package com.revengemission.customerservice.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationData implements Serializable {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private int perStaff = 5;
    private boolean enableNewConversation = true;
    private Map<Long, Integer> customerServiceStaff = new LinkedHashMap<>();

    public synchronized Long openAConversation() {
        if (enableNewConversation) {
            if (customerServiceStaff.size() == 0) {
                log.info("暂时没有在线客服，请稍后！");
                return null;
            } else {
                Map<Long, Integer> sorted = sortMapByValueAsc();
                Map.Entry<Long, Integer> firstEntry = getFirstElement(sorted);
                if (firstEntry.getValue() >= perStaff) {
                    log.info("在线客服忙，请稍后！");
                    return null;
                } else {
                    customerServiceStaff.put(firstEntry.getKey(), firstEntry.getValue() + 1);
                    return firstEntry.getKey();
                }
            }
        } else {
            log.info("在线客服已下线，请稍后！");
            return null;
        }
    }


    public synchronized void closeAConversation(Long staffId) {
        if (customerServiceStaff.containsKey(staffId)) {
            Integer total = customerServiceStaff.get(staffId);
            total--;
            if (total < 0) {
                total = 0;
            }
            customerServiceStaff.put(staffId, total);
        }
    }

    public int getStaffConversationCount(Long staffId) {
        if (customerServiceStaff.containsKey(staffId)) {
            return customerServiceStaff.get(staffId);
        } else {
            return 0;
        }
    }

    public synchronized void onlineStaff(Long staffId) {
        customerServiceStaff.put(staffId, 0);
    }

    public synchronized void offlineStaff(Long staffId) {
        if (customerServiceStaff.containsKey(staffId)) {
            customerServiceStaff.remove(staffId);
        }
    }

    public int onlineStaffCount() {
        return customerServiceStaff.size();
    }

    public Map<Long, Integer> sortMapByValueAsc() {
        Map<Long, Integer> finalMap = new LinkedHashMap<>();
        //Sort a map and add to finalMap
        customerServiceStaff.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));
        return finalMap;
    }

    public <K, V> Map.Entry<K, V> getFirstElement(Map<K, V> map) {
        return map.entrySet().iterator().next();
    }

    public int getPerStaff() {
        return perStaff;
    }

    public void setPerStaff(int perStaff) {
        this.perStaff = perStaff;
    }

    public boolean isEnableNewConversation() {
        return enableNewConversation;
    }

    public void setEnableNewConversation(boolean enableNewConversation) {
        this.enableNewConversation = enableNewConversation;
    }
}
