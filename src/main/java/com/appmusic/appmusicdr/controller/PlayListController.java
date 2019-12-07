package com.appmusic.appmusicdr.controller;


import java.util.ArrayList;
import java.util.Optional;

import com.appmusic.appmusicdr.model.PlayList;
import com.appmusic.appmusicdr.services.IPlayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayListController {
    @Autowired
    IPlayList playListService;

    @PostMapping(value = "/lists")
    public PlayList saveSong(@RequestBody PlayList newPlayList) {
        return playListService.save(newPlayList);
    }

    @GetMapping(value="/lists")
    public ArrayList<PlayList> getLists() {
        return playListService.getAll();
    }

    @GetMapping(value="/lists/{name}")
    public Optional<String> getListDescription(@PathVariable(value="name") String name){
        return playListService.getDescription(name);
    }

    @PutMapping(value="/lists/{name}")
    public Optional<PlayList> modifiedList(@PathVariable(value="name") String name, @RequestBody PlayList update){
        return playListService.updateDescription(name, update);
    }

    @DeleteMapping(value="/lists/{name}")
    public boolean deleteList(@PathVariable(value="name") String name){
        return playListService.delete(name);
    }
}
