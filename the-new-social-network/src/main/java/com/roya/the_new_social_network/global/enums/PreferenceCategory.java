package com.roya.the_new_social_network.global.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum PreferenceCategory {
    SCIENCE(1),
    TECHNOLOGY(2),
    ART(3),
    MUSIC(4),
    SPORTS(5),
    TRAVEL(6),
    FOOD(7),
    LITERATURE(7),
    HISTORY(8),
    NATURE(9),
    FITNESS(10),
    GAMING(11),
    MOVIES(12),
    PHOTOGRAPHY(13),
    FASHION(14),
    POLITICS(15),
    ENVIRONMENT(16),
    EDUCATION(17),
    HEALTH(18),
    CULTURE(19),
    BUSINESS(20),
    FINANCE(21),
    SOCIAL_ISSUES(22),
    RELATIONSHIPS(23),
    SELF_IMPROVEMENT(24),
    HOBBIES(25),
    DIY(26),
    ANIMALS(27),
    ASTRONOMY(28),
    PHILOSOPHY(29);

    private int prefId;
}
