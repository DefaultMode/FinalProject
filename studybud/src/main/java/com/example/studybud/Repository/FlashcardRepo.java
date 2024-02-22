package com.example.studybud.Repository;

import com.example.studybud.model.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlashcardRepo extends JpaRepository<Flashcard, Long> {

}
