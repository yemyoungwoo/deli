<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav>
    <ul>
        <li><a href="/"></a></li>
        <li><a href="/store/search"></a></li>
        <li><a href="/likes/store" onclick="return loginCheck();"></a></li>
        <li><a href="/orderList" onclick="return loginCheck();"></a></li>
        <li><a href="/mypage"></a></li>
    </ul>
</nav>