package com.roya.the_new_social_network.projects.access;

import com.roya.the_new_social_network.projects.members.ProjectMember;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project_access_policy_groups")
@Getter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProjectAccessPolicyGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long projectAccessPolicyGroupId;

    @Column(name = "group_name", nullable = false, unique = true)
    private String projectAccessPolicyGroupName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "project_access_policy_group_members",
            joinColumns = @JoinColumn(name = "access_policy_group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "id")
    )
    @Builder.Default
    private List<ProjectMember> members = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accessPolicyGroup", orphanRemoval = true)
    @Builder.Default
    private List<ProjectAccessPolicy> accessPolicies = new ArrayList<>();

    public void addMember(ProjectMember member) {
        this.members.add(member);
    }

    public void removeMember(ProjectMember member) {
        this.members.remove(member);
    }

    public void addAccessPolicy(ProjectAccessPolicy policy) {
        this.accessPolicies.add(policy);
        policy.setAccessPolicyGroup(this);
    }

}
