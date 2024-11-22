package com.example.MapleArticles.service.qna;

import com.example.MapleArticles.domain.post.PostRepository;
import com.example.MapleArticles.domain.qna.QnA;
import com.example.MapleArticles.domain.qna.QnARepository;
import com.example.MapleArticles.dto.post.request.QnACreateRequest;
import com.example.MapleArticles.dto.post.request.QnAUpdateRequest;
import com.example.MapleArticles.dto.post.response.QnAResponse;
import com.example.MapleArticles.repository.post.PostJdbcRepository;
import com.example.MapleArticles.repository.qna.QnAJdbcRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QnAService {

    private final QnARepository qnaRepository;
    private final QnAJdbcRepository qnaJdbcRepository;

    public QnAService(QnARepository qnaRepository, @Qualifier("qnAJdbcRepository") QnAJdbcRepository qnaJdbcRepository) {
        this.qnaRepository = qnaRepository;
        this.qnaJdbcRepository = qnaJdbcRepository;
    }

    @Transactional
    public void saveQnA(QnACreateRequest request) {
        QnA qna = new QnA(request.getTitle(), request.getContent(), request.getUserId(), request.getCategory());
        qnaRepository.save(qna);
    }

    @Transactional(readOnly = true)
    public List<QnAResponse> getQnAs() {
        List<QnA> qnas = qnaRepository.findAll();
        return qnas.stream()
                .map(QnAResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateQnA(QnAUpdateRequest request) {
        QnA qna = qnaRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        qna.updateTitle(request.getTitle());
        qna.updateContent(request.getContent());
        qna.updateCategory(request.getCategory());

        qnaRepository.save(qna);
    }

    @Transactional
    public void deleteQnA(Long id) {
        QnA qna = qnaRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        qnaRepository.delete(qna);
    }
}
