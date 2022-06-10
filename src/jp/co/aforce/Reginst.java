package jp.co.aforce;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Bean;
import jp.co.aforce.dao.ReginstDao;
import jp.co.aforce.dao.SearchDao;
import jp.co.aforce.tool.Page;

@WebServlet(urlPatterns = { "/jp.co.aforce/Reginst" })
public class Reginst extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Page.header(out);


		try {
			//パラメータの取得
			String lastName = request.getParameter("last_name");
			String firstName = request.getParameter("first_name");
			String sex = request.getParameter("sex");
			int year = Integer.parseInt(request.getParameter("birth_year"));
			int month = Integer.parseInt(request.getParameter("birth_month"));
			int day = Integer.parseInt(request.getParameter("birth_day"));
			String job = request.getParameter("job");
			String phone = request.getParameter("phone_number");
			String mail = request.getParameter("mail_address");


			//入力値セット

			Bean b = new Bean();

			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMddHHmmss");

			// 書式を指定してLocalDateTimeのインスタンスを作成する
			LocalDateTime ldt = LocalDateTime.now();

			// システム日付を、DateTimeFormatterで指定したフォーマットで文字列に変換
			String date = ldt.format(format);


			//A＋日付でIDを採番
			b.setId("A" + date);
			b.setLastName(lastName);
			b.setFirstName(firstName);
			b.setSex(sex);
			b.setYear(year);
			b.setMonth(month);
			b.setDay(day);
			b.setJob(job);
			b.setPhone(phone);
			b.setMail(mail);

			//SearchDaoオブジェクト宣言
			SearchDao sdao = new SearchDao();

			int line = sdao.search(b);

			if(line > 0) {
				request.setAttribute("message", "会員情報が既に登録されています。");
				request.getRequestDispatcher("../view/regist.jsp").forward(request, response);

			}else {

				//RegnistDaoオブジェクト宣言
				ReginstDao rdao = new ReginstDao();

			    rdao.insert(b);
				out.println("会員情報の登録が完了しました。");

			}
		} catch (Exception e) {
			out.println("会員情報の登録に失敗しました。");

			e.printStackTrace();

		}

		Page.footer(out);
	}
}
