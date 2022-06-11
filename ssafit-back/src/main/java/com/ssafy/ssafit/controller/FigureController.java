package com.ssafy.ssafit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.model.dto.Figure;
import com.ssafy.ssafit.service.FigureService;

@RestController
@RequestMapping("/api")
public class FigureController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private FigureService figureService;
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("/figure/{userId}")
	public ResponseEntity<Figure> one(@PathVariable String userId) {
		return new ResponseEntity<Figure>(figureService.getFigureOne(userId), HttpStatus.OK);
	}
	
	@PostMapping("/upload/{userId}")
	public ResponseEntity<String> upload(@PathVariable String userId, @RequestParam(value="upload_file", required = false) MultipartFile upload_file) {	// front의 input 태그의 name="upload_file"
//		System.out.println("fileName : " + upload.getOriginalFilename());
//		
//		String fileName = fileUploadDownloadService.storeFile(upload);
//		System.out.println("fileName : " + fileName);
//        
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                                .path("/downloadFile/")
//                                .path(fileName)
//                                .toUriString();
//		System.out.println("fileDownloadUri : " + fileDownloadUri);
		
		String uploadPath = servletContext.getRealPath("/file");
		System.out.println("uploadPath : " + uploadPath);
		System.out.println("-----" + upload_file);
		String fileName = upload_file.getOriginalFilename();
		System.out.println("fileName : " + upload_file.getOriginalFilename());
		File target = new File(uploadPath, fileName);
		if( !new File(uploadPath).exists() )
			new File(uploadPath).mkdirs();
		try {
			FileCopyUtils.copy(upload_file.getBytes(), target);
			System.out.println("target : " + target.getCanonicalPath());
//			URL r = this.getClass().getResource("");
//			String path = r.getPath();
//			String path1 = path.split("com")[0];
//			String path2 = path1.substring(1);
			String path = target.getCanonicalPath().split("webapp")[0];
			String path1 = path + "\\resources\\static\\file";
//			String path2 = path1.substring(1);
			System.out.println("path: " + path);
//			System.out.println("path1: " + path1);
			System.out.println("path1: " + path1 + "\\" + fileName);
//			System.out.println("path2: " + path2);
//			path2 = path2.replace('/', '\\');
//			System.out.println("path2: " + path2 + "file\\" + fileName);
			
			//파일객체생성
	        File oriFile = new File(target.getCanonicalPath());
	        //복사파일객체생성
	        File copyFile = new File(path1 + "\\"+ fileName);
			
	        FileInputStream fis = new FileInputStream(oriFile); //읽을파일
            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
            
            int fileByte = 0; 
            // fis.read()가 -1 이면 파일을 다 읽은것
            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }
            //자원사용종료
            fis.close();
            fos.close();
			
			Figure figure = figureService.getFigureOne(userId);
			figure.setFileUri(path1 + "\\"+ fileName);
			figure.setFileName(fileName);
			figureService.modifyFigure(figure);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
	}
}
