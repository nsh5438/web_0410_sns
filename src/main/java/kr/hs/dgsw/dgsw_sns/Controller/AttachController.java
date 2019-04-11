package kr.hs.dgsw.dgsw_sns.Controller;

import kr.hs.dgsw.dgsw_sns.Domain.Content;
import kr.hs.dgsw.dgsw_sns.Domain.User;
import kr.hs.dgsw.dgsw_sns.Protocol.AttachmentPro;
import kr.hs.dgsw.dgsw_sns.Repository.ContentRep;
import kr.hs.dgsw.dgsw_sns.Repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachController {
    @Autowired
    private UserRep userRep;
    @Autowired
    private ContentRep contentRep;

    @PostMapping("/attachment")
    public AttachmentPro upload(@RequestPart MultipartFile uploadFile){
        String destFilename = "D:/3102_남가영/IdeaProjects/dgsw_sns/upload/" + UUID.randomUUID().toString() + " " + uploadFile.getOriginalFilename();
        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            uploadFile.transferTo(destFile);
            return new AttachmentPro(destFilename, uploadFile.getOriginalFilename());
        }catch (Exception e){
            return null;
        }
    }

    @PutMapping("/uploadfile/{id}")
    public Content uploadfile(@PathVariable Long id, @RequestBody Content content){
        try {
            return this.contentRep.findById(id)
                    .map(found->{
                        found.setSavepath(Optional.ofNullable(content.getSavepath()).orElse(found.getSavepath()));
                        found.setOrdinaryname(Optional.ofNullable(content.getOrdinaryname()).orElse(found.getOrdinaryname()));
                        found.setExplanation(Optional.ofNullable(content.getExplanation()).orElse(found.getExplanation()));
                        return contentRep.save(found);
                    })
                    .orElse(null);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping("/download/{type}/{id}")
    public void download(@PathVariable String type,@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){
        String filepath ;
        String filename;

        if(type.equals("content")){
            Optional<Content> found = this.contentRep.findById(id);
            filepath = found.get().getSavepath();
            filename = found.get().getOrdinaryname();
        }else{
            Optional<User> found = this.userRep.findById(id);
            filepath = found.get().getSavepath();
            filename = found.get().getOrdinaryname();
        }

        try{
            File file =new File(filepath);
            if(file.exists() == false) return;
            String fileType = URLConnection.guessContentTypeFromName(file.getName());
            if(fileType == null) fileType = "application/octet-stream";
            response.setContentType(fileType);
            response.setHeader("Content-Disposition", "inline; filename=\'" + filename + "\'");
            response.setContentLength((int)file.length());
            InputStream ip = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(ip,response.getOutputStream());
        }catch( Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
