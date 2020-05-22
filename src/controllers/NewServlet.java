package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;

/**
 * Servlet implementation class NewServlet
 */
@WebServlet("/new")
public class NewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
    IOException {
        //CSRF対策
        request.setAttribute("_token", request.getSession().getId());

        //
        request.setAttribute("tasklist", new Tasks());

        //現状、new.jspを開くだけのServlet
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mv/new.jsp");
        rd.forward(request, response);
    }

        /*EntityManager em = DBUtil.createEntityManager();
        em.getTransaction().begin();
        // TODO Auto-generated method stub
        //インスタンス
        Tasklist tl = new Tasklist();
        //テーブルに送るデータ
        String contents = "準備用にノートを購入";
        tl.setContents(contents);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        tl.setCreated_date(currentTime);
        tl.setUpdated_date(currentTime);
        //DBに保存のメソッド
        em.persist(tl);
        em.getTransaction().commit();
        //idは自動で採番されるようにする(1→2→3→4→5)
        response.getWriter().append(Integer.valueOf(tl.getId()).toString());
        em.close();
    }*/
}