import model.Topic;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.TopicService;

public class TopicServiceTest {

    private TopicRepositoryMock topicRepositoryMock;
    private TopicService topicService;
    private final Topic testTopic = Topic.builder().id(0).name("Test").build();

    @Before
    public void init() {
        topicRepositoryMock = new TopicRepositoryMock();
        topicService = new TopicService(topicRepositoryMock);
    }

    @Test
    public void getAllTopicTest() {
        Assert.assertTrue(topicService.getAllTopic().size() > 0);
    }

    @Test
    public void getTopicByIdTest() {
        Topic result = topicService.getTopicById(testTopic.getId());
        Assert.assertEquals(result.getId(), testTopic.getId());
    }

    @Test
    public void addTopicTest() {
        Assert.assertTrue(topicService.addTopic(testTopic));
    }

    @Test
    public void removeTopicTest() {
        Assert.assertTrue(topicService.removeTopic(testTopic.getId()));
    }

    @Test
    public void updateTopicTest() {
        int result = topicService.updateTopic(testTopic);
        Assert.assertEquals(result, 1);
    }
}
