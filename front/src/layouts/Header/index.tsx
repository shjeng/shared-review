import React, { useEffect, useRef, useState } from "react";
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
import { getCategorysReqeust } from "../../apis";
import { GetCategorysResponseDto } from "../../apis/response/board";
import ResponseDto from "../../apis/response/response.dto";
import { Category } from "../../types/interface";

const Header = () => {
  const [categoryDrop, setCategoryDrop] = useState(false);
  const [profileDrop, setprofileDrop] = useState(false);
  const { loginUser } = useLoginUserStore();
  const [cookies, setCookies] = useCookies();
  const searchInputRef = useRef<any>(null);
  const [categorys, setCategorys] = useState<Category[]>([]);
  const [category, setCategory] = useState<Category | undefined>();

  const onCategoryClick = (category: Category) => {
    setCategory(category);
  };
  const handleClickOutside = (e: MouseEvent) => {
    if (
      categoryDrop &&
      searchInputRef.current &&
      !searchInputRef.current.contains(e.target as Node)
    ) {
      setCategoryDrop(false);
    }
  };
  useEffect(() => {
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [categoryDrop]);

  // =========================================================
  // Effect: 처음 렌더링 시 카테고리를 가져와줌.
  useEffect(() => {
    getCategorysReqeust().then(getCategorysResponse);
  }, []);
  const getCategorysResponse = (
    responseBody: GetCategorysResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) {
      alert("서버로부터 응답이 없습니다.");
      return;
    }
    const { code } = responseBody;
    if (code === "VF") alert("유효성 검사 실패");
    if (code === "DBE") alert("데이터베이스 오류");
    if (code !== "SU") {
      return;
    }
    const result = responseBody as GetCategorysResponseDto;
    setCategorys(result.categorys);
  };

  // =========================================================

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
            <div className="header-category-dropdown" ref={searchInputRef}>
              <div className="dropdown-box" onClick={toggleDropdown}>
                <div className="dropdown_text">카테고리</div>
                <div className="dropdown_icon"></div>
              </div>
              {categoryDrop && (
                <div className="dropdown-content">
                  {categorys.map(
                    (
                      category,
                      index // 카테고리 목록 불러오기.
                    ) => (
                      <div
                        className="board-dropdown-content-item"
                        onClick={() => onCategoryClick(category)}
                      >
                        {category.categoryName}
                      </div>
                    )
                  )}
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
            <div className="header-login-button">{"로그인 / 회원가입"}</div>
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
