package controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import models.validators.TasksValidator;
import utils.DBUtil;

/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        //空ファイルだった場合の対策
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            //インスタンス
            Tasks tl = new Tasks();

            //フォームから受け取る
            String content = request.getParameter("content");
            tl.setContents(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            tl.setCreated_date(currentTime);
            tl.setUpdated_date(currentTime);

            // バリデーションを実行してエラーがあったら新規登録のフォームに戻る
            List<String> errors = TasksValidator.validate(tl);
            if (errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("tasklist", tl);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mv/new.jsp");
                rd.forward(request, response);
            } else {
                // データベースに保存
                em.getTransaction().begin();
                em.persist(tl);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "入力が完了しました。");
                em.close();

                // indexのページにリダイレクト
                response.sendRedirect(request.getContextPath() + "/index");
            }
        }
    }
}