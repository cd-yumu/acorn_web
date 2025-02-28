package com.example.spring10.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

@Repository
public class FileDaoImpl implements FileDao{
	
	@Autowired private SqlSession session;
	
	
	@Override
	public int saveFileInfo(FileDto dto) {
		// 파일의 번호는 files_seq 라는 이름의 시퀀스로 넣기
		return session.insert("file.insertFileInfo", dto);
	}
	@Override
	public int updateFile(FileDto dto) {
		// 번호를 이용해서 title 만 수정하도록 한다.
		return session.selectOne("file.updateFileInfo", dto);
	}
	@Override
	public int deleteFile(long num) {
		// 번호를 이용해서 삭제
		return session.delete("file.deleteFileInfo", num);
	}
	
	
	@Override
	public List<FileDto> getList(FileListDto dto) {
		// 번호에 대해서 내림차순 정렬된 파일 목록 + 검색 조건
		return session.selectList("file.getList", dto);
	}

	
	
	
	@Override
	public FileDto getData(long num) {
		return session.selectOne("file.getData", num);
	}

	

	@Override
	public int getCount(FileListDto dto) {
		return session.selectOne("file.getCount", dto);
	}

	

}
