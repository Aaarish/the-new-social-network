package com.roya.the_new_social_network.forum.posts.dao;

import com.roya.the_new_social_network.forum.posts.entities.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostDaoCustomImpl implements PostDaoCustom {
    private final EntityManager entityManager;

    @Override
    public List<Post> findFeedPostsForProfile(String profileId, int projectPostsLimit, int mentorPostsLimit, int mentorProjectPostsLimit, int categoryPostsLimit) {
        // Single native query that combines all feed sources
        String sql = """
            WITH profile_data AS (
                -- Get user's project IDs
                SELECT DISTINCT pm.project_id as source_id, 'PROJECT' as source_type
                FROM project_members pm
                WHERE pm.profile_id = :profileId
            ),
            mentor_data AS (
                -- Get mentor profile IDs
                SELECT DISTINCT m.profile_id as source_id, 'MENTOR' as source_type
                FROM apprentices a
                JOIN mentors m ON a.mentor_id = m.id
                WHERE a.profile_id = :profileId
            ),
            mentor_project_data AS (
                -- Get open projects from mentors
                SELECT DISTINCT p.id as source_id, 'MENTOR_PROJECT' as source_type
                FROM apprentices a
                JOIN mentors m ON a.mentor_id = m.id
                JOIN project_members pm ON pm.profile_id = m.profile_id
                JOIN projects p ON p.id = pm.project_id
                WHERE a.profile_id = :profileId
                  AND p.project_joining_strategy = 'OPEN'
            ),
            preference_data AS (
                -- Get user's preference categories
                SELECT DISTINCT pref.category as source_id, 'CATEGORY' as source_type
                FROM preferences pref
                WHERE pref.profile_id = :profileId
            ),
            -- Fetch posts from user's projects
            project_posts AS (
                SELECT p.*, 'PROJECT' as feed_source, p.created_at as sort_date
                FROM posts p
                WHERE p.project_id IN (SELECT source_id FROM profile_data)
                ORDER BY p.created_at DESC
                LIMIT :projectPostsLimit
            ),
            -- Fetch posts from mentors
            mentor_posts AS (
                SELECT p.*, 'MENTOR' as feed_source, p.created_at as sort_date
                FROM posts p
                WHERE p.author_id IN (SELECT source_id FROM mentor_data)
                  AND p.project_id IS NULL  -- Only personal posts
                ORDER BY p.created_at DESC
                LIMIT :mentorPostsLimit
            ),
            -- Fetch posts from mentor's open projects
            mentor_project_posts AS (
                SELECT p.*, 'MENTOR_PROJECT' as feed_source, p.created_at as sort_date
                FROM posts p
                WHERE p.project_id IN (SELECT source_id FROM mentor_project_data)
                ORDER BY p.created_at DESC
                LIMIT :mentorProjectPostsLimit
            ),
            -- Fetch posts by preference categories
            category_posts AS (
                SELECT p.*, 'CATEGORY' as feed_source, p.created_at as sort_date
                FROM posts p
                WHERE p.category IN (SELECT source_id FROM preference_data)
                  AND p.id NOT IN (
                      SELECT id FROM project_posts
                      UNION SELECT id FROM mentor_posts
                      UNION SELECT id FROM mentor_project_posts
                  )  -- Avoid duplicates
                ORDER BY p.created_at DESC
                LIMIT :categoryPostsLimit
            )
            -- Combine all sources and sort by date
            SELECT * FROM (
                SELECT * FROM project_posts
                UNION ALL
                SELECT * FROM mentor_posts
                UNION ALL
                SELECT * FROM mentor_project_posts
                UNION ALL
                SELECT * FROM category_posts
            ) combined_posts
            ORDER BY sort_date DESC
            LIMIT 50
            """;

        Query query = entityManager.createNativeQuery(sql, Post.class);
        query.setParameter("profileId", profileId);
        query.setParameter("projectPostsLimit", projectPostsLimit);
        query.setParameter("mentorPostsLimit", mentorPostsLimit);
        query.setParameter("mentorProjectPostsLimit", mentorProjectPostsLimit);
        query.setParameter("categoryPostsLimit", categoryPostsLimit);

        @SuppressWarnings("unchecked")
        List<Post> results = query.getResultList();

        return results;
    }
}
