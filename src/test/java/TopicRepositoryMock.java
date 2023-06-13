import model.Topic;
import repository.dao.TopicRepository;
import java.util.List;

public class TopicRepositoryMock implements TopicRepository {

    private final Topic testTopic = Topic.builder().id(0).name("Test").build();

    @Override
    public List<Topic> getAll() {
        return List.of(
                Topic.builder().id(0).name("First").build(),
                Topic.builder().id(1).name("Second").build(),
                Topic.builder().id(2).name("Third").build(),
                Topic.builder().id(3).name("Four").build()
        );
    }

    @Override
    public boolean add(Topic topic) {
        return topic.equals(testTopic);
    }

    @Override
    public Topic get(int id) {
        return getAll().get(id);
    }

    @Override
    public boolean remove(int id) {
        return id == testTopic.getId();
    }

    @Override
    public int update(Topic topic) {
        if (topic.equals(testTopic)) return 1;
        return 0;
    }
}
