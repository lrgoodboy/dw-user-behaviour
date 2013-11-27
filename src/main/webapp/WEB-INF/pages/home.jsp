<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="naviTab" value="home" scope="request" />
<t:base>

<jsp:attribute name="styles">
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
</jsp:attribute>

<jsp:body>

<div class="row">
  <div class="span6 system-stats">
    <div style="padding: 50px 0 0 100px;">
      <span class="stats-label">总查询次数</span>
      <span class="stats-text">10000 次</span>
    </div>
    <div style="padding: 10px 0 0 100px;">
      <span class="stats-label">总使用人数</span>
      <span class="stats-text">30 人</span>
    </div>
  </div>
  <div class="span6 system-mission">
    <div style="padding-top: 50px;">
      <div style="margin-bottom: 5px;">为什么使用系统？</div>
      <ul>
        <li>提供高效的目标人群筛选，找到你想要的人。</li>
        <li>更好的用户行为分析，帮助产品、研发、市场和其他相关部门了解用户的精确动向！
      </ul>
    </div>
  </div>
</div>

</jsp:body>

</t:base>