package com.spring.oauthdemo.web;

import com.spring.oauthdemo.dto.UserDto;
import com.spring.oauthdemo.model.User;
import com.spring.oauthdemo.repo.UserRepository;
import com.spring.oauthdemo.security.MyUserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController
{
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public UserController(UserRepository userRepository, ModelMapper modelMapper)
	{
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@PostMapping(value = "/users/create")
	public User createNewUser(@RequestBody User user)
	{
		return userRepository.saveAndFlush(user);
	}

	@GetMapping(value = "/users/list")
	public Page<User> findAll(@RequestParam(required = false, defaultValue = "0") Integer pageNumber, @RequestParam(required = false, defaultValue = "10") Integer limit)
	{
		return userRepository.findAll(PageRequest.of(pageNumber, limit, Sort.by(Sort.Direction.ASC, "id")));
	}

	@GetMapping(value = "/user/{id}")
	public Optional<User> findById(@PathVariable Long id)
	{
		return userRepository.findById(id);
	}

	@GetMapping(value = "/user/oauth2")
	public UserDto getUserInfoUsingOAuth2Token()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDto userDto = new UserDto();
		if (!(authentication instanceof AnonymousAuthenticationToken))
		{
			MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
			User user = findByUsername(myUserDetails.getUsername());
			modelMapper.map(user, userDto);
		}
		return userDto;
	}

	@GetMapping(value = "/user/{username}")
	public User findByUsername(@PathVariable String username)
	{
		return userRepository.findByUsername(username);
	}
}
