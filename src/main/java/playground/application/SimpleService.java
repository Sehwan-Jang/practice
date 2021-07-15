package playground.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import playground.dao.SimpleDao;
import playground.domain.Player;
import playground.dto.PlayerRequest;
import playground.dto.PlayerResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleService {

    private final SimpleDao simpleDao;

    public SimpleService(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    @Transactional
    public PlayerResponse addPlayer(PlayerRequest playerRequest) {
//        Player player = new Player(playerRequest.getName(), playerRequest.getBackNumber());
        Player player = playerRequest.toEntity();
        Long id = simpleDao.save(player);
        return new PlayerResponse(id, player.getName(), player.getBackNumber());
    }

    public PlayerResponse getPlayer(Long id) {
        return PlayerResponse.of(this.simpleDao.findById(id));
    }

    public List<PlayerResponse> getAllPlayers() {
        List<Player> players = this.simpleDao.findAll();
        return players.stream()
                .map(PlayerResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id) {
        this.simpleDao.deleteById(id);
    }
}
