package playground.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import playground.domain.Player;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@ActiveProfiles("test")
class SimpleDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;

    private SimpleDao simpleDao;

    @BeforeEach
    void setUp() {
        simpleDao = new SimpleDao(jdbcTemplate, dataSource);
    }

    @Test
    void save() {
        //given
        Player player = new Player("aaron", 8);
        Player player2 = new Player("aaron", 8);
        Player player3 = new Player("aaron", 8);

        //when
        System.out.println(simpleDao.save(player));
        System.out.println(simpleDao.save(player2));
        System.out.println(simpleDao.save(player3));

        //then
        assertThat(simpleDao.findAll()).hasSize(3);
    }
}