package com.example.task.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.task.model.Author;
import com.example.task.model.Note;

public interface INoteService {
	public List<Note> getAllNotes();

	public Note getNote(Long id);

	public ResponseEntity<?> deleteNote(Long id);

	public Note updateNote(Long id, Note noteNew);

	public Note createNote(Note noteNew);
	
	public List<Note> getRelatedNotes(Note note);

	public List<Note> getNotesByAuthor(String authorName) ;
	
	public List<Author> getAuthors();

}
