package com.example.task.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.ForeignKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.task.exception.ResourceNotFoundException;
import com.example.task.model.Author;
import com.example.task.model.Note;
import com.example.task.repository.AuthorRepository;
import com.example.task.repository.NoteRepository;

@Service
public class NoteService implements INoteService {

	@Autowired
	NoteRepository noteRepository;
	@Autowired
	AuthorRepository authorRepository;

	public List<Note> getAllNotes() {
		return noteRepository.findAll();
	}

	public Note getNote(Long id) {
		return noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	}

	public ResponseEntity<?> deleteNote(Long id) {
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

		noteRepository.delete(note);

		return ResponseEntity.ok().build();
	}

	public Note updateNote(Long id, Note noteNew) {
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));

		note.setTitle(noteNew.getTitle());
		note.setContent(noteNew.getContent());

		Note updatedNote = noteRepository.save(note);
		return updatedNote;
	}

	public Note createNote(Note noteNew) {
		return noteRepository.save(noteNew);
	}

	public List<Note> getRelatedNotes(Note note) {
		return noteRepository.fetchNote(note.getTitle(), note.getContent());

	}

	// ---------------------------------//
	public List<Note> getNotesByAuthor(String authorName) {

		List<Note> findAll = noteRepository.findAll();
		List<Note> notes = new ArrayList<Note>();
		for (Note note : findAll) {
			if (note.getAuthor().getName().equals(authorName)) {

				notes.add(note);

			}

		}

		return notes;

	}

	public List<Author> getAuthors() {

		return authorRepository.findAll();

	}

}