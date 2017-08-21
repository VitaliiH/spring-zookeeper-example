package com.github.spring.zookeeper.example.album;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepo extends CrudRepository<Album, Long> {

    List<Album> findByName(String name);
}
