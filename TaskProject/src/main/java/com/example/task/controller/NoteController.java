package com.example.task.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.task.model.Author;
import com.example.task.model.Note;
import com.example.task.service.INoteService;

@RestController
@RequestMapping("/api")
public class NoteController {

	@Autowired
	INoteService noteService;

	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return noteService.getAllNotes();
	}

	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return noteService.createNote(note);
	}

	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long noteId) {
		return noteService.getNote(noteId);
	}

	@GetMapping("/notes/{title}/{content}")
	public List<Note> getNoteByTitleAndContent(@PathVariable(value = "title") String title,
			@PathVariable(value = "content") String content) {
		Note n = new Note();
		n.setContent(content);
		n.setTitle(title);
		return noteService.getRelatedNotes(n);
	}

	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {

		return noteService.updateNote(noteId, noteDetails);
	}

	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
		return noteService.deleteNote(noteId);
	}
	// --------author part--------------//

	@GetMapping("/author/{authorName}/notes/")
	public List<Note> getNoteByAuthor(@PathVariable(value = "authorName") String authorName) {

		return noteService.getNotesByAuthor(authorName);
	}

	@GetMapping("/authors")
	public List<Author> getAuthors() {

		return noteService.getAuthors();
	}

}
