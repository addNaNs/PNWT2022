package com.amn.forum.controllers;


import com.amn.forum.dto.PostRepository;
import com.amn.forum.misc.JWTUtil;
import com.amn.forum.models.Post;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.jsonwebtoken.Claims;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> addPost(
            @RequestHeader(name="Authorization", required = false) String auth,
            @RequestBody ObjectNode body
    ){

        Post p = new Post();
        p.setTitle(body.get("title").asText());
        p.setText(body.get("text").asText());
        p.setCourseId(body.get("course_id").asInt());
        if(body.get("user_id") == null){
            p.setUserId(body.get("user_id").asInt());
        } else {
            Claims x = jwtUtil.getAllClaimsFromToken(auth.split(" ")[1]);
            p.setUserId((Integer) x.get("user_id"));
        }

        postRepository.save(p);

        JSONObject entity = new JSONObject();
        entity.put("message","Saved");
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Iterable<Post> getPosts(){
        return postRepository.findAll();
    }

    @GetMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Post getPost(@PathVariable(value="id") Integer id){
        return postRepository.findById(id).get();
    }

    @GetMapping(path="course/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody Iterable<Post> getPostByCourse(@PathVariable(value="id") Integer id){
        Iterable<Post> posts = postRepository.findAll();
        List<Post> result = new ArrayList<>();
        for (Post p: posts) {
            if(p.getCourseId() == id){
                result.add(p);
            }
        }
        return result;
    }

    @DeleteMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> deletePost(@PathVariable(value="id") Integer id){
        postRepository.deleteById(id);

        JSONObject entity = new JSONObject();
        entity.put("message","Deleted");
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }

    @PutMapping(path="{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private @ResponseBody ResponseEntity<Object> updatePost(
            @RequestHeader(name="Authorization", required = false) String auth,
            @RequestBody ObjectNode body
    ){

        Post p = new Post();

        if(body.get("title") == null) p.setTitle(body.get("title").asText());
        if(body.get("text") == null) p.setText(body.get("text").asText());
        if(body.get("course_id") == null) p.setCourseId(body.get("course_id").asInt());
        if(body.get("user_id") == null){
            p.setUserId(body.get("user_id").asInt());
        } else {
            Claims x = jwtUtil.getAllClaimsFromToken(auth.split(" ")[1]);
            p.setUserId((Integer) x.get("user_id"));
        }

        JSONObject entity = new JSONObject();
        entity.put("message","Updated");
        return new ResponseEntity<Object>(entity, HttpStatus.OK);
    }
}
