package com.example.project.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PDF_file")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private RequestEntity request;

    @Column
    private String fileOriginalName;

    @Column
    private String filepath;

    @Column
    private String fileName;

    public FileEntity(RequestEntity request, String filepath, String fileName) {
        this.request = request;
        this.filepath = filepath;
        this.fileName = fileName;
    }

    public FileEntity(RequestEntity request, String fileOriginalName, String fileName, String filepath) {
        this.request = request;
        this.fileOriginalName = fileOriginalName;
        this.fileName = fileName;
        this.filepath = filepath;
    }

    //toString시 무한루프에 빠지므로 request는 출력에서 제외시킴
    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", fileOriginalName='" + fileOriginalName + '\'' +
                ", filepath='" + filepath + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}