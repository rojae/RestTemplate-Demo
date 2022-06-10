package kr.rojae.rest.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Person {
    private int userId;
    private int id;
    private String title;
    private String body;
}
