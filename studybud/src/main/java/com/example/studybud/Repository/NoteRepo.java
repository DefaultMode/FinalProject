package com.example.studybud.Repository;

import com.example.studybud.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {

}
