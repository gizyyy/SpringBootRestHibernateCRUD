package com.example.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task.model.Author;
import com.example.task.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
	List<Note> findByTitle(String title);

	@Query("SELECT a FROM Note a WHERE a.title=:title and a.content=:content")
	List<Note> fetchNote(@Param("title") String title, @Param("content") String content);


}
