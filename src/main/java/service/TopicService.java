package service;

import model.Topic;
import repository.dao.TopicRepository;
import java.util.List;

public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public List<Topic> getAllTopic(){
        return topicRepository.getAll();
    }

    public Topic getTopicById(int id){
        return topicRepository.get(id);
    }

    public boolean addTopic(Topic topic){
        return topicRepository.add(topic);
    }

    public boolean removeTopic(int id){
       return topicRepository.remove(id);
    }

    public int updateTopic(Topic topic){
        return topicRepository.update(topic);
    }
}
