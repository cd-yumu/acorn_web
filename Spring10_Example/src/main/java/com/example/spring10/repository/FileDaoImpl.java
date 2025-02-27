package com.example.spring10.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

@Repository
public class FileDaoImpl implements FileDao{
	
	@Autowired SqlSession session;
	
	@Override
	public List<FileDto> getList(FileListDto dto) {
		return session.selectList("file.getList", dto);
	}

	@Override
	public int saveFileInfo(FileDto dto) {
		return session.insert("file.insertFileInfo", dto);
	}

	@Override
	public FileDto getData(long num) {
		return session.selectOne("file.getData", num);
	}

	@Override
	public int deleteFile(long num) {
		return session.delete("file.deleteFileInfo", num);
	}

	@Override
	public int getCount(FileListDto dto) {
		return session.selectOne("file.getCount", dto);
	}

}
