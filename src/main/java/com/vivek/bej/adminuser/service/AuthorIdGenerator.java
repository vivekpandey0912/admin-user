package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.AuthorSequence;
import com.vivek.bej.adminuser.domain.Sequence;
import com.vivek.bej.adminuser.repository.AuthorSequenceRepository;
import com.vivek.bej.adminuser.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorIdGenerator {

    private final AuthorSequenceRepository authorSequenceRepository;

    @Autowired
    public AuthorIdGenerator(AuthorSequenceRepository authorSequenceRepository) {
        this.authorSequenceRepository = authorSequenceRepository;
    }
    public long getNextAuthorId() {
        // Get the current bookId from the sequence collection
        AuthorSequence sequence = authorSequenceRepository.findById("author").orElse(new AuthorSequence("author", 100L));
        long currentBookId = sequence.getAuthorId();

        // Increment the bookId and save it back to the sequence collection
        sequence.setAuthorId(currentBookId + 1);
        authorSequenceRepository.save(sequence);

        return currentBookId + 1;
    }
}
