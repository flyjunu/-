package webBoard.korean.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import webBoard.korean.dto.BoardDTO;
import webBoard.korean.service.BoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor //모든 인수 있는 생성자 생성
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm(Model model) {
        model.addAttribute("board", new BoardDTO());
        return "save";
    }

    //모델 어트리뷰트 에노테이션: 파라미터의 오브젝트를 선언하지 않아도 자동 생성한다.
    // 단 getter setter 함수가 생성하려는 객체에 있어야만 가능하다.
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        boardService.save(boardDTO);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "list";
    }

    //@PathVariable은 URL경로의 일부 값을 가져오는 에너테이션 여기서는 {id}값을 받아서
    // Long id 변수에 집어넣는다.
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        // boardService에서 ID를 가져온다.
        BoardDTO board = boardService.findById(id);
        // 모델에 board 객체를 추가하여, detail.html로 보낸다.
        // 그러면 detail.html에서 이 객체를 사용할 수 있다.
        model.addAttribute("board", board);
        return "detail";
    }

    //수정 화면 보여주기.
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        BoardDTO board = boardService.findById(id);
        model.addAttribute("board", board);
        return "edit";
    }

    //수정 요청 보내기
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute BoardDTO boardDTO) {
        boardService.update(id, boardDTO);
        return "redirect:/detail/" + id;
    }

    // (1) 비밀번호 입력 폼
    @GetMapping("/edit/check/{id}")
    public String checkEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "edit-check";
    }

    @PostMapping("/edit/check/{id}")
    public String checkEdit(@PathVariable Long id,
                            @RequestParam String boardPass,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        BoardDTO board = boardService.findById(id);
        if (!board.getBoardPass().equals(boardPass)) {
            // 비밀번호 불일치 시 에러 메시지와 함께 비번 확인 폼 재출력
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/edit/check/" + id;
        }
        // 비밀번호 일치 → 수정 폼 이동
        model.addAttribute("board", board);
        return "edit";
    }

    // (1) 삭제 비번 입력 폼
    @GetMapping("/delete/check/{id}")
    public String checkDeleteForm(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.findById(id));
        return "delete-check";
    }

    // (2) 비번 확인 후 실제 삭제 처리
    @PostMapping("/delete/check/{id}")
    public String checkDelete(@PathVariable Long id,
                              @RequestParam String boardPass,
                              RedirectAttributes redirectAttributes) {
        BoardDTO board = boardService.findById(id);
        if (!board.getBoardPass().equals(boardPass)) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/delete/check/" + id;
        }
        // 비밀번호 일치 → 삭제
        boardService.delete(id);
        return "redirect:/list";
    }

}

