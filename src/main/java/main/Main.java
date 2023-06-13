package main;

import repository.QuestionRepositoryPostgres;
import repository.TopicRepositoryPostgres;
import service.QuestionService;
import service.TopicService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuestionRepositoryPostgres questionRepositoryPostgres = new QuestionRepositoryPostgres();
        TopicRepositoryPostgres topicRepositoryPostgres = new TopicRepositoryPostgres();
        QuestionService questionService = new QuestionService(questionRepositoryPostgres);
        TopicService topicService = new TopicService(topicRepositoryPostgres);
        Speaker speaker = new Speaker(questionService, topicService, scanner);
        System.out.println("Welcome to the Java Question Speaker!");
        speaker.run();
        scanner.close();
    }
}
