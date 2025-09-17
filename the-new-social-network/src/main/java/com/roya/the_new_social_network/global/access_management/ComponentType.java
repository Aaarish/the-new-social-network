package com.roya.the_new_social_network.global.access_management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum ComponentType {
    ALL(1),
    FORUM(2),
    SHELF(3),
    DRAWER(4),
    GALLERY(5);

    private int typeId;

}
