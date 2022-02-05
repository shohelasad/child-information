package com.bd.childinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.bd.childinfo.service.OrganizationService;


@Component
public class StartupHouseKeeper implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartupHouseKeeper.class);

    @Autowired
    private OrganizationService organizationService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("onApplicationEvent()");

        organizationService.indexOrganization();
    }
}
