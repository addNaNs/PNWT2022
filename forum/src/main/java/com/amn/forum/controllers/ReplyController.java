package com.amn.forum.controllers;

import com.amn.forum.dto.PostRepository;
import com.amn.forum.dto.ReplyRepository;
import com.amn.forum.misc.JWTUtil;
import com.amn.forum.models.Post;
import com.amn.forum.models.Reply;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Claims;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "reply")
public class ReplyController {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody
    ResponseEntity<Object> addReply(
            @RequestHeader(name="Authorization", required = false) String auth,
            @RequestBody ObjectNode body
    ){

        Reply r = new Reply();
        r.setPost(postRepository.findById(body.get("post_id").asInt()).get());
        r.setText(body.get("text").asText());

        if(body.get("user_id") == null){
            r.setUserId(body.get("user_id").asInt());
        } else {
            Claims x = jwtUtil.getAllClaimsFromToken(auth.split(" ")[1]);
            r.setUserId((Integer) x.get("user_id"));
        }

        replyRepository.save(r);

        JSONObject entity = new JSONObject();
        entity.put("message","Saved");
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Iterable<Reply> getReplies(){
        return replyRepository.findAll();
    }

    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Reply getReply(@PathVariable(value="id") Integer id){
        return replyRepository.findById(id).get();
    }

    @GetMapping(path="post/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Iterable<Reply> getRepliesByPost(@PathVariable(value="id") Integer id){
        Iterable<Reply> replies = replyRepository.findAll();
        List<Reply> result = new ArrayList<>();
        for (Reply r: replies) {
            if(r.getPost().getId() == id){
                result.add(r);
            }
        }
        return result;
    }

    @DeleteMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> deleteReply(@PathVariable(value="id") Integer id){
        replyRepository.deleteById(id);

        JSONObject entity = new JSONObject();
        entity.put("message","Deleted");
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @PutMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> updateReply(
            @RequestHeader(name="Authorization", required = false) String auth,
            @RequestBody ObjectNode body
    ){
        Reply r = new Reply();

        if(body.get("post_id") == null) r.setPost(postRepository.findById(body.get("post_id").asInt()).get());
        if(body.get("text") == null) r.setText(body.get("text").asText());

        if(body.get("user_id") == null){
            r.setUserId(body.get("user_id").asInt());
        } else {
            Claims x = jwtUtil.getAllClaimsFromToken(auth.split(" ")[1]);
            r.setUserId((Integer) x.get("user_id"));
        }

        replyRepository.save(r);

        JSONObject entity = new JSONObject();
        entity.put("message","Updated");
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

}
