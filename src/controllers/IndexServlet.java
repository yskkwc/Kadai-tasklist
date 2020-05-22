package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(NumberFormatException e){}

        //1ページあたりの最大件数と開始の指定
        List<Tasks> tasklists = em.createNamedQuery("getAllTasks", Tasks.class)
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .getResultList();

        // 全件数を取得
        long tasklist_count = (long)em.createNamedQuery("getTasksCount", Long.class)
                                      .getSingleResult();

        em.close();

        request.setAttribute("tasklists", tasklists);
        request.setAttribute("tasklists_count", tasklist_count); // 全件
        request.setAttribute("page", page);

        //セッションスコープに登録されたフラッシュメッセージをリクエストスコープへ入れてから除去
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mv/index.jsp");
        rd.forward(request, response);
    }
}