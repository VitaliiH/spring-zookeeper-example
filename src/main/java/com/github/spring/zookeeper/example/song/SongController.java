package com.github.spring.zookeeper.example.song;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * A controller to test whether entity manager is working properly with {@link Song} entity.
 */
@RestController
@RequestMapping("/song")
public class SongController {
    private static final Logger log = LoggerFactory.getLogger(SongController.class);

    @Autowired
    SongRepository songRepository;

    @RequestMapping("/create")
    public Song create(@RequestParam Song song) {
        return songRepository.save(song);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Song get(@PathVariable("id") Long id) {
        log.info("loading album with id: {}", id);
        return songRepository.findOne(id);
    }
}
