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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController
{
    private final UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    //Users Controller Methods

    @PostMapping(value = "/users/create")
    public User createNewUser(@RequestBody User user)
    {
        return userRepository.saveAndFlush(user);
    }

    @GetMapping(value = "/users/list")
    public Page<User> findAll(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer limit)
    {
        if (pageNumber == null)
            pageNumber = 0;
        if (limit == null)
            limit = 10;
        return userRepository.findAll(PageRequest.of(pageNumber, limit, Sort.by(Sort.Direction.ASC, "id")));
    }

    @GetMapping(value = "/user/{id}")
    public Optional<User> findById(@PathVariable Long id)
    {
        return userRepository.findById(id);
    }

    @GetMapping(value = "/user/{username}")
    public User findByUsername(@PathVariable String username)
    {
        return userRepository.findByUsername(username);
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
}
