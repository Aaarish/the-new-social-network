package com.roya.the_new_social_network.shelves.api.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionResponse {
    private String sectionId;
    private String shelfId;
    private String heading;
    private String content;
    private List<String> media;
    private List<String> urls;
    private int mediaCount;
    private int urlCount;

}
