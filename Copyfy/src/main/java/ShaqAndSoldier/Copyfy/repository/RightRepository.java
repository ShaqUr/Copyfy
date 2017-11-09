package ShaqAndSoldier.Copyfy.repository;

/**
 *
 * @author Aram
 */
import ShaqAndSoldier.Copyfy.model.Right;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightRepository extends CrudRepository<Right, String> {
    Optional<Right> findByUID(String id);

    Optional<Right> findBySID(String id);
}
