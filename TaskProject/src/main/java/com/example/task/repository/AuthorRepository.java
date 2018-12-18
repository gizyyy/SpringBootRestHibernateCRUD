package com.example.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task.model.Author;
import com.example.task.model.Note;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
