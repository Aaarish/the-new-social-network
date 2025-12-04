package com.roya.the_new_social_network.global.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GlobalGenericResponse {
    private Object response;
    private String message;
    private ResponseStatus status;

    private enum ResponseStatus {
        SUCCESS,
        FAILURE,
        WARNING
    }

    public static GlobalGenericResponse generateSuccessResponse(Object response, String message) {
        return GlobalGenericResponse.builder()
                .response(response)
                .message(message)
                .status(ResponseStatus.SUCCESS)
                .build();
    }

    public static GlobalGenericResponse generateFailureResponse(Object response, String message) {
        return GlobalGenericResponse.builder()
                .response(response)
                .message(message)
                .status(ResponseStatus.FAILURE)
                .build();
    }

}
