import React, { useEffect, useState } from "react";
import "./style.css";
import { Navigate, useNavigate } from "react-router-dom";
import {
  MAIN_PATH,
  AUTH_PATH,
  SIGN_IN_PATH,
  BOARD_WRITE,
} from "../../constant";
import { useLoginUserStore } from "../../store";
import { useCookies } from "react-cookie";

const Header = () => {
  const [categoryDrop, setCategoryDrop] = useState(false);
  const [profileDrop, setprofileDrop] = useState(false);
  const { loginUser } = useLoginUserStore();
  const [cookies, setCookies] = useCookies();
  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };

  const profileDropdown = () => {
    setprofileDrop(!profileDrop);
  };

  //        function: 네비게이트 함수     //
  const navigate = useNavigate();

  //      event handler: 로고 클릭 이벤트 처리 함수       //
  const onLogoClickHandler = () => {
    navigate(MAIN_PATH());
  };

  //      event handler: 로그인 클릭 이벤트 처리 함수       //
  const onLoginClickHandler = () => {
    navigate(SIGN_IN_PATH());
  };

  //      event handler: 글 작성 페이지 이동    //

  const onDropdownCategory = () => {};
  return (
    <div id="header-wrap">
      <div className="header-top-box">
        <div className="header-left-box" onClick={onLogoClickHandler}>
          <div className="header-icon"></div>
          <div className="header-logo">{"SReview"}</div>
        </div>

        <div className="header-middle-box">
          <div className="header-category">
            <div className="header-category-dropdown">
              <div className="dropdown-box" onClick={toggleDropdown}>
                <div className="dropdown_text">카테고리</div>
                <div className="dropdown_icon"></div>
              </div>
              {categoryDrop && (
                <div className="dropdown-content">
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    2
                  </div>
                  <div
                    className="dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    oasidjf;oizsdjfo;zsdijzsd;foisjfd;szofdijzdf
                  </div>
                </div>
              )}
            </div>
          </div>
          <div className="header-search">
            <input type="text" placeholder="검색어 입력" />
            <div className="header-search-img"></div>
          </div>
        </div>
        {loginUser ? (
          <>
            <div className="header-right-box" onClick={profileDropdown}>
              <div className="profile-dropdown-box">
                <div className="header-right-box-img"></div>
                <div className="header-right-box-nickName">{`닉네임`}</div>
                <div className="header-right-box-drop"></div>
              </div>

              {profileDrop && (
                <div className="profile-dropdown-content">
                  <div
                    className="profile-dropdown-content-item"
                    onClick={() => navigate(BOARD_WRITE())}
                  >
                    글작성
                  </div>
                  <div
                    className="profile-dropdown-content-item"
                    onClick={onDropdownCategory}
                  >
                    1
                  </div>
                </div>
              )}
            </div>
          </>
        ) : (
          <div className="header-right-box" onClick={onLoginClickHandler}>
            <div className="header-login-button">{"로그인/회원가입"}</div>
          </div>
        )}
      </div>

      <div className="header-bottom-box">
        <div className="heder-bottom-item">식품</div>
        <div className="heder-bottom-item">가전제품</div>
        <div className="heder-bottom-item">가게</div>
      </div>
    </div>
  );
};

export default Header;
