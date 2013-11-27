<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="naviTab" value="filter" scope="request" />
<t:base>

<jsp:attribute name="styles">
<link href="<c:url value="/resources/css/filter.css" />" rel="stylesheet">
</jsp:attribute>

<jsp:body>

<form class="form-inline filter-form">
  <input type="text" name="date" placeholder="日期" class="input-medium">
  <select name="os" class="input-medium">
    <option value="">系统</option>
    <option value="ios">iOS</option>
    <option value="android">Android</option>
  </select>
  <select name="version" class="input-medium">
    <option value="">版本</option>
    <option value="6.0">6.0</option>
    <option value="6.0.1">6.0.1</option>
  </select>
  <select name="channel" class="input-medium">
    <option value="">渠道</option>
    <option value="A01">A01</option>
    <option value="A02">A02</option>
  </select>
  <input type="button" value="筛选" class="btn">
</form>

<table class="table table-condensed table-striped">
  <tr>
    <th>#</th>
    <th>Unique ID</th>
    <th>Page Name</th>
    <th>Action Name</th>
    <th>Time</th>
  </tr>
  <c:forEach var="actionLog" items="${actionLogList}">
  <tr>
    <td>${actionLog.id}</td>
    <td>${actionLog.uniqueId}</td>
    <td>${actionLog.pageId}</td>
    <td>${actionLog.actionId}</td>
    <td>${actionLog.logTime}</td>
  </tr>
  </c:forEach>
</table>

</jsp:body>

</t:base>