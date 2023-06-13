package model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Topic {

    private int id;
    private String name;

}
