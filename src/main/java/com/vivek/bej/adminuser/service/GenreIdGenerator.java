package com.vivek.bej.adminuser.service;

import com.vivek.bej.adminuser.domain.GenreSequence;
import com.vivek.bej.adminuser.repository.GenreSequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreIdGenerator {

    private final GenreSequenceRepository genreSequenceRepository;

    @Autowired
    public GenreIdGenerator(GenreSequenceRepository genreSequenceRepository) {
        this.genreSequenceRepository = genreSequenceRepository;
    }


    public long getNextGenreId() {
        // Get the current bookId from the sequence collection
        GenreSequence sequence = genreSequenceRepository.findById("genre").orElse(new GenreSequence("genre", 2000L));
        long currentBookId = sequence.getGenreId();
        // Increment the bookId and save it back to the sequence collection
        sequence.setGenreId(currentBookId + 1);
        genreSequenceRepository.save(sequence);
        return currentBookId + 1;
    }
}