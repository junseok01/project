package com.example.project.rating;

public class RatingDTO {
    private Long id;
    private String username;
    private int score;
    private Long gymInfo;


    public RatingDTO(RatingEntity entity){
        this.id=entity.getId();
        this.username=entity.getUsername();
        this.score=entity.getScore();
        this.gymInfo=entity.getGymInfo();
    }
}
