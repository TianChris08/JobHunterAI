package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.model.UserSkill;
import com.jobhunter.jobhunterai.repository.UserSkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;

    public UserSkillService(UserSkillRepository userSkillRepository) {
        this.userSkillRepository = userSkillRepository;
    }

    public UserSkill saveUserSkill(UserSkill userSkill) {
        return userSkillRepository.save(userSkill);
    }

    public List<UserSkill> getUserSkills() {
        return userSkillRepository.findAll();
    }

    public UserSkill getUserSkill(Long id) {
        return userSkillRepository.findById(id)
                .orElseThrow();
    }

    public UserSkill updateUserSkill(Long id, UserSkill userSkill) {
        UserSkill existingUserSkill = getUserSkill(id);

        existingUserSkill.setMasterProfile(userSkill.getMasterProfile());
        existingUserSkill.setSkill(userSkill.getSkill());

        return userSkillRepository.save(existingUserSkill);
    }

    public void deleteUserSkill(Long id) {
        userSkillRepository.deleteById(id);
    }
}