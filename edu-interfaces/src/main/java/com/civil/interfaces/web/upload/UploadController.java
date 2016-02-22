package com.civil.interfaces.web.upload;

import com.civil.report.reportService.WaveTimeHistoryService;
import com.vzs.Infrastructure.auth.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Created by zaza on 5/5/15. Not used now, will be using if onboarding file uploading function for certain app.
 */
@Controller
@MultipartConfig
@Slf4j
@Role("admin")
public class UploadController {
    @RequestMapping("/upload")
    public ModelAndView upload() {
        ModelAndView mav = new ModelAndView("upload/upload");
        return mav;
    }

    @RequestMapping(value = "/upload/submit", method = RequestMethod.POST)
    public void handleFileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
                                 HttpServletResponse response) {
        if (!file.isEmpty()) {
            try {

                BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
                WaveTimeHistoryService whs = new WaveTimeHistoryService();
                ByteArrayOutputStream baos = whs.processWaveTimeHistoryLoading(br);

                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                response.setHeader("Content-disposition", "attachment; filename=WaveTimeHistory.xlsx;");
                response.getOutputStream().write(baos.toByteArray());

            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            log.info("The file uploaded was empty.");
        }
    }

}
