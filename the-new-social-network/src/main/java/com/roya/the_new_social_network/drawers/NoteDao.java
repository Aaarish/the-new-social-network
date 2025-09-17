package com.roya.the_new_social_network.drawers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends MongoRepository<Note, Long> {
}
