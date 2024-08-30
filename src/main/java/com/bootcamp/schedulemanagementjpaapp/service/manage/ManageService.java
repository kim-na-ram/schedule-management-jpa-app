package com.bootcamp.schedulemanagementjpaapp.service.manage;

import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;

import java.util.Set;

public interface ManageService {
    void addManagerList(Set<String> managerEmailList, Schedule schedule);
    void deleteManageList(long scheduleId);
}
