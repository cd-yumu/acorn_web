package com.example.spring15.service;

import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.example.spring15.dto.CommentDto;
import com.example.spring15.dto.CommentListRequest;
import com.example.spring15.dto.PostDto;
import com.example.spring15.dto.PostListDto;
import com.example.spring15.exception.DeniedException;
import com.example.spring15.repository.CommentDao;
import com.example.spring15.repository.PostDao;

@Repository
public class PostServiceImpl implements PostService{
	
	
	//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=5;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	// 서비스는 어러가지 dao 에 의존성이 있을 수 있다.
	@Autowired private PostDao postDao;
	@Autowired private CommentDao commentDao;

	/*
	 *  pageNum 과 검색 조건, 키워드가 담겨있을 수 있는 PostDto 를 전달하면
	 *  해당 글 목록을 리턴하는 메소드
	 *  
	 *  *어디서든지 페이지 번호와 검색 조회 정보가 담긴 Dto 만 전달하면 사용할 수 있음으로
	 *  Post 에서만 한정된 것은 아니다!
	 */
	
	@Override
	public PostListDto getPosts(int pageNum, PostDto search) {
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		//하단 시작 페이지 번호 
		int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//하단 끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//전체 글의 갯수
		int totalRow=postDao.getCount(search);
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
		if(endPageNum > totalPageCount){
			endPageNum=totalPageCount; //보정해 준다. 
		}	
		
		// startRowNum 과 endRowNum 을 PostDto 객체에 담아서
		search.setStartRowNum(startRowNum);
		search.setEndRowNum(endRowNum);
		
		// 글 목록 얻어오기
		List<PostDto> list = postDao.getList(search);
		
		String findQuery = "";
		if(search.getKeyword() != null) {
			findQuery = "&keyword=" + search.getKeyword() + "&condition=" + search.getCondition();
		}
		
		// 예전에 요청 범위에 일일이 넣었던 것을 전용 dto 로 만들어서 한 줄 코딩으로 빌드 후 그 객체로 전달한다.
		PostListDto dto = PostListDto.builder()
				.list(list)
				.startPageNum(startPageNum)
				.endPageNum(endPageNum)
				.totalPageCount(totalPageCount)
				.pageNum(pageNum)
				.totalRow(totalRow)
				.findQuery(findQuery)
				.condition(search.getCondition())
				.keyword(search.getKeyword())
				.build();
		return dto;
	}

	@Override
	public long createPost(PostDto dto) {
		// 글 작성자를 얻어내서 dto에 담는다.
		String writer = SecurityContextHolder.getContext().getAuthentication().getName();
		dto.setWriter(writer);
		
		// 저장할 글 번호를 미리 얻어온다.
		long num = postDao.getSequence();
		
		// dto 에 글번호를 넣은 다음 DB 에 저장한다.
		dto.setNum(num);
		postDao.insert(dto);
		
		// 저장한 글 번호를 리턴해준다.
		return num;
	}

	@Override
	public PostDto getByNum(long num) {
		return postDao.getData(num);
	}

	@Override
	public PostDto getDetail(PostDto dto) {
		return postDao.getDetail(dto);
	}

	@Override
	public void updatedPost(PostDto dto) {
		postDao.update(dto);
	}

	@Override
	public void deletePost(long num) {
		// 글 작성자와 로그인된 userName 과 다르면 Exception 을 발생시키고 ExceptionController 에서 처리하게 한다
		/*
		String writer = postDao.getData(num).getWriter();
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if(!writer.equals(userName)) {
			throw new DeniedException("요청이 거부되었습니다");
		}*/
		System.out.println("111");
		// posts 의 num 을 참조하고 있는 자식 레코드를 미리 삭제
		postDao.deleteReaded(num);
		System.out.println("222");
		// 글 삭제
		postDao.delete(num);
	}

	@Override
	public void manageViewCount(long num, String sessionId) {
		// 세션 아이디를 얻어와서
		boolean isReaded = postDao.isReaded(num, sessionId);
		// 만약 안 읽었다면
		if(!isReaded) {
			// 조회수 1 증가
			postDao.addViewCount(num);
			// 이미 읽었다고 표시
			postDao.insertReaded(num, sessionId);
		}
	}

	@Override
	public void createComment(CommentDto dto) {
		// 댓글 작성자 (session 객체가 없기 때문에 바로 session 에서 얻을 수는 없다. 가져오면 가능)
		String writer = SecurityContextHolder.getContext().getAuthentication().getName();
		// 저장할 댓글의 번호 미리 얻어내기
		long num = commentDao.getSequence();
		long parentNum = dto.getParentNum();
		// 만일 원글의 댓글이면
		if(parentNum == 0) {
			// 댓글의 글번호가 parentNum 이 된다.
			parentNum = num;
		}
		// CommentDto 에 추가 정보를 담는다.
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setParentNum(parentNum);
		// dao 를 이용해서 DB에 저장
		commentDao.insert(dto);
		// rowcount 를 받아 예외 처리를 구지 추가하지 않는 이유는 DB 에서 exception 처리 하면 되기 때문이다?
	}

	@Override
	public void updateComment(CommentDto dto) {
		commentDao.update(dto);
	}

	@Override
	public void deleteComment(long num) {
		commentDao.delete(num);
	}

	@Override
	public Map<String, Object> getComments(CommentListRequest clr) {
		
		CommentDto dto=new CommentDto();
		dto.setPostNum(clr.getPostNum());
		/*
		[ 댓글 페이징 처리에 관련된 로직 ]
		*/
		//한 페이지에 댓글을 몇개씩 표시할 것인지
		final int PAGE_ROW_COUNT=10;
		int pageNum = clr.getPageNum();
		
		//보여줄 페이지의 시작 ROWNUM
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지의 끝 ROWNUM
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		//계산된 값을 dto 에 담는다
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		
		//전체 댓글의 갯수
		int totalRow=commentDao.getCount(clr.getPostNum());
		//전체 페이지의 갯수 구하기
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		//pageNum 에 해당하는 댓글 목록을 얻어낸다.
		List<CommentDto> list=commentDao.getList(dto);
		//Gson 객체에 전달할 Map 객체를 구성한다.
		Map<String, Object> map=new HashMap<>();
		map.put("list", list);
		map.put("totalPageCount", totalPageCount);
		
		return map;
	}


}
