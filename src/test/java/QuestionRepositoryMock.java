import model.Question;
import repository.dao.QuestionRepository;
import java.util.List;

public class QuestionRepositoryMock implements QuestionRepository {

    private final Question testQuestion = Question.builder().id(0).text("Test").topic_id(0).build();

    @Override
    public List<Question> getAll() {
        return List.of(
                Question.builder().id(0).text("First question").topic_id(0).build(),
                Question.builder().id(1).text("Second question").topic_id(1).build(),
                Question.builder().id(2).text("Third question").topic_id(2).build(),
                Question.builder().id(3).text("Four question").topic_id(3).build()
        );
    }

    @Override
    public boolean add(Question question) {
        return question.equals(testQuestion);
    }

    @Override
    public Question get(int id) {
        return getAll().get(id);
    }

    @Override
    public boolean remove(int id) {
        return id == testQuestion.getId();
    }

    @Override
    public int update(Question question) {
        if (question.equals(testQuestion)) return 1;
        return 0;
    }
}
