package com.example.task;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.task.model.Note;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExampleTaskApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleTaskApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;


	private String getRootUrl() {
		return "http://localhost:8080/api";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllNotes() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/notes", HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testNoteById() {
		Note note = restTemplate.getForObject(getRootUrl() + "/notes/1", Note.class);
		System.out.println(note.getTitle());
		assertNotNull(note);
	}

	@Test
	public void testCreateEmployee() {
		Note note = new Note();
		note.setTitle("title2");
		note.setContent("content2");

		ResponseEntity<Note> postResponse = restTemplate.postForEntity(getRootUrl() + "/notes", note, Note.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdateEmployee() {
		int id = 1;
		Note note = restTemplate.getForObject(getRootUrl() + "/notes/" + id, Note.class);
		note.setTitle("update1");
		note.setContent("update2");
		restTemplate.put(getRootUrl() + "/notes/" + id, note);
		Note updatedNote = restTemplate.getForObject(getRootUrl() + "/notes/" + id, Note.class);
		assertNotNull(updatedNote);
	}

//	@Test
//	public void testDeleteEmployee() {
//		int id = 2;
//		Note employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
//		assertNotNull(employee);
//		restTemplate.delete(getRootUrl() + "/employees/" + id);
//		try {
//			employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
//		} catch (final HttpClientErrorException e) {
//			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
//		}
//	}
}