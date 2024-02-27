import React, { useState } from "react";
import "./style.css";

const Header = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const onDropdownCategory = () => {};
  return (
    <div id="header-wrap">
      <div className="header-top-box">
        <div className="header-left-box">
          <div className="header-icon"></div>
          <div className="header-logo">{"SReview"}</div>
        </div>

        <div className="header-middle-box">
          <div className="header-category">
            <div className="header-category-dropdown">
              <div className="dropdown-box" onClick={toggleDropdown}>
                <div className="dropdown_text">카테고리v</div>
              </div>
              {isOpen && (
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

        <div className="header-right-box">
          <div className="header-login-button">{"로그인/회원가입"}</div>
        </div>
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
