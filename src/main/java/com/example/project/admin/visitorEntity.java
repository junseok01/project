package com.example.project.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class visitorEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDate visitDate;

    @Column
    private int visitCount;
    public visitorEntity(LocalDate visitDate, int visitCount) {
        this.visitDate = visitDate;
        this.visitCount = visitCount;
    }
}
