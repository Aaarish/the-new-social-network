package com.roya.the_new_social_network.shelves;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelfDao extends JpaRepository<Shelf, String> {
}
