<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="naviTab" value="filter" scope="request" />
<t:base>

<jsp:attribute name="styles">
<link href="<c:url value="/resources/css/filter.css" />" rel="stylesheet">
</jsp:attribute>

<jsp:body>
<form class="form-inline filter-form" method="get" action="">
  <input type="text" name="date" placeholder="日期" class="input-medium" value="${date}">
  <select name="os" class="input-medium">
    <option value="">系统</option>
    <option value="ios" ${os == 'ios' ? 'selected="selected"' : ''}>iOS</option>
    <option value="android" ${os == 'android' ? 'selected="selected"' : ''}>Android</option>
  </select>
  <select name="version" class="input-medium">
    <option value="">版本</option>
    <c:forEach var="key" items="${versionList}">
    <option value="${key}" ${key == version ? 'selected="selected"' : ''}>${key}</option>
    </c:forEach>
  </select>
  <select name="channel" class="input-medium">
    <option value="">渠道</option>
    <c:forEach var="key" items="${channelList}">
    <option value="${key}" ${key == channel ? 'selected="selected"' : ''}>${key}</option>
    </c:forEach>
  </select>
  <input type="submit" value="筛选" class="btn">
</form>

<table class="table table-condensed table-striped">
  <tr>
    <th>#</th>
    <th>Unique ID</th>
    <th>Page Name</th>
    <th>Action Name</th>
    <th>Client Time</th>
  </tr>
  <c:forEach var="actionLog" items="${actionLogList}">
  <tr>
    <td>${actionLog.id}</td>
    <td><a href="<c:url value="/user?date=${date}&uniqid=${actionLog.uniqueId}" />">${actionLog.uniqueId}</a></td>
    <td>${actionLog.pageId}</td>
    <td>${actionLog.actionId}</td>
    <td>${actionLog.logTime}</td>
  </tr>
  </c:forEach>
</table>

</jsp:body>

</t:base>