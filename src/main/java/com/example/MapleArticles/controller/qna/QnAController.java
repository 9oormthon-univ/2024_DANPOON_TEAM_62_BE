package com.example.MapleArticles.controller.qna;

import com.example.MapleArticles.dto.post.request.QnACreateRequest;
import com.example.MapleArticles.dto.post.request.QnAUpdateRequest;
import com.example.MapleArticles.dto.post.response.QnAResponse;
import com.example.MapleArticles.service.post.PostService;
import com.example.MapleArticles.service.qna.QnAService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QnAController {
    private final QnAService qnaService;

    public QnAController(QnAService qnaService) {
        this.qnaService = qnaService;
    }

    @PostMapping("/qna")
    public void saveQnA(@RequestBody QnACreateRequest request) {
        qnaService.saveQnA(request);
    }

    @GetMapping("/qna")
    public List<QnAResponse> getQnAs() {
        return qnaService.getQnAs();
    }

    @PutMapping("/qna")
    public void updateQnA(@RequestBody QnAUpdateRequest request) {
        qnaService.updateQnA(request);
    }

    @DeleteMapping("/qna")
    public void deleteQnA(@RequestParam Long id) {
        qnaService.deleteQnA(id);
    }
}
