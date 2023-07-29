package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.Sequence;
import com.vivek.bej.adminuser.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookIdGeneratorService {
    private final SequenceRepository sequenceRepository;

    @Autowired
    public BookIdGeneratorService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    public long getNextBookId() {
        // Get the current bookId from the sequence collection
        Sequence sequence = sequenceRepository.findById("book").orElse(new Sequence("book", 1000L));
        long currentBookId = sequence.getBookId();

        // Increment the bookId and save it back to the sequence collection
        sequence.setBookId(currentBookId + 1);
        sequenceRepository.save(sequence);

        return currentBookId + 1;
    }
}
