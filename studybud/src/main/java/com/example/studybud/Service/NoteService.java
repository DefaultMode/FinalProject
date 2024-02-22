package com.example.studybud.Service;

import com.example.studybud.model.Note;
import com.example.studybud.Repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepo noteRepo;

    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    // Save or update a note
    public Note save(Note note) {
        return noteRepo.save(note);
    }

    // Get all notes
    public List<Note> findAll() {
        return noteRepo.findAll();
    }

    // Get a note by ID
    public Optional<Note> findById(Long id) {
        return noteRepo.findById(id);
    }

    // Delete a note
    public Note update(Long id, Note noteDetails) {
        return noteRepo.findById(id).map(note -> {
            note.setTitle(noteDetails.getTitle());
            note.setContent(noteDetails.getContent());
            return noteRepo.save(note);
        }).orElse(null); // Consider throwing an exception or a different handling strategy
    }

    // Delete a note by id, with a boolean return type for success or failure
    public boolean delete(Long id) {
        if (noteRepo.existsById(id)) {
            noteRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
