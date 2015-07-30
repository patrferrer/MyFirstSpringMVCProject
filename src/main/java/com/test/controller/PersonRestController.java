package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.test.model.Person;
import com.test.service.PersonService;

@RestController
public class PersonRestController {

    private PersonService personService;

	public PersonService getPersonService() {
		return personService;
	}

    @Autowired(required=true)
    @Qualifier(value="personService")
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> personForm(@PathVariable int id) {
		Person person = personService.getPersonById(id);
		System.out.println(person);
		return new ResponseEntity<>(person.toString(), null, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/api/person", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> personSave(@RequestBody Person person) {
		personService.addPerson(person);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(
				ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
}