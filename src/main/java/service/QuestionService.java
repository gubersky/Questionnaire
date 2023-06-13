package service;

import model.Question;
import repository.dao.QuestionRepository;
import java.util.List;

public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestion() {
        return questionRepository.getAll();
    }

    public Question getRandomQuestion() {
        List<Question> questions = getAllQuestion();
        int random = (int) (Math.random() * questions.size());
        return questions.get(random);
    }

    public Question getRandomQuestionByTopic(int topicId) {
        List<Question> questions = getAllQuestion().stream()
                .filter(x -> x.getTopic_id() == topicId)
                .toList();
        int random = (int) (Math.random() * questions.size());
        return questions.get(random);
    }

    public Question getQuestionById(int id) {
        return questionRepository.get(id);
    }

    public boolean addQuestion(Question question) {
        return questionRepository.add(question);
    }

    public boolean removeQuestion(int id) {
        return questionRepository.remove(id);
    }

    public int updateQuestion(Question question) {
        return questionRepository.update(question);
    }
}
