package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tasks;
import utils.DBUtil;

/**
 * Servlet implementation class DestroyServlet
 */
@WebServlet("/destroy")
public class DestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Tasks tl = em.find(Tasks.class, (Integer) (request.getSession().getAttribute("tasklist_id")));

            //DB操作
            em.getTransaction().begin();
            em.remove(tl);// データ削除
            em.getTransaction().commit();
            request.getSession().setAttribute("flush", "削除が完了しました。");
            em.close();

            // セッションスコープ上の不要になったデータを削除
            request.getSession().removeAttribute("tasklist_id");

            // indexページへリダイレクト
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}