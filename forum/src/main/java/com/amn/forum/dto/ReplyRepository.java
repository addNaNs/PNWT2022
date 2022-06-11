package com.amn.forum.dto;

import com.amn.forum.models.Reply;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepository extends CrudRepository<Reply, Integer> {
}
