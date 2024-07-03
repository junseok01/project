package com.example.project.login;


import com.example.project.trainer.Chat.entity.ChatRoom;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column
    private int loginType;
    @Column
    private Integer point=0;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<ChatRoom> roomlist = new ArrayList<>();

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
    public UserEntity(String loginId, String loginPw, String name, String nickname, String userType, String cellphoneNo,int loginType) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.nickname = nickname;
        this.userType = userType;
        this.cellphoneNo = cellphoneNo;
        this.loginType=loginType;
    }

    public UserEntity(String loginId, String name, String nickname, String userType, int loginType) {
        this.loginId = loginId;
        this.name = name;
        this.nickname = nickname;
        this.userType = userType;
        this.loginType = loginType;
    }
}
