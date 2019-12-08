package com.appmusic.appmusicdr.services;

import com.appmusic.appmusicdr.model.PlayList;
import com.appmusic.appmusicdr.utils.Response;

import java.util.ArrayList;
import java.util.Optional;

public interface IPlayList {
    PlayList save(PlayList newList);
    ArrayList<PlayList> getAll();
    Response getDescription(String listName);
    Response updateDescription(String listName, PlayList list);
    Response delete(String name);
}
