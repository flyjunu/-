package webBoard.korean.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import webBoard.korean.dto.BoardDTO;

import java.sql.Timestamp;

// @Entity 이 클래스가 JPA를 사용함을 선언
@Entity
// @Table 이 클래스가 테이블임을 선언하고 테이블 명을
@Table(name = "board_sts_jpa")
@Data
public class BoardEntity {
    // @Id 는 이 컬럼이 PK임을 선언하기 위한 에노테이션이다.
    // @GeneratedValue는 PK컬럼에 사용하며,PK를 어떻게 생성할지 선택한다.
    // Table= 키 생성 전용 테이블을 만든다.
    // SEQUENCE = 시퀸스 테이블을 만든다.
    // IDENTITY = PK생성을 DB에 위임한다.
    // AUTO = JPA가 자동으로 생성전략을 결정한다.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    @Column(length = 30, nullable = false)
    private String boardTitle;

    @Column(length = 1000)
    private String boardContents;

    private String boardPass;

    //조회수
    private int boardHits = 0;

    @CreationTimestamp
    private Timestamp createdAt;

    // DTO -> Entity 변환용 static 메서드
    public static BoardEntity toEntity(BoardDTO dto) {
        BoardEntity entity = new BoardEntity();
        entity.setId(dto.getId());
        entity.setBoardWriter(dto.getBoardWriter());
        entity.setBoardPass(dto.getBoardPass());
        entity.setBoardTitle(dto.getBoardTitle());
        entity.setBoardContents(dto.getBoardContents());
        entity.setBoardHits(dto.getBoardHits());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}
