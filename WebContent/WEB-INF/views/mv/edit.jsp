<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../layout/app.jsp">
  <c:param name="content">
    <c:choose>
      <c:when test="${tasklist != null}">
        <h3>履歴番号: ${tasklist.id }の編集</h3>
        <form method="POST"
          action="${pageContext.request.contextPath}/update">
          <c:import url="../layout/form.jsp" />
        </form>
        <p>
          <a href="${pageContext.request.contextPath}/index">一覧に戻る</a>
        </p>
      </c:when>
      <c:otherwise>
        <h3>お探しのデータは見つかりません。</h3>
      </c:otherwise>
    </c:choose>
  </c:param>
</c:import>