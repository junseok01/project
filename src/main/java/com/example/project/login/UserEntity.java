package com.example.project.login;


import com.example.project.gym.GymBoardEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.*;

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
    //결재를 위한 포인트
    @Column
    private Integer point=0;

    @ManyToMany
    @JoinTable(
            name = "user_hearts",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "gymboard_id")
    )
    private List<GymBoardEntity> heartedGyms = new ArrayList<>();

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

    public void heartGym(GymBoardEntity gymBoard) {
        // 이미 하트를 누른 게시물인지 확인
        if (!heartedGyms.contains(gymBoard)) {
            gymBoard.incrementHeartCount();
            heartedGyms.add(gymBoard);
        }
    }

    public void unheartGym(GymBoardEntity gymBoard) {
        if (heartedGyms.contains(gymBoard)) {
            gymBoard.decrementHeartCount();
            System.out.println(gymBoard.hashCode() + " : " + heartedGyms);
            heartedGyms.remove(gymBoard);
            System.out.println("삭제 후 언하트 입니다 +++++++++" + heartedGyms);
        }
    }
}
