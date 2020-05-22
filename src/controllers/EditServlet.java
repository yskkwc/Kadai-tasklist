package controllers;

import java.io.IOException;

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
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        //DBから指定したid１件をString型→integerに直して取得、そしてDB閉じる
        Tasks tl = em.find(Tasks.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        //DBから指定したidの情報を入れた変数tlをedit.jspに渡す
        request.setAttribute("tasklist", tl);
        request.setAttribute("_token", request.getSession().getId());

        //IDをセッションスコープに登録
        if (tl != null) {
            request.getSession().setAttribute("tasklist_id", tl.getId());
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/mv/edit.jsp");
        rd.forward(request, response);
    }
}