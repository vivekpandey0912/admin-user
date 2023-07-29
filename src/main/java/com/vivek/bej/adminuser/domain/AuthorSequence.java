package com.vivek.bej.adminuser.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "AuthorSequence")
public class AuthorSequence {
        @Id
        private String id;
        private long authorId;

    public String getId() {
        return id;
    }

    public AuthorSequence(String id, long authorId) {
        this.id = id;
        this.authorId = authorId;
    }

    public AuthorSequence() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }
}
