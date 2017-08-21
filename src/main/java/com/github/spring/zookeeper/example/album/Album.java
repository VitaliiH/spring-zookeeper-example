package com.github.spring.zookeeper.example.album;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.spring.zookeeper.example.song.Song;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Example entity, that represents an music album with song.
 */
@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AlbumID", nullable = false, insertable = false, updatable = false)
    private Long id;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Set<Song> songs;

    Album() { }

    Album(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " # " + name;
    }
}