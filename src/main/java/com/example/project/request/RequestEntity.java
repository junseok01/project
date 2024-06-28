package com.example.project.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //"1"은 존재 "0"은 삭제된 리퀘스트
    @Column
    private String state;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FileEntity> files;


    public RequestEntity(RequestDTO requestDTO){
        this.loginId = requestDTO.getLoginId();
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.state ="1";
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", state='" + state + '\'' +
                ", files=" + files +
                '}';
    }
}