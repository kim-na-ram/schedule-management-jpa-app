package com.bootcamp.schedulemanagementjpaapp.service;

import com.bootcamp.schedulemanagementjpaapp.entity.Manage;
import com.bootcamp.schedulemanagementjpaapp.entity.Schedule;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import com.bootcamp.schedulemanagementjpaapp.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.repository.ManageJPARepository;
import com.bootcamp.schedulemanagementjpaapp.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.bootcamp.schedulemanagementjpaapp.contstant.ResponseCode.NOT_EXIST_USER;

@Service
@RequiredArgsConstructor
public class ManageServiceImpl implements ManageService {
    private final ManageJPARepository manageRepository;
    private final UserJPARepository userRepository;

    @Override
    @Transactional
    public void addManagerList(Set<String> managerEmailList, Schedule schedule) {
        deleteManageList(schedule.getId());
        manageRepository.flush();

        List<Manage> manageList = new ArrayList<>();
        for (String managerEmail : managerEmailList) {
            User user = userRepository.findByEmail(managerEmail)
                    .orElseThrow(() -> new ApiException(NOT_EXIST_USER));
            manageList.add(new Manage(schedule, user));
        }

        manageRepository.saveAll(manageList);
    }

    @Override
    @Transactional
    public void deleteManageList(Long scheduleId) {
        manageRepository.deleteAllBySchedule_Id(scheduleId);
    }
}
