package com.example.project.login;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "member")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String loginId;
    @Column
    private String loginPw;
    @Column
    private String name;
    @Column
    private String nickname;
    //관리번호(0-관리자, 1-일반유저,2-트레이너,3-관장)
    @Column
    private String userType;
    @Column
    private String cellphoneNo;
    @CreationTimestamp
    private Date joinDate;

    public UserEntity(String loginId, String loginPw, String name, String nickname, String cellphoneNo) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.nickname = nickname;
        this.cellphoneNo = cellphoneNo;
    }

    public UserEntity(String loginId, String loginPw, String name, String nickname, String userType, String cellphoneNo) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.nickname = nickname;
        this.userType = userType;
        this.cellphoneNo = cellphoneNo;
    }
}
