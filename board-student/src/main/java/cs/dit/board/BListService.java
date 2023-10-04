package cs.dit.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. BoardDao 를 생성
		BoardDao dao = new BoardDao();
		int count = dao.RecordCount();  //전체 레코드의 갯수
		int numOfRecords = 10;          //한번에 가져올 레코드의 갯수
		int numOfPages = 5;             //한 화면에 표시될 페이지의 갯수
		int p=1;  //현재 페이지 번호
		
		String page = request.getParameter("p");
		
		if(page!=null && !page.equals("")) {
		p = Integer.parseInt("page");}
		
		int startNum = p-((p-1)% numOfPages);
		int lastNum = (int)Math.ceil((float)(count/numOfRecords));
		
		//2. dao의 해당 메소드를 호출
		ArrayList<BoardDto> dtos = dao.list(p, numOfRecords);
		
		//3. 호출 결과 처리
		
		request.setAttribute("dtos", dtos);
		request.setAttribute("p", p);
		request.setAttribute("startNum", startNum);
		request.setAttribute("lastNum", lastNum);
		request.setAttribute("numOfPages", numOfPages);
	}

}
