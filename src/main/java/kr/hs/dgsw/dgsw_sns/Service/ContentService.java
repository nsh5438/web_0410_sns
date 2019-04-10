package kr.hs.dgsw.dgsw_sns.Service;

import kr.hs.dgsw.dgsw_sns.Domain.Content;
import kr.hs.dgsw.dgsw_sns.Protocol.ContentUsernamePro;

import java.util.List;

public interface ContentService {

    List<ContentUsernamePro> lstAllContent();

    ContentUsernamePro addcnt(Content cmt);

    Content updatecmt(Long id, Content cmt);

    boolean deletecmt(Long id);
}
