package com.amn.forum.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(
        name = "reply"
)
public class Reply {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;

        @JsonIgnoreProperties({"replies"})
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "post_id")
        private Post post;

        private Integer userId;

        private String text;

        public Reply() {
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public Post getPost() {
                return post;
        }

        public void setPost(Post post) {
                this.post = post;
        }

        public Integer getUserId() {
                return userId;
        }

        public void setUserId(Integer userId) {
                this.userId = userId;
        }

        public String getText() {
                return text;
        }

        public void setText(String text) {
                this.text = text;
        }
}
