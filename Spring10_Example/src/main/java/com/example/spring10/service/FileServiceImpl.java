package com.example.spring10.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
import com.example.spring10.repository.FileDao;

@Service
public class FileServiceImpl implements FileService{
	
	final int PAGE_ROW_COUNT = 3;		// 한 페이지에 몇 ROW 씩 보여줄
	final int PAGE_DISPLAY_COUNT = 4;	// 하단 페이징 버튼을 몇개씩 보여줄

	@Autowired FileDao dao;
	
	// 파일을 저장할 위치
	@Value("${file.location}")
	private String fileLocation;
	
	
	@Override
	public void uploadFile(FileDto dto) {
		
		// 만약 파일이 업로드 되지 않았을 경우
		if(dto.getMyFile().isEmpty()) {
			throw new RuntimeException("Can't Find File Uploaded");
		}
		
		// 업로드 한 사람
		String uploader = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setUploader(uploader);
		// 원본 파일명
		String orgingFileName = dto.getMyFile().getOriginalFilename();
		dto.setOriginFileName(orgingFileName);
		// 파일 크기
		long fileSize = dto.getMyFile().getSize();
		dto.setFileSize(fileSize);
		// 저장될 이름
		String saveFileName = fileLocation + File.separator + UUID.randomUUID().toString() + orgingFileName;
		dto.setSaveFileName(saveFileName);
		
		// 파일 저장
		try {
			File saveFile = new File(saveFileName);
			dto.getMyFile().transferTo(saveFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		// 저장된 파일 정보를 DB 에 저장
		dao.saveFileInfo(dto);
		
	}
	
	
	
	@Override
	public void uploadFile2(FileDto dto) {	// dto 에는 title 과 myFile 만 들어있는 상태
		
		// FileDto 객체에서 MultipartFile 객체를 얻어낸다.
		MultipartFile myFile = dto.getMyFile();
		
		// 만일 파일이 업로드되지 않았다면
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않았습니다.");
		}
		
		// 원본 파일명
		String originFileName = myFile.getOriginalFilename();
		// 파일의 크기
		long fileSize = myFile.getSize();
		//저장할 파일의 이름을 Universal Unique 한 문자열로 얻어내기
		String saveFileName = UUID.randomUUID().toString() + originFileName;
		// 저장할 파일의 전체 경로 구성하기
		String filePath = fileLocation + File.separator + saveFileName;
		
		try {
			// 업로드된 파일을 저장할 파일 객체 생성
			File saveFile = new File(filePath);
			myFile.transferTo(saveFile);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 업로더
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		// FileDto 에 추가 정보를 담는다.
		dto.setUploader(userName);
		dto.setSaveFileName(saveFileName);
		dto.setFileSize(fileSize);
		dto.setOriginFileName(originFileName);
		dto.setSaveFileName(filePath);
		
		// dao 를 이용해서 DB 에 저장하기
		dao.saveFileInfo(dto);
		
	}
	
	
	@Override
	public void updateFile(FileDto dto) {
		dao.updateFile(dto);
	}
	
	@Override
	public void deleteFile(long num) {
		// 실제 파일 삭제??
		
		// 파일 삭제
	 	int resultRow = dao.deleteFile(num);
	 	if(resultRow < 1) {
	 		throw new RuntimeException("파일 삭제 실패");
	 	}
	}

	
	
	
	
	
	
	@Override
	public FileListDto getDatas(FileListDto fileListDto) {
		// 요청 페이지 번호
		int pageNum = fileListDto.getPageNum();
		pageNum = (pageNum == 0) ? 1 : pageNum;
		fileListDto.setPageNum(pageNum);
		
		// DB 에서 가져올 시작 row
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		fileListDto.setStartRowNum(startRowNum);
		// DB 에서 가져올 끝 row
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		fileListDto.setEndRowNum(endRowNum);
		
		// 하단 페이징 시작 번호
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		fileListDto.setStartPageNum(startPageNum);
		// 하단 페이징 끝 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		
		// 전체 글 개수(를 알아야 전체 페이지 개수 계산 가능)
		int totalRow = dao.getCount(fileListDto);
		fileListDto.setTotalRowCount(totalRow);
		
		// 전체 페이지 개수(를 알아야
		int totalPageCount = (int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		if(endPageNum > totalPageCount){	//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
			endPageNum=totalPageCount; 		//보정해 준다. 
		}	
		fileListDto.setTotalPageCount(totalPageCount);
		fileListDto.setEndPageNum(endPageNum);
		
		// 위 정보를 바탕으로 DB 에서 리스트 뽑아오기
		List<FileDto> list = dao.getList(fileListDto);
		fileListDto.setList(list);
		
		// 검색 조건
		String findQuery = "";
		if(fileListDto.getKeyword() != null) {
			findQuery = "&keyword=" + fileListDto.getKeyword() + "&condition=" + fileListDto.getCondition();
		}
		fileListDto.setFindQuery(findQuery);
		
		return fileListDto;
	}

	

	@Override
	public ResponseEntity<InputStreamResource> getResponse(long num) {
		// 해당 파일 번호에 해당하는 파일 정보 추출
		FileDto dto = dao.getData(num);
		
		// 원본 파일명
		String originFileName = dto.getOriginFileName();
		// 저장 파일명
		String saveFileName = dto.getSaveFileName();
		// 파일 크기
		long fileSize = dto.getFileSize();
		
		// 파일 다운로드
		try {
			// 다운로드 시켜줄 원본 파일명 (한글때문에)
			String encodedName=URLEncoder.encode(originFileName, "utf-8");
			// 파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
			encodedName=encodedName.replaceAll("\\+"," ");
			//응답 헤더정보(스프링 프레임워크에서 제공해주는 클래스) 구성하기 (웹브라우저에 알릴정보)
			HttpHeaders headers=new HttpHeaders();
			// 파일을 다운로드 시켜 주겠다는 정보
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
			// 파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			// 파일의 크기 정보도 담아준다.
			headers.setContentLength(fileSize);
			
			// 읽어들일 파일의 경로 구성
			String filePath=saveFileName;
			
			// 파일에서 읽어들일 스트림 객체
			InputStream is = new FileInputStream(filePath);
			// InputStreamResource 객체의 참조값을 얻어내기
			InputStreamResource isr = new InputStreamResource(is);
			// ResponseEntity 객체를 구성해서
			ResponseEntity<InputStreamResource> resEntity = ResponseEntity.ok()
					.headers(headers)
					.body(isr);
			// 리턴해주면 파일이 다운로드 된다.
			return resEntity;
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("파일 다운로드 실패!!!");
		}
		
	}

	

	


}
