package com.appmusic.appmusicdr.controller;


import java.util.ArrayList;
import java.util.Optional;

import com.appmusic.appmusicdr.model.PlayList;
import com.appmusic.appmusicdr.services.IPlayList;
import com.appmusic.appmusicdr.services.impl.PlayListS;
import com.appmusic.appmusicdr.utils.Response;
import com.appmusic.appmusicdr.utils.ResponseFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayListController {
    Response res = new Response();

    @Autowired
    IPlayList playListService;

    @PostMapping(value = "/lists")
    public ResponseEntity saveSong(@RequestBody PlayList newPlayList) {
        if(newPlayList.getName() == null || newPlayList.getName().isEmpty()){
            res.setCodeMessage(400);
            res.setMessage("400 Bad Request");
            return new ResponseEntity(res.getMessage(),HttpStatus.valueOf(res.getCodeMessage()));
        }
        PlayList newPlaylistResponse =  playListService.save(newPlayList);
        res.setCodeMessage(201);
        res.setMessageBody(newPlaylistResponse);
        return new ResponseEntity(res.getMessageBody(),HttpStatus.valueOf(res.getCodeMessage()));

    }

    @GetMapping(value="/lists")
    public ArrayList<PlayList> getLists() {
        return playListService.getAll();
    }

    @GetMapping(value="/lists/{name}")
    public ResponseEntity getListDescription(@PathVariable(value="name") String name){
        return ResponseFormatter.formatterResponse(playListService.getDescription(name));
    }

    @PutMapping(value="/lists/{name}")
    public ResponseEntity modifiedList(@PathVariable(value="name") String name, @RequestBody PlayList update){
        return ResponseFormatter.formatterResponse(playListService.updateDescription(name, update));
    }

    @DeleteMapping(value="/lists/{name}")
    public ResponseEntity deleteList(@PathVariable(value="name") String name){
        return ResponseFormatter.formatterResponse(playListService.delete(name));
    }
}
