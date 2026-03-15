package springMvcWeb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

// Ensure this path matches your configuration file (XML or Java class)
@SpringJUnitConfig(locations = "classpath:spring/testApplicationContext.xml") 
@ActiveProfiles("test") 
@Transactional 
public class AppUserIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @BeforeEach
    public void setup() {
    	jdbcTemplate.update("DELETE FROM AppUsers");
    }

    @Test
    void testCrudOperations() {
        // 1. CREATE (Using your new columns: name, email)
        String insertSql = "INSERT INTO AppUsers (name, email) VALUES (?, ?)";
        jdbcTemplate.update(insertSql, "John Doe", "john@example.com");

        // 2. READ
        String selectSql = "SELECT * FROM AppUsers WHERE name = ?";
        // H2 returns column names in UPPERCASE in the resulting Map
        Map<String, Object> user = jdbcTemplate.queryForMap(selectSql, "John Doe");
        
        Assertions.assertEquals("John Doe", user.get("NAME"));
        Assertions.assertEquals("john@example.com", user.get("EMAIL"));

        // 3. UPDATE
        String updateSql = "UPDATE AppUsers SET email = ? WHERE name = ?";
        jdbcTemplate.update(updateSql, "new_email@example.com", "John Doe");
        
        String updatedEmail = jdbcTemplate.queryForObject(
            "SELECT email FROM AppUsers WHERE name = ?", String.class, "John Doe");
        Assertions.assertEquals("new_email@example.com", updatedEmail);

        // 4. DELETE
        String deleteSql = "DELETE FROM AppUsers WHERE name = ?";
        jdbcTemplate.update(deleteSql, "John Doe");

        List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT * FROM AppUsers");
        Assertions.assertTrue(users.isEmpty(), "Table should be empty after delete");
    }
}
