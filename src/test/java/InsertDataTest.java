import com.springtesting.model.Category;
import com.springtesting.model.User;
import com.springtesting.model.UserProfile;
import com.springtesting.repo.CategoryRepository;
import com.springtesting.repo.UserProfileRepository;
import com.springtesting.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InsertDataTest
{
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Before
    public void setUp()
    {

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
    public void insertUser()
    {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
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
    public void insertUserAndUserProfile()
    {
        User user = new User();
        user.setUsername("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        user.setActive(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user = userRepository.saveAndFlush(user);

        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName("Admin");
        userProfile.setLastName("Admin");
        userProfile.setEmail("admin@hm.com");
        userProfile.setUser(user);
        userProfileRepository.saveAndFlush(userProfile);
    }

}
