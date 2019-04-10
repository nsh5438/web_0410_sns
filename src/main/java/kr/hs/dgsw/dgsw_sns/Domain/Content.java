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
public class Content {

    @Id
    @GeneratedValue
    private Long id;
    private String userid;
    private String explanation;
    private String savepath;
    private String ordinaryname;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    public Content(String userid, String explanation)
    {
        this.userid = userid;
        this.explanation = explanation;
    }

    public Content(Content cmt){
        this.id = cmt.getId();
        this.explanation = cmt.explanation;
        this.userid = cmt.getUserid();
        this.created = cmt.getCreated();
        this.updated = cmt.getUpdated();
    }
}
