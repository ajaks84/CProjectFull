package com.deshand.adc.web.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.deshand.adc.dao.db.auth.UserData;
import com.deshand.adc.services.impl.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Inject
	private AuthService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserData> getById(@RequestBody CredModel model) {

		System.out.println(model.getUserName());
		System.out.println(model.getPassword());
		UserData ud = service.login(model.getUserName(), model.getPassword());
		System.out.println(ud);

		return new ResponseEntity<UserData>(HttpStatus.OK);
	}

	// private Book model2entity( bookModel) {
	// Book book = new Book();
	// book.setAuthorId(bookModel.getAuthorId());
	// book.setTitle(bookModel.getTitle());
	// book.setGenre(BookGenre.valueOf(bookModel.getGenre()));
	// return book;
	// }

}
