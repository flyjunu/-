package webBoard.korean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webBoard.korean.dto.User;
import webBoard.korean.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
 //특별한 함수 없이 save 함수등 사용가능

}
