package com.appmusic.appmusicdr.services.impl;


import com.appmusic.appmusicdr.dao.IdaoPlayList;
import com.appmusic.appmusicdr.model.PlayList;
import com.appmusic.appmusicdr.services.IPlayList;

import com.appmusic.appmusicdr.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PlayListS implements IPlayList {

    @Autowired
    private IdaoPlayList playListsDao;

    @Override
    @Transactional
    public PlayList save(PlayList newPlayList) {
        return playListsDao.save(newPlayList);
    }

    @Override
    @Transactional
    public ArrayList<PlayList> getAll() {
        ArrayList<PlayList> allPlayLists = new ArrayList<>();
        for(PlayList playLists: playListsDao.findAll()){
            allPlayLists.add(playLists);
        }
        return allPlayLists;
    }

    @Override
    public Response getDescription(String listName) {
        Response res = new Response();
        Optional<PlayList> playLists= playListsDao.findById(listName);
        if (!playLists.isPresent()){
            res.setCodeMessage(404);
            res.setState(false);
        } else{
            res.setState(true);
            res.setCodeMessage(200);
            res.setMessageBody(playLists.map(x -> x.getDescription()));
        }
        return res;
    }

    @Override
    @Transactional
    public Response updateDescription(String listName, PlayList playLists) {
        Response res = new Response();
        if(listName.equals(playLists.getName())) {
            Optional<PlayList> newPlayLists = playListsDao.findById(listName);
            if (!newPlayLists.isPresent()){
                res.setCodeMessage(404);
                res.setState(false);
            } else {
                Optional<PlayList> updatedPlayLists = newPlayLists.map(name->{name.setDescription(playLists.getDescription());
                    return name;});
                playListsDao.save(updatedPlayLists.get());
                res.setCodeMessage(204);
                res.setState(true);
                res.setMessageBody(updatedPlayLists);
            }
        } else {
            res.setCodeMessage(409);
            res.setState(false);
        }
        return res;
    }

    @Override
    public Response delete(String name) {
        Response res = new Response();
        boolean playlistExist = playListsDao.existsById(name);
        if (playlistExist) {
            playListsDao.deleteById(name);
            res.setCodeMessage(204);
        } else{
            res.setCodeMessage(404);
        }
        return res;
    }
}
