package com.reins.bookstore.models;

import com.reins.bookstore.entity.Comment;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    Long id;
    String username;
    String content;
    String reply;
    Integer like;
    Boolean liked;
    Timestamp createdAt;

    public static CommentDTO from(Comment comment, Boolean liked) {
        Comment reply = comment.getReply();
        return new CommentDTO(
                comment.getId(),
                comment.getUser().getNickname(),
                comment.getContent(),
                (reply != null) ? reply.getUser().getNickname() : null,
                comment.getLike(),
                liked,
                comment.getCreatedAt()
        );
    }
}
