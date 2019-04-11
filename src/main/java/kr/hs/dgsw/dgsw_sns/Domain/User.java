package kr.hs.dgsw.dgsw_sns.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String userid;
    private String username;
    private String password;
    private String email;
    private String savepath;
    private String ordinaryname;

    @CreationTimestamp
    private LocalDateTime created;


    public User(String userid, String username, String password, String email, String savepath, String ordinaryname) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.savepath = savepath;
        this.ordinaryname = ordinaryname;
    }
}

