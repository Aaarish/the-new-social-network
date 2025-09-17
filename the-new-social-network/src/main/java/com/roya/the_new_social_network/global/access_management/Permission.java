package com.roya.the_new_social_network.global.access_management;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum Permission {
    READ(1),
    WRITE(2),
    DELETE(3);

    private int preference;

}
