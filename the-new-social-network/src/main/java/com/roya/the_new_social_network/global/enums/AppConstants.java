package com.roya.the_new_social_network.global.enums;

import java.util.Map;

public class AppConstants {
    public static final String SEPARATOR = "_";

    // SCOPES

    public static final int PROFILE_TYPE_ID = 0;
    public static final int PROJECT_TYPE_ID = 1;

    public static final String PROFILE_SLUG = "PROFILE";
    public static final String PROJECT_SLUG = "PROJECT";

    public static final Map<Integer, String> SCOPE_MAP = Map.of(
            PROFILE_TYPE_ID, PROFILE_SLUG,
            PROJECT_TYPE_ID, PROJECT_SLUG
    );

    // COMPONENTS

    public static final int FORUM_COMPONENT_ID = 0;
    public static final int SHELF_COMPONENT_ID = 1;
    public static final int DRAWER_COMPONENT_ID = 2;
    public static final int GALLERY_COMPONENT_ID = 3;

    public static final String FORUM_SLUG = "FORUM";
    public static final String SHELF_SLUG = "SHELF";
    public static final String DRAWER_SLUG = "DRAWER";
    public static final String GALLERY_SLUG = "GALLERY";

    public static final Map<Integer, String> COMPONENT_MAP = Map.of(
        FORUM_COMPONENT_ID, FORUM_SLUG,
        SHELF_COMPONENT_ID, SHELF_SLUG,
        DRAWER_COMPONENT_ID, DRAWER_SLUG,
        GALLERY_COMPONENT_ID, GALLERY_SLUG
    );

    // ACTORS

    public static final int OWNER_ACTOR_ID = 0;
    public static final int MEMBER_ACTOR_ID = 1;
    public static final int APPLICANT_ACTOR_ID = 2;
    public static final int WATCHER_ACTOR_ID = 3;

    public static final int MENTOR_ACTOR_ID = 4;
    public static final int APPRENTICE_ACTOR_ID = 5;
    public static final int SELF_ACTOR_ID = 6;


    public static final String OWNER_SLUG = "OWNER";
    public static final String MEMBER_SLUG = "MEMBER";
    public static final String APPLICANT_SLUG = "APPLICANT";
    public static final String WATCHER_SLUG = "WATCHER";
    public static final String MENTOR_SLUG = "MENTOR";
    public static final String APPRENTICE_SLUG = "APPRENTICE";
    public static final String SELF_SLUG = "SELF";

    public static final Map<Integer, String> ACTOR_MAP = Map.of(
        OWNER_ACTOR_ID, OWNER_SLUG,
        MEMBER_ACTOR_ID, MEMBER_SLUG,
        APPLICANT_ACTOR_ID, APPLICANT_SLUG,
        WATCHER_ACTOR_ID, WATCHER_SLUG,
        MENTOR_ACTOR_ID, MENTOR_SLUG,
        APPRENTICE_ACTOR_ID, APPRENTICE_SLUG,
        SELF_ACTOR_ID, SELF_SLUG
    );

}
