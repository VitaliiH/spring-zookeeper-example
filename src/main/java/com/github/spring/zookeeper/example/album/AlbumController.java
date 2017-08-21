package com.github.spring.zookeeper.example.album;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * A controller to test whether entity manager is working properly with {@link Album} entity.
 */
@RestController
@RequestMapping("/album")
public class AlbumController {
    private static final Logger log = LoggerFactory.getLogger(AlbumController.class);

    @Autowired
    AlbumService albumService;

    @RequestMapping(value = "/{id}", method = GET)
    public Album get(@PathVariable("id") Long id) {
        log.info("loading album with id: {}", id);
        return albumService.get(id);
    }

    @RequestMapping("/create")
    public Album create(@RequestParam String name) {
        return albumService.save(name);
    }
}
