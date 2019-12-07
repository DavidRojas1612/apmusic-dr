package com.appmusic.appmusicdr.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;


@Entity
@Table(name= "playList")
public class PlayList implements Serializable {
    @Id
    private String name;
    private String description;
    private ArrayList<Song> songs;

    public PlayList() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
