package kr.hs.dgsw.dgsw_sns.Protocol;

import kr.hs.dgsw.dgsw_sns.Domain.Content;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContentUsernamePro extends Content {
    private String username;

    public ContentUsernamePro(Content cmt, String username){
        super(cmt);
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
