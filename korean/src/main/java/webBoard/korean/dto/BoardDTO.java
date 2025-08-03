package webBoard.korean.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import webBoard.korean.entity.BoardEntity;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 (옵션)
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private Timestamp createdAt;

    // Entity -> DTO 변환용 생성자
    public BoardDTO(BoardEntity entity) {
        this.id = entity.getId();
        this.boardWriter = entity.getBoardWriter();
        this.boardPass = entity.getBoardPass();
        this.boardTitle = entity.getBoardTitle();
        this.boardContents = entity.getBoardContents();
        this.boardHits = entity.getBoardHits();
        this.createdAt = entity.getCreatedAt();
    }
}
