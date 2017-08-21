package com.github.spring.zookeeper.example.song;

import com.github.spring.zookeeper.example.album.Album;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Represents a song in a album.
 */
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SongID", nullable = false, insertable = false, updatable = false)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Duration")
    private long duration;

    @ManyToOne
    @JoinColumn(name = "AlbumID")
    private Album album;

    public Song() {}

    public Song(String name, long duration, Album album) {
        this.name = name;
        this.duration = duration;
        this.album = album;
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

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return id + " # " + name;
    }
}
