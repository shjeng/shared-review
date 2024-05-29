import React, { useEffect, useRef, useState } from "react";
import "./style.css";
import { useNavigate } from "react-router-dom";
import {
  BOARD_LIST,
  BOARD_WRITE,
  MAIN_PATH,
  SIGN_IN_PATH,
  USER_PAGE_PATH,
} from "../../constant";
import { useBoardSearchStore, useLoginUserStore } from "../../store";
import { useCookies } from "react-cookie";
import { getCategorysReqeust } from "../../apis";
import { GetCategorysResponseDto } from "../../apis/response/board";
import ResponseDto from "../../apis/response/response.dto";
import { Category } from "../../types/interface";

const Header = () => {
  const navigator = useNavigate();
  const [categoryDrop, setCategoryDrop] = useState(false);
  const [profileDrop, setprofileDrop] = useState(false);
  const { loginUser } = useLoginUserStore();
  const { categoryId, setCategoryId } = useBoardSearchStore();
  const [cookies, setCookies] = useCookies();
  const searchDropRef = useRef<any>(null);
  const [categorys, setCategorys] = useState<Category[]>([]);
  const [category, setCategory] = useState<Category | undefined>();

  const onCategoryClick = (category: Category) => {
    setSearch(category.categoryName);
    setCategoryDrop(false);
  };
  const handleClickOutside = (e: MouseEvent) => {
    if (
      categoryDrop &&
      searchDropRef.current &&
      !searchDropRef.current.contains(e.target as Node)
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

  // 카테고리로 게시물 목록 불러오기
  const categoryBoardList = (category: Category) => {
    setCategoryId(category.categoryId);
    navigator(BOARD_LIST());
  };
  // const categoryBoardList

  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };

  const profileDropdown = () => {
    setprofileDrop(!profileDrop);
  };

  //        function: 네비게이트 함수     //
  const navigate = useNavigate();

  const myPage = () => {
    if (!loginUser) {
      return;
    }
    navigator(USER_PAGE_PATH(loginUser.email));
  };
  //      event handler: 로고 클릭 이벤트 처리 함수       //
  const onLogoClickHandler = () => {
    navigate(MAIN_PATH());
  };

  //      event handler: 로그인 클릭 이벤트 처리 함수       //
  const onLoginClickHandler = () => {
    navigate(SIGN_IN_PATH());
  };

  const [inputValue, setInputValue] = useState<string>("");

  const [search, setSearch] = useState<string>("카테고리");

  const searchInputRef = useRef<any>(null);

  const onCategorySearch = () => {
    const inputValue = searchInputRef.current?.value || "";
    setInputValue(inputValue);
    if (inputValue.length === 0) {
      alert("검색어를 입력해주세요.");
      return;
    } else if (search == "카테고리") {
      alert("검색기준을 선택해주세요.");
      return;
    }
  };

  return (
    <div id="header-wrap">
      <div className="header-top-box">
        <div className="header-left-box" onClick={onLogoClickHandler}>
          <div className="header-icon"></div>
          <div className="header-logo">{"SReview"}</div>
        </div>

        <div className="header-middle-box">
          <div className="header-category">
            <div className="header-category-dropdown" ref={searchDropRef}>
              <div className="dropdown-box" onClick={toggleDropdown}>
                {/* <div className="dropdown_text">카테고리</div> */}
                <div className="dropdown_text">{search}</div>
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
            <input type="text" placeholder="검색어 입력" ref={searchInputRef} />
            <div className="header-search-img" onClick={onCategorySearch}></div>
          </div>
        </div>
        {loginUser ? (
          <>
            <div className="header-right-box" onClick={profileDropdown}>
              <div className="profile-dropdown-box">
                {loginUser.profileImage ? (
                  <div
                    className="header-right-box-img"
                    style={{
                      backgroundImage: `url(${loginUser.profileImage})`,
                    }}
                  ></div>
                ) : (
                  <div className="header-right-box-img-default"></div>
                )}

                <div className="header-right-box-nickName">
                  {loginUser.nickname}
                </div>
                <div className="header-right-box-drop"></div>
              </div>

              {profileDrop && (
                <div className="profile-dropdown-content">
                  <div
                    className="profile-dropdown-content-item"
                    onClick={myPage}
                  >
                    마이페이지
                  </div>
                  <div
                    className="profile-dropdown-content-item"
                    onClick={() => navigate(BOARD_WRITE())}
                  >
                    글작성
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
        {categorys.map((category, index) => (
          <div
            className="heder-bottom-item"
            onClick={() => categoryBoardList(category)}
          >
            {category.categoryName}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Header;
