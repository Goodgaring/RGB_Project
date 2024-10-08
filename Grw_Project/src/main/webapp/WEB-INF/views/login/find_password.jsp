<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>인증번호 요청페이지</title>
<link href="./css/password/password.css" rel="stylesheet" type="text/css" />
<style>
    .timer {
        color: red;
        font-size: 16px;
        font-weight: bold;
    }
</style>
</head>
<%@ include file="./lo_header.jsp"%>
<body id="kt_body" class="app-blank app-blank">
<script>var defaultThemeMode = "light"; var themeMode; if ( document.documentElement ) { if ( document.documentElement.hasAttribute("data-bs-theme-mode")) { themeMode = document.documentElement.getAttribute("data-bs-theme-mode"); } else { if ( localStorage.getItem("data-bs-theme") !== null ) { themeMode = localStorage.getItem("data-bs-theme"); } else { themeMode = defaultThemeMode; } } if (themeMode === "system") { themeMode = window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light"; } document.documentElement.setAttribute("data-bs-theme", themeMode); }</script>

<!-- 왼쪽 레이아웃 추가 -->
<div class="d-flex flex-column flex-root" id="kt_app_root">
    <div class="d-flex flex-column flex-lg-row flex-column-fluid">
        <!-- 왼쪽 영역 추가 -->
        <div
            class="d-flex flex-lg-row-fluid w-lg-50 bgi-size-cover bgi-position-center"
            style="background-image: url(./img/jsj1.jpg)">
            <!-- Content -->
            <div class="d-flex flex-column flex-center p-6 p-lg-10 w-100">
                <h1 class="d-none d-lg-block text-white fs-2qx fw-bold text-center mb-7">GDJ_81 RGB PROJECT</h1>
                <div class="d-none d-lg-block text-white fs-base text-center">조홍준, 명대홍, 양승, 김유진, 전성진</div>
            </div>
        </div>
        <!-- 오른쪽 영역 -->
        <div class="d-flex flex-column flex-lg-row-fluid w-lg-50 p-10">
            <div class="d-flex flex-center flex-column flex-lg-row-fluid">
                <div class="w-lg-500px p-10">
                    <form class="form w-100" novalidate="novalidate" id="kt_password_reset_form">
                        <div class="text-center mb-10">
                            <h1 class="text-gray-900 fw-bolder mb-3">암호를 잊으셨습니까 ?</h1>
                            <div class="text-gray-500 fw-semibold fs-6">비밀번호를 재설정하려면 요구하는 정보를 입력해주세요.</div>
                        </div>
                        <div class="fv-row mb-8">
                            <input type="text" placeholder="이름" name="emp_name" autocomplete="off" class="form-control bg-transparent custom-input" />
                        </div>
                        <div class="fv-row mb-8">
                            <input type="text" placeholder="사원번호" name="emp_no" autocomplete="off" class="form-control bg-transparent custom-input" />
                        </div>
                        <div class="fv-row mb-8">
                            <div class="input-group">
                                <input type="text" placeholder="이메일" name="emp_email" autocomplete="off" class="form-control bg-transparent" />
                                <button type="button" id="kt_password_reset_submit" class="btn btn-primary custom-btn">인증코드 요청</button>
                            </div>
                        </div>
                        <div class="fv-row mb-8 mail_check_wrap">
                            <input type="text" placeholder="인증코드 확인" name="auth_code" autocomplete="off" class="form-control bg-transparent mail_check_input_box disabled-field" id="mail_check_input_box_false" disabled="disabled" />
                            <div id="timer" class="timer"></div>
                        </div>
                        <div class="d-flex flex-wrap justify-content-center pb-lg-0">
                            <button type="button" id="kt_auth_code_submit" class="btn btn-primary me-4" disabled>
                                <span class="indicator-label">완료</span> 
                                <span class="indicator-progress">잠시만 기다려주세요... <span class="spinner-border spinner-border-sm align-middle ms-2"></span></span>
                            </button>
                            <a href="./loginServlet.do" class="btn btn-light">취소</a> 
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="./js/reset-password_jsj.js"></script>
<%@ include file="./lo_footer.jsp"%>
</body>
</html>
