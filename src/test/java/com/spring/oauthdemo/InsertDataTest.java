package com.spring.oauthdemo;

import com.spring.oauthdemo.model.Category;
import com.spring.oauthdemo.model.User;
import com.spring.oauthdemo.model.UserProfile;
import com.spring.oauthdemo.repo.CategoryRepository;
import com.spring.oauthdemo.repo.UserProfileRepository;
import com.spring.oauthdemo.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "integrationtest")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InsertDataTest
{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;


    //@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Before
    public void setUp()
    {

    }

    @Test
    public void insertUserAndUserProfile()
    {
        User user = new User();
        user.setUsername("admin");
        user.setId(1L);
        //user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setPassword("$2a$12$pqcdXB.Xboa7pGNba51YHuWQVhlZM8TVguRUCL2ss8GOwhEqiqwOu");
        user.setActive(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user = userRepository.saveAndFlush(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setId(1L);
        userProfile.setFirstName("Admin");
        userProfile.setLastName("Admin");
        userProfile.setEmail("admin@hm.com");
        userProfile.setUser(user);
        userProfileRepository.saveAndFlush(userProfile);
    }


    @Test
    public void insertUser()
    {
        User user = new User();
        user.setUsername("admin");
        //user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setPassword("$2a$12$pqcdXB.Xboa7pGNba51YHuWQVhlZM8TVguRUCL2ss8GOwhEqiqwOu");
        user.setActive(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        userRepository.saveAndFlush(user);
    }

    @Test
    public void insertUserProfile()
    {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("Admin");
        userProfile.setLastName("Admin");
        userProfile.setEmail("admin@hm.com");
        userProfile.setUser(userRepository.findAll().get(0));
        userProfileRepository.saveAndFlush(userProfile);
    }



    @Test
    public void insertCategory()
    {
        Category category = new Category();
        category.setId(1001L);
        category.setName("Books");
        categoryRepository.saveAndFlush(category);
    }

    @Test
    public void getCategories()
    {
        List<Category> categoryList=categoryRepository.findAll();
        for(Category category: categoryList)
            System.out.println(category.toString());
    }

    @Test
    public void convertLongTime()
    {
        long longValue=1547439181186L;
        System.out.println(LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), ZoneId.systemDefault()));
    }

}
