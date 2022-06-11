package com.ssafy.ssafit.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.ssafit.exception.FileDownloadException;
import com.ssafy.ssafit.exception.FileUploadException;
import com.ssafy.ssafit.model.dao.FigureDao;
import com.ssafy.ssafit.model.dto.Figure;
import com.ssafy.ssafit.property.FileUploadProperties;

@Service
public class FigureServiceImpl implements FigureService {
	
	@Autowired
	private FigureDao figureDao;
	
	@Override
	public Figure getFigureOne(String userId) {
		return figureDao.selectFigureOne(userId);
	}

	@Override
	public void writeFigure(String userId) {
		figureDao.insertFigure(userId);
	}

	@Override
	public boolean modifyFigure(Figure figure) {
		Figure originFigure = figureDao.selectFigureOne(figure.getUserId());
		originFigure.setFileUri(figure.getFileUri());
		originFigure.setFileName(figure.getFileName());
		return figureDao.updateFigure(originFigure) == 1;
	}
}
