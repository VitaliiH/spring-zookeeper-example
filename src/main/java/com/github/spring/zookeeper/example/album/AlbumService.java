package com.github.spring.zookeeper.example.album;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AlbumService {
    private static final Logger log = LoggerFactory.getLogger(AlbumService.class);

    @Autowired
    AlbumRepo albumRepo;

    public Album get(long id) {
        return albumRepo.findOne(id);
    }

    public Album save(String name) {
        log.info("Attempting to save album with name: {}", name);
        Album album = new Album(name);
        return albumRepo.save(album);
    }
}
