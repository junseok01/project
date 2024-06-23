package com.example.project.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "request")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntity {
    @Id
    @GeneratedValue
    private Long _id;
    @Column
    private String loginId;
    @Column
    private String Request;

    public RequestEntity(String loginId, String request) {
        this.loginId = loginId;
        Request = request;
    }
}
