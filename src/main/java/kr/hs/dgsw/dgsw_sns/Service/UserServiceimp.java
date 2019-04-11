package kr.hs.dgsw.dgsw_sns.Service;

import kr.hs.dgsw.dgsw_sns.Domain.Content;
import kr.hs.dgsw.dgsw_sns.Domain.User;
import kr.hs.dgsw.dgsw_sns.Repository.ContentRep;
import kr.hs.dgsw.dgsw_sns.Repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class UserServiceimp implements UserService{


    @Autowired
    private UserRep userRep;

    @Autowired
    private ContentRep contentRep;

    @PostConstruct
    private void init() {

        if(this.userRep.count() > 0) return;
        User u = this.userRep.save(new User("abc", "1234","1234","abcdgsw",
                "D:/3102_남가영/IdeaProjects/dgsw_sns/upload/ddac7354-0b41-40f8-b5c4-a5e2a48084e0 flower.jpg",
                "flower.jpg"));

        this.contentRep.save(new Content(u.getUserid(), "hi 111"));
        this.contentRep.save(new Content(u.getUserid(), "hi 111"));
        this.contentRep.save(new Content(u.getUserid(), "hi 111"));

    }

    @Override
    public User login(User user) {
        Optional<User> found = this.userRep.findByUserid(user.getUserid());
        if(found.isPresent()){
            if(found.get().getPassword().equals(user.getPassword())){
                return found.get();
            }
        }
        return null;
    }
}
