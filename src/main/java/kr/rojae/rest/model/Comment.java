package kr.rojae.rest.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Comment{
    public int postId;
    public int id;
    public String name;
    public String email;
    public String body;
}
