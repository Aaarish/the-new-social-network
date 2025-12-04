//package com.roya.the_new_social_network.events;
//
//import lombok.*;
//import org.springframework.context.ApplicationEvent;
//
//import java.util.Map;
//
//@Getter @Builder
//public class AppEvent extends ApplicationEvent {
//    private String eventId;
//    private String name;
//    private Action action;
//    private EventType type;
//    @Setter
//    private Map<String, Object> payload;
//
//    public AppEvent(Object source) {
//        super(source);
//    }
//
//    public enum EventType {
//        SYSTEM_EVENT,
//        PROFILE_EVENT,
//        PROJECT_EVENT;
//    }
//
//    public enum Action {
//        ADD,
//        UPDATE,
//        DELETE;
//    }
//
//}
