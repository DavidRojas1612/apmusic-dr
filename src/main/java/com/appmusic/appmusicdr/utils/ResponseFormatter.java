package com.appmusic.appmusicdr.utils;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseFormatter {
    public static ResponseEntity formatterResponse(Response res){
        HttpStatus state = HttpStatus.valueOf(res.getCodeMessage());
        if(res.getState()){
            return new ResponseEntity(res.getMessageBody(), state);
        }else{
            return new ResponseEntity(state);
        }
    }
}
