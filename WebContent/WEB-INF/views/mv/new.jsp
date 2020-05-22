<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:import url="../layout/app.jsp">
  <c:param name="content">
    <h3 class="new">新規作成</h3>
    <form method="POST" class="form"
      action="${pageContext.request.contextPath}/create">
      <c:import url="../layout/form.jsp" />
    </form>
    <p>
      <a href="${pageContext.request.contextPath}/index">一覧に戻る</a>
    </p>
  </c:param>
</c:import>