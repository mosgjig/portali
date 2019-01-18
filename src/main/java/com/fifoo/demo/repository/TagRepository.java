package com.fifoo.demo.repository;

import com.fifoo.demo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository  extends JpaRepository<Tag , Long> {

    Optional<Tag> findByTitle(String title);
    List<Tag> findByTitleIn(List<String> titles);
}
