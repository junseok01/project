package com.example.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tube")
public class tube {
    @Id
    private Long exercisenum;
    private String exercisename;
    private String category;
    private String tubelink;
    private String tubename;
}
