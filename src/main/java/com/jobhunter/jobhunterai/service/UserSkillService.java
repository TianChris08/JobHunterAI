package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.dto.UserSkillRequest;
import com.jobhunter.jobhunterai.dto.UserSkillResponse;
import com.jobhunter.jobhunterai.model.MasterProfile;
import com.jobhunter.jobhunterai.model.Skill;
import com.jobhunter.jobhunterai.model.UserSkill;
import com.jobhunter.jobhunterai.repository.MasterProfileRepository;
import com.jobhunter.jobhunterai.repository.SkillRepository;
import com.jobhunter.jobhunterai.repository.UserSkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final MasterProfileRepository masterProfileRepository;
    private final SkillRepository skillRepository;

    public UserSkillService(
            UserSkillRepository userSkillRepository,
            MasterProfileRepository masterProfileRepository,
            SkillRepository skillRepository) {
        this.userSkillRepository = userSkillRepository;
        this.masterProfileRepository = masterProfileRepository;
        this.skillRepository = skillRepository;
    }

    @Transactional
    public UserSkillResponse saveUserSkill(UserSkillRequest request) {
        String email = getAuthenticatedEmail();

        MasterProfile masterProfile = getMasterProfile(email);

        Skill skill = skillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Habilidad no encontrada"));

        if (userSkillRepository.existsByMasterProfileIdAndSkillId(
                masterProfile.getId(), skill.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "La habilidad ya está asociada al perfil");
        }

        UserSkill userSkill = new UserSkill();
        userSkill.setMasterProfile(masterProfile);
        userSkill.setSkill(skill);

        return new UserSkillResponse(userSkillRepository.save(userSkill));
    }

    @Transactional(readOnly = true)
    public List<UserSkillResponse> getUserSkills() {
        String email = getAuthenticatedEmail();

        return userSkillRepository.findByMasterProfileUserEmail(email)
                .stream()
                .map(UserSkillResponse::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserSkillResponse getUserSkill(Long id) {
        return new UserSkillResponse(getOwnedUserSkill(id));
    }

    @Transactional
    public UserSkillResponse updateUserSkill(
            Long id, UserSkillRequest request) {

        UserSkill existing = getOwnedUserSkill(id);

        Skill skill = skillRepository.findById(request.getSkillId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Habilidad no encontrada"));

        if (!existing.getSkill().getId().equals(skill.getId())
                && userSkillRepository.existsByMasterProfileIdAndSkillId(
                existing.getMasterProfile().getId(), skill.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "La habilidad ya está asociada al perfil");
        }

        existing.setSkill(skill);

        return new UserSkillResponse(userSkillRepository.save(existing));
    }

    @Transactional
    public void deleteUserSkill(Long id) {
        userSkillRepository.delete(getOwnedUserSkill(id));
    }

    private UserSkill getOwnedUserSkill(Long id) {
        return userSkillRepository
                .findByIdAndMasterProfileUserEmail(id, getAuthenticatedEmail())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Habilidad asociada no encontrada"));
    }

    private MasterProfile getMasterProfile(String email) {
        return masterProfileRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Perfil maestro no encontrado"));
    }

    private String getAuthenticatedEmail() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }
}