package com.example.project.login;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String loginId;

    private String loginPw;

    private String name;

    private String nickname;
    //관리번호(0-관리자, 1-일반유저,2-트레이너,3-관장)

    private String userType;

    private String cellphoneNo;
    private Date joinDate;

    public UserDTO(UserEntity entity) {
        this.loginId = entity.getLoginId();
        this.loginPw = entity.getLoginPw();
        this.name = entity.getName();
        this.nickname = entity.getNickname();
        this.userType = entity.getUserType();
        this.cellphoneNo = entity.getCellphoneNo();
        this.joinDate = entity.getJoinDate();
    }
}
