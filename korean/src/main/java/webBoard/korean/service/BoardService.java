package webBoard.korean.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import webBoard.korean.dto.BoardDTO;
import webBoard.korean.entity.BoardEntity;
import webBoard.korean.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;

@Service//자바 내부 로직을 처리하기 위한 것
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @GetMapping("/save")
    public String save() {return "save";}

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        BoardEntity entity = BoardEntity.toEntity(boardDTO);
        boardRepository.save(entity);
        return "index";
    }

    // 전체 글 목록 반환 (Entity → DTO 변환)
    public List<BoardDTO> findAll() {
        List<BoardEntity> entityList = boardRepository.findAll();
        List<BoardDTO> dtoList = new ArrayList<>();
        for (BoardEntity entity : entityList) {
            dtoList.add(new BoardDTO(entity));
        }
        return dtoList;
    }

    //글 1건 조회하기.
    public BoardDTO findById(Long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new BoardDTO(entity);
    }

    // 글 수정 기능
    public void update(Long id, BoardDTO boardDTO) {
        // 1. 기존 게시글을 DB에서 가져옴
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));

        // 2. 필드값 변경 (원하는 필드만)
        entity.setBoardTitle(boardDTO.getBoardTitle());
        entity.setBoardContents(boardDTO.getBoardContents());
        // 필요하면 작성자, 비밀번호 등도 수정 가능
        // entity.setBoardWriter(boardDTO.getBoardWriter());

        // 3. 저장
        boardRepository.save(entity);
    }

    //글 삭제 기능
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
