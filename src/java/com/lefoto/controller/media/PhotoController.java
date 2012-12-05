/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lefoto.controller.media;

import com.lefoto.common.base.BaseController;
import com.lefoto.model.content.LeComment;
import com.lefoto.model.media.LePhoto;
import com.lefoto.model.user.LeUser;
import com.lefoto.service.iface.content.CommentService;
import com.lefoto.service.iface.media.PhotoService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Eric
 */
@Controller
@RequestMapping("/photo")
public class PhotoController extends BaseController {
    
    @Autowired PhotoService photoService;
    @Autowired CommentService commentService;

    @RequestMapping(value = "/detail")
    public ModelAndView show(HttpServletRequest request) {
        this.execute(request);
        ModelAndView mv = new ModelAndView("/photo/detail");
        int photoId = this.getParaIntFromRequest("photoId");
        LeUser user = this.getUser();
        if (user != null) {
            mv.addObject("user", user);
        }
        LePhoto photo = photoService.findPhotoById(photoId);
        List<LeComment> comments = commentService.getCommentsAjax(0, photo.getId(), 0, 20);
        mv.addObject("photo", photo);
        mv.addObject("comments", comments);
        return mv;
    }
}