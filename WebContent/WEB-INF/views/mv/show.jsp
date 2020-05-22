<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
  <c:param name="content">
    <c:choose>
      <c:when test="${tasklist != null}">
        <h3>履歴:${tasklist.id }の詳細</h3>
        <table>
          <tbody>
            <tr>
              <th>作成日時</th>
              <td><fmt:formatDate value="${tasklist.created_date}"
                  pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <tr>
              <th>更新日時</th>
              <td><fmt:formatDate value="${tasklist.updated_date}"
                  pattern="yyyy-MM-dd HH:mm:ss" /></td>
            </tr>
            <tr>
              <th>メッセージ</th>
              <td><c:out value="${tasklist.contents}" /></td>
            </tr>
          </tbody>
        </table>
        <p class="show">
          <a href="${pageContext.request.contextPath}/edit?id=${tasklist.id}">この履歴を編集する</a>
        </p>

        <p class="show">
          <a href="#" onclick="confirmDestroy();">この履歴を削除する</a>
        </p>
        <form method="POST"
          action="${pageContext.request.contextPath}/destroy">
          <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
          function confirmDestroy() {
              if(confirm("本当に削除しますか？")) {
                  document.forms[0].submit();
              }
          }
          </script>
        <p>
          <a href="${pageContext.request.contextPath}/index">一覧に戻る</a>
        </p>
      </c:when>
      <c:otherwise>
        <h3>お探しのデータは見つかりません</h3>
      </c:otherwise>
    </c:choose>
  </c:param>
</c:import>