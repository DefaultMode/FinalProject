package com.example.studybud.Controller;

import com.example.studybud.model.Flashcard;
import com.example.studybud.Service.FlashcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

    private final FlashcardService flashcardService;

    @Autowired
    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @PostMapping
    public ResponseEntity<Flashcard> createFlashcard(@RequestBody Flashcard flashcard) {
        Flashcard createdFlashcard = flashcardService.saveOrUpdate(flashcard);
        return new ResponseEntity<>(createdFlashcard, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Flashcard>> getAllFlashcards() {
        List<Flashcard> flashcards = flashcardService.findAll();
        return new ResponseEntity<>(flashcards, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flashcard> getFlashcardById(@PathVariable Long id) {
        return flashcardService.findById(id)
                .map(flashcard -> new ResponseEntity<>(flashcard, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flashcard> updateFlashcard(@PathVariable Long id, @RequestBody Flashcard flashcardDetails) {
        Flashcard updatedFlashcard = flashcardService.saveOrUpdate(flashcardDetails);
        return new ResponseEntity<>(updatedFlashcard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFlashcard(@PathVariable Long id) {
        boolean isDeleted = flashcardService.delete(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
