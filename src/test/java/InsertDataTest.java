import com.springtesting.model.Category;
import com.springtesting.repo.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(value = "integrationtest")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InsertDataTest
{


    @Autowired
    private CategoryRepository categoryRepository;

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


}
