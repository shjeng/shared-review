import React from "react";
import "./style.css";

const UserPage = () => {
  return (
    <div id="userPage-wrap">
      <div className="userPage-box">
        <div className="userPage-top">
          <div className="userPage-title">프로필</div>
        </div>
        <div className="userPage-mid">
          <div className="user-img"></div>
          <div className="user-information">
            <div className="user-email">이메일</div>
            <div className="user-id">아이디</div>
            <div className="user-nickName">닉네임</div>
            <div className="user-birthDate">생년월일</div>
          </div>
        </div>
        <div className="userPage-bottom">
          <div className="userPage-intro">
            여러분들에게 도움이 되는 리뷰를 남기고자 가입하였습니다.
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserPage;
