package com.appmusic.appmusicdr.services.impl;


import com.appmusic.appmusicdr.dao.IdaoPlayList;
import com.appmusic.appmusicdr.model.PlayList;
import com.appmusic.appmusicdr.services.IPlayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlayListS implements IPlayList {

    @Autowired
    private IdaoPlayList daoPlayList;

    @Override
    @Transactional
    public PlayList save(PlayList newPlayList) {
        return daoPlayList.save(newPlayList);
    }

    @Override
    @Transactional
    public ArrayList<PlayList> getAll() {
        ArrayList<PlayList> allPlayLists = new ArrayList<>();
        for(PlayList playLists: daoPlayList.findAll()){
            allPlayLists.add(playLists);
        }
        return allPlayLists;
    }

    @Override
    public Optional<String> getDescription(String listName) {
        Optional<PlayList> playLists= daoPlayList.findById(listName);
        return playLists.map(x->x.getDescription());

    }

    @Override
    public Optional<PlayList> updateDescription(String listName, PlayList playLists) {
        Optional<PlayList> newPlayLists = daoPlayList.findById(listName);
        newPlayLists.map(name->{name.setDescription(playLists.getDescription());
            return name;});
        return newPlayLists;

    }

    @Override
    public boolean delete(String name) {
        boolean listExist = daoPlayList.existsById(name);
        daoPlayList.deleteById(name);
        return listExist;
    }
}
