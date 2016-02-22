package com.civil.interfaces.web.upload.image;

import com.vzs.application.qiniu.QiniuUploadServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by byao on 6/7/15.
 */
@Controller
@RequestMapping("/upload/image/")
public class ImageUploadController {

	@Value("#{environment['qiniu.bulkname.default']}")
	String bulkName;

	@Autowired
	QiniuUploadServcie qiniuUploadServcie;

	@RequestMapping("/simple-image-upload")
	public ModelAndView upload() {
		ModelAndView mav = new ModelAndView("/upload/images/simpleImageUpload");
		return mav;
	}

	@RequestMapping("/upload-cdn")
	@ResponseBody
	public Boolean imageUpload(@RequestParam("file") MultipartFile file) throws IOException {
		return qiniuUploadServcie.upload(file.getBytes(), bulkName, UUID.randomUUID() + ".jpg");
	}
}
