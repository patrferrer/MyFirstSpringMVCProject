package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	@ResponseBody
	public ResponseEntity<Person> getPersonById(@PathVariable int id) {
		Person person = personService.getPersonById(id);
		if(person==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(person, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/person", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Person>> getAll() {
		List<Person> persons = personService.listPersons();
		if(persons==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(persons, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/api/person", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Person>> save(@RequestBody Person person) {
		personService.addPerson(person);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/person", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Person>> update(@RequestBody Person person) {
		personService.updatePerson(person);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/api/person/{id}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Person> deletePersonById(@PathVariable int id) {
		personService.removePerson(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}