package model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Question {

    private int id;
    private String text;
    private int topic_id;

}
