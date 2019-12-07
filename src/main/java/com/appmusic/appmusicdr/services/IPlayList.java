package com.appmusic.appmusicdr.services;

import com.appmusic.appmusicdr.model.PlayList;

import java.util.ArrayList;
import java.util.Optional;

public interface IPlayList {
    PlayList save(PlayList newList);
    ArrayList<PlayList> getAll();
    Optional<String> getDescription(String listName);
    Optional<PlayList> updateDescription(String listName, PlayList list);
    boolean delete(String name);
}
