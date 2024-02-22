package com.example.studybud.Service;

import com.example.studybud.model.Flashcard;
import com.example.studybud.Repository.FlashcardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardService {

    private final FlashcardRepo flashcardRepo;

    @Autowired
    public FlashcardService(FlashcardRepo flashcardRepo) {
        this.flashcardRepo = flashcardRepo;
    }

    public Flashcard saveOrUpdate(Flashcard flashcard) {
        return flashcardRepo.save(flashcard);
    }

    public List<Flashcard> findAll() {
        return flashcardRepo.findAll();
    }

    public Optional<Flashcard> findById(Long id) {
        return flashcardRepo.findById(id);
    }

    public boolean delete(Long id) {
        if (flashcardRepo.existsById(id)) {
            flashcardRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
