package kr.hs.dgsw.dgsw_sns.Controller;

import kr.hs.dgsw.dgsw_sns.Domain.Content;
import kr.hs.dgsw.dgsw_sns.Protocol.ContentUsernamePro;
import kr.hs.dgsw.dgsw_sns.Service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    @GetMapping("/list")
    public List<ContentUsernamePro> list(){
        return this.contentService.lstAllContent();
    }

    @PostMapping("/addcmt")
    public ContentUsernamePro addcmt(@RequestBody Content cmt) {return this.contentService.addcnt(cmt);}

    @PutMapping("/updatecmt/{id}")
    public Content updatecmt(@PathVariable Long id, @RequestBody Content cmt) {return this.contentService.updatecmt(id,cmt);}

    @DeleteMapping("/deletecmt/{id}")
    public boolean deletecmt(@PathVariable Long id){ return this.contentService.deletecmt(id); }

}
