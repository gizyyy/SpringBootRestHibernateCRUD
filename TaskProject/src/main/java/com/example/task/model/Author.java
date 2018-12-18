package com.example.task.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "author")
@EntityListeners(AuditingEntityListener.class)
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long author_id;

	@NotBlank
	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	private List<Note> notes = new ArrayList<Note>();

	public Long getId() {
		return author_id;
	}

	public void setId(Long author_id) {
		this.author_id = author_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

}
