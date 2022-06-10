package jp.co.aforce;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.beans.Bean;
import jp.co.aforce.dao.UpdateDao;



@WebServlet(urlPatterns = { "/jp.co.aforce/update" })
public class Update extends HttpServlet {

	public void doPost(
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		try {


		//パラメータの取得
		String last_name = request.getParameter("last_name");
		String first_name = request.getParameter("first_name");
		String sex = request.getParameter("sex");
		int birth_year= Integer.parseInt(request.getParameter("birth_year"));
		int birth_month= Integer.parseInt(request.getParameter("birth_month"));
		int birth_day= Integer.parseInt(request.getParameter("birth_day"));
		String job = request.getParameter("job");
		String phone_number = request.getParameter("phone_number");
		String mail_address = request.getParameter("mail_address");

		//オブジェクト宣言
		Bean b = new Bean();

		b.setLastName(last_name);
		b.setFirstName(first_name);
		b.setSex(sex);
		b.setYear(birth_year);
		b.setMonth(birth_month);
        b.setDay(birth_day);
        b.setJob(job);
        b.setPhone(phone_number);
        b.setMail(mail_address);

    	//DAOオブジェクト宣言
		UpdateDao up = new UpdateDao();

		//更新メソッドの呼び出し
		 int line = up.update(b);

		request.setAttribute("bean", b);



		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
