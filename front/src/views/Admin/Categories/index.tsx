import { useNavigate } from "react-router-dom";
import "./style.css";
import {
  ADMIN_BOARD_LIST,
  CATEGORI_MANAGE_PATH,
  USER_MANAGE_PATH,
} from "../../../constant";
import { useEffect, useRef, useState } from "react";
import { getCategorysReqeust, getUserList } from "../../../apis";
import GetUserListResponseDto from "../../../apis/response/user/get-user-list-response.dto";
import { GetCategorysResponseDto } from "../../../apis/response/board";
import ResponseDto from "../../../apis/response/response.dto";
import { Category } from "../../../types/interface";
import CategorieList from "../../../types/interface/categorie-list.interface";
import GetAdminCategorysResponseDto from "../../../apis/response/board/get-admin-categorys-response.dto";

const Categories = () => {
  const [users, setUsers] = useState<GetUserListResponseDto[]>([]);

  //        function: 네비게이트 함수     //
  const navigate = useNavigate();
  const onUserListClickHandler = () => {
    navigate(USER_MANAGE_PATH());
  };
  const onCategoriesClickHandler = () => {
    navigate(CATEGORI_MANAGE_PATH());
  };
  const onAdminBoardListClickHandler = () => {
    navigate(ADMIN_BOARD_LIST());
  };

  // 카테고리
  const [categoryDrop, setCategoryDrop] = useState(false);
  const searchInputRef = useRef<any>(null);
  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };
  const onDropdownCategory = () => {};

  const [categorys, setCategorys] = useState<Category[]>([]);

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

  return (
    <div id="admin-categori-wrap">
      <div className="admin-categori-top">
        <div className="admin-categori-title">카테고리</div>
      </div>
      <div className="admin-categori-mid">
        <div className="admin-categori-mid-left">
          <div className="admin-menu-userList" onClick={onUserListClickHandler}>
            회원목록
          </div>
          <div className="admin-menu-announcement">공지사항</div>
          <div
            className="admin-menu-post"
            onClick={onAdminBoardListClickHandler}
          >
            게시글목록
          </div>
          <div
            className="admin-menu-category-bold"
            onClick={onCategoriesClickHandler}
          >
            카테고리
          </div>
        </div>

        <div className="admin-categori-mid-right">
          <div className="admin-categori-mid-right-top">
            <div className="admin-categori-search">
              <input type="text" placeholder="추가 내용 입력" />
              <div className="admin-categori-add">추가하기</div>
            </div>
            <div className="admin-categori-classification">
              <div className="admin-categori-item-check-box">
                <input type="checkbox" />
              </div>
              <div className="admin-categori-id">ID</div>
              <div className="admin-categori-nickName">카테고리명</div>
              <div className="admin-categori-email">작성자</div>
              <div className="admin-categori-writerDate">작성날짜</div>

              <div className="admin-categori-actions">action</div>
            </div>

            <div className="admin-categori-Item-box">
              {categorys.map((category, index) => (
                <div key={index} className="userList-Item">
                  <div className="checkBox">
                    <input type="checkbox" />
                  </div>
                  <div className="admin-categori-item-id">
                    {category.categoryName}
                  </div>
                  <div className="admin-categori-item-nickName">
                    {category.categoryName}
                  </div>
                  <div className="admin-categori-item-email">
                    {category.categoryName}
                  </div>
                  <div className="admin-categori-item-writerDate">
                    {category.categoryName}
                    {/* {
                      new Date(categorys.categoryName)
                        .toISOString()
                        .split("T")[0]
                    } */}
                    {/* 날짜형식 백에서 처리하기 */}
                  </div>

                  <div className="admin-categori-item-action">
                    <div className="actions-icon-img"></div>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="admin-categori-mid-right-bottom">
            <div className="admin-categori-delete-btn">삭제</div>
          </div>
        </div>
      </div>

      <div className="admin-categori-bottom">
        <div className="admin-categori-bottom-top">
          <div className="header-category">
            <div className="header-category-dropdown" ref={searchInputRef}>
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

          <div className="admin-search">
            <input type="text" placeholder="검색어 입력" />
            <div className="admin-search-img"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Categories;
