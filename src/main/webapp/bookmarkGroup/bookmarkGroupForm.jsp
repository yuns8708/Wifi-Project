<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>와이파이 정보 구하기</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            text-align: center;
        }

        table td,
        table th {
            border: 1px solid #ddd;
            padding: 15px;
            
        }

        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        table tr:hover {
            background-color: #ddd;
        }

        table th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            color: white;
        }

        div {
            margin-bottom: 15px;
            position: relative;
        }

        div>a:not(:last-child) {
            margin-right: 14px;
        }

        div>a:not(:last-child)::after {
            content: "";
            position: absolute;
            top: 2px;
            bottom: 2px;
            width: 2px;
            margin-left: 7px;
            background-color: grey;
        }

        form {
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
    <h1>와이파이 정보 구하기</h1>
    <div>
        <a href="../index.jsp">홈</a>
        <a href="../history/history.jsp">위치 히스토리 목록</a>
        <a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
        <a href="../bookmark/bookmark.jsp">즐겨 찾기 보기</a>
        <a href="bookmarkGroup.jsp">즐겨 찾기 그룹 관리</a>
    </div>
    
    <table>
        <form action="createBookmarkGroup.jsp" method="post">
            <tbody>
                <tr>
                    <th>북마크 이름</th>
                    <td>
                        <input type="text" name="bookmark-name" autocomplete='off'>
                    </td>
                </tr>
                <tr>
                    <th>순서</th>
                    <td>
                        <input type="text" name="bookmark-order" autocomplete='off'>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit">추가</button>
                    </td>
                </tr>
            </tbody>
        </form>

    </table>

</body>
</html>