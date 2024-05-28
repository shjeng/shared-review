import React, {useEffect} from "react";
import "./style.css";
import {useParams} from "react-router-dom";
import {getLoginUser} from "../../apis";
import {GetUserResponseDto} from "../../apis/response/user";
import ResponseDto from "../../apis/response/response.dto";

const UserPage = () => {
  const {userEmail} = useParams();

  useEffect(() => {
    if (!userEmail) {
      return;
    }
    getLoginUser(userEmail).then(getMyInfoResponse);
  }, [userEmail]);

  const getMyInfoResponse = (response: GetUserResponseDto | ResponseDto | null) => {
    console.log(response);
  }
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
