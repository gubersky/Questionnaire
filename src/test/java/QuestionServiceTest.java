import model.Question;
import model.Topic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.QuestionService;

public class QuestionServiceTest {

    private QuestionRepositoryMock questionRepositoryMock;
    private QuestionService questionService;
    private final Question testQuestion = Question.builder().id(0).text("Test").topic_id(0).build();

    @Before
    public void init() {
        questionRepositoryMock = new QuestionRepositoryMock();
        questionService = new QuestionService(questionRepositoryMock);
    }

    @Test
    public void getAllQuestionTest() {
        Assert.assertTrue(questionService.getAllQuestion().size() > 0);
    }

    @Test
    public void getQuestionByTopicTest() {
        Topic testTopic = Topic.builder().id(1).name("OOP").build();
        Question result = questionService.getRandomQuestionByTopic(testTopic.getId());
        Assert.assertEquals(result.getTopic_id(), testTopic.getId());
    }

    @Test
    public void getRandomQuestionTest() {
        Question result = questionService.getRandomQuestion();
        Assert.assertTrue(questionService.getAllQuestion().contains(result));
    }

    @Test
    public void addQuestionTest() {
        Assert.assertTrue(questionService.addQuestion(testQuestion));
    }

    @Test
    public void removeQuestionTest() {
        Assert.assertTrue(questionService.removeQuestion(testQuestion.getId()));
    }

    @Test
    public void updateQuestionTest() {
        int result = questionService.updateQuestion(testQuestion);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void getQuestionByIdTest() {
        Question result = questionService.getQuestionById(testQuestion.getId());
        Assert.assertEquals(result.getId(), testQuestion.getId());
    }
}
