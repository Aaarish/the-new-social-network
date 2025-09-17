package com.roya.the_new_social_network.projects.services;


public interface ProjectWatchService {
    void watch(WatcherRequestDto watcherRequest);
    void unWatch(String watcherId, WatcherRequestDto watcherRequest);

}
