package com.jobhunter.jobhunterai.service;

import com.jobhunter.jobhunterai.dto.SkillRequest;
import com.jobhunter.jobhunterai.dto.SkillResponse;
import com.jobhunter.jobhunterai.model.Skill;
import com.jobhunter.jobhunterai.repository.SkillRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public SkillResponse saveSkill(SkillRequest request) {
        if (request.getName() == null || request.getName().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }

        String name = request.getName().trim();
        String normalizedName = normalize(name);

        return skillRepository.findByNormalizedName(normalizedName)
                .map(SkillResponse::new)
                .orElseGet(() -> {
                    Skill skill = new Skill();
                    skill.setName(name);
                    skill.setNormalizedName(normalizedName);

                    return new SkillResponse(skillRepository.save(skill));
                });
    }

    public List<SkillResponse> getSkills() {
        return skillRepository.findAll()
                .stream()
                .map(SkillResponse::new)
                .toList();
    }

    public SkillResponse getSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Habilidad no encontrada"));

        return new SkillResponse(skill);
    }

    private String normalize(String name) {
        String normalized = Normalizer.normalize(
                name, Normalizer.Form.NFD);

        return normalized
                .replaceAll("\\p{M}", "")
                .trim()
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }
}