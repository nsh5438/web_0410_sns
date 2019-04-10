package kr.hs.dgsw.dgsw_sns.Protocol;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class AttachmentPro {

    private String savepath;
    private String originalname;

    public AttachmentPro(String savepath, String originalname) {
        this.savepath = savepath;
        this.originalname = originalname;

    }
}