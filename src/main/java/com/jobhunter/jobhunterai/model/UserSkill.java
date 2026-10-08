package com.jobhunter.jobhunterai.model;

import jakarta.persistence.*;

@Entity
@Table(
        name = "user_skills",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"master_profile_id", "skill_id"})
        }
)
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "master_profile_id", nullable = false)
    private MasterProfile masterProfile;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    public UserSkill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MasterProfile getMasterProfile() {
        return masterProfile;
    }

    public void setMasterProfile(MasterProfile masterProfile) {
        this.masterProfile = masterProfile;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}