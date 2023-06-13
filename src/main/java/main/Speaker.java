package main;

import model.Question;
import model.Topic;
import service.QuestionService;
import service.TopicService;
import java.util.List;
import java.util.Scanner;

public class Speaker {

    private final QuestionService questionService;
    private final TopicService topicService;
    private final Scanner scanner;

    public Speaker(QuestionService questionService, TopicService topicService, Scanner scanner) {
        this.questionService = questionService;
        this.topicService = topicService;
        this.scanner = scanner;
    }

    public void run() {
        Command[] commands = Command.values();
        System.out.println("Select the number of command you want to run:");
        for (Command command : commands) {
            System.out.println(command.command);
        }
        int commandNumber;
        if (scanner.hasNextInt()) {
            commandNumber = scanner.nextInt();
            switch (commandNumber) {
                case 1 -> getRandomQuestionByTopic();
                case 2 -> getRandomQuestion();
                case 3 -> addQuestion();
                case 4 -> removeQuestion();
                case 5 -> getTopic();
                case 6 -> addTopic();
                default -> System.out.println("Wrong command!");
            }
            System.out.println("Would you like to continue?");
            String answer = scanner.next().toLowerCase();
            if (answer.equals("yes")) run();
            else System.out.println("See you soon!");
        }else System.out.println("Wrong Data!");
    }

    private void getRandomQuestion() {
        System.out.println("Question: " + questionService.getRandomQuestion().getText() + "\n");
    }

    private void getRandomQuestionByTopic() {
        System.out.println("Select Topic:");
        List<Topic> topics = getTopic();
        int topicId = 0;
        String topic = scanner.next();
        for (Topic findTopic : topics) {
            if (topic.equals(findTopic.getName())) topicId = findTopic.getId();
        }
        if (topicId != 0) {
            System.out.println("Question: " + questionService.getRandomQuestionByTopic(topicId).getText() + "\n");
        } else System.out.println("Wrong Topic!");
    }

    private void addQuestion() {
        System.out.println("Select Topic:");
        List<Topic> topics = getTopic();
        int topicId = 0;
        String topic = scanner.next();
        for (Topic findTopic : topics) {
            if (topic.equals(findTopic.getName())) topicId = findTopic.getId();
        }
        System.out.print("Enter the question you want to add: ");
        String question = scanner.next();
        questionService.addQuestion(Question.builder().text(question).topic_id(topicId).build());
        System.out.println("Question add successfully!" + "\n");
    }

    private void removeQuestion() {
        System.out.print("Enter the question id you want to remove: ");
        int questionId;
        if (scanner.hasNextInt()) {
            questionId = scanner.nextInt();
            questionService.removeQuestion(questionId);
            System.out.println("Question remove successfully!" + "\n");
        } else {
            System.out.println("Wrong Data!");
            scanner.next();
        }
    }

    private List<Topic> getTopic() {
        List<Topic> topics = topicService.getAllTopic();
        for (Topic topic : topics) {
            System.out.println(topic.getName());
        }
        return topics;
    }

    public void addTopic() {
        System.out.print("Enter the topic you want to add: ");
        String topic = scanner.next();
        topicService.addTopic(Topic.builder().name(topic).build());
        System.out.println("Topic add successfully!" + "\n");
    }
}
