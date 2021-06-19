package playground.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import playground.domain.Player;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class SimpleDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    private final RowMapper<Player> playerRowMapper = (rs, rowNum) ->
            new Player(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("back_number"));

    public SimpleDao(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PLAYERS")
                .usingGeneratedKeyColumns("id");
    }

    public Long save(Player player) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(player);
        return this.simpleJdbcInsert.executeAndReturnKey(parameterSource).longValue();
    }

    public Player findById(Long id) {
        String sql = "select id, name, back_number from PLAYERS where id = ?";
        return jdbcTemplate.queryForObject(sql, playerRowMapper, id);
    }

    public List<Player> findAll() {
        String sql = "select id, name, back_number from PLAYERS";
        return jdbcTemplate.query(sql, playerRowMapper);
    }

    public void deleteById(Long id) {
        String sql = "delete from PLAYERS where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
