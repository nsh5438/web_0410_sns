package kr.hs.dgsw.dgsw_sns.Service;

import kr.hs.dgsw.dgsw_sns.Domain.Content;
import kr.hs.dgsw.dgsw_sns.Domain.User;
import kr.hs.dgsw.dgsw_sns.Protocol.ContentUsernamePro;
import kr.hs.dgsw.dgsw_sns.Repository.ContentRep;
import kr.hs.dgsw.dgsw_sns.Repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceimp implements  ContentService {

    @Autowired
    private ContentRep contentRep;
    @Autowired
    private UserRep userRep;


    @Override
    public List<ContentUsernamePro> lstAllContent() {
        List<Content> commentList = this.contentRep.findAll();
        List<ContentUsernamePro> cmtlst = new ArrayList<>();
        commentList.forEach(content -> {
            Optional<User> found = this.userRep.findByUserid(content.getUserid());
            String username = null;
            if (found.isPresent()) username = found.get().getUsername();
            cmtlst.add(new ContentUsernamePro(content, username));

        });
        return cmtlst;
    }

    @Override
    public ContentUsernamePro addcnt(Content cmt) {
        Content comment = this.contentRep.save(cmt);
        return new ContentUsernamePro(
                comment,
                this.userRep.findByUserid(comment.getUserid()).map(user->user.getUsername()).orElse(null));
    }

    @Override
    public Content updatecmt(Long id, Content cmt) {
        return this.contentRep.findById(id)
                .map(found -> {
                    found.setExplanation(Optional.ofNullable(cmt.getExplanation()).orElse(found.getExplanation()));
                    return this.contentRep.save(found);
                })
                .orElse(null);
    }

    @Override
    public boolean deletecmt(Long id) {
        Optional<Content> comment = this.contentRep.findById(id);
        if (comment.isPresent()) {
            this.contentRep .delete(comment.get());
            return true;
        } else
            return false;
    }
}
