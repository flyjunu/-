package webBoard.korean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webBoard.korean.dto.User;

public interface UserRepository extends JpaRepository<User, Long>
{
    // 특별한 메서드 없이도 save, findAll, findById 등 사용 가능
}
