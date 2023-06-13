package repository.dao;

import model.Topic;
import java.util.List;

public interface TopicRepository {

    List<Topic> getAll();

    boolean add(Topic topic);

    Topic get(int id);

    boolean remove(int id);

    int update(Topic topic);

}
