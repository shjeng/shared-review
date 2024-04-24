import { useNavigate } from "react-router-dom";
import "./style.css";
import {
  ADMIN_BOARD_LIST,
  CATEGORI_MANAGE_PATH,
  USER_MANAGE_PATH,
} from "../../../constant";
import { useEffect, useRef, useState } from "react";
import {
  getBoardListRequest,
  getCategorysReqeust,
  getUserList,
} from "../../../apis";
import GetUserListResponseDto from "../../../apis/response/user/get-user-list-response.dto";
import { Board, Category } from "../../../types/interface";
import { GetCategorysResponseDto } from "../../../apis/response/board";
import ResponseDto from "../../../apis/response/response.dto";
import GetAdminBoardResponseDto from "../../../apis/response/board/get-admin-board-list-response.dto";
import AdminBoard from "../../../types/interface/admin-board.interface";

const AdminBoardList = () => {
  const [boards, setBoards] = useState<AdminBoard[]>([]);
  const [selectAll, setSelectAll] = useState<boolean>();
  const [category, setCategory] = useState<Category | undefined>();
  const [categorys, setCategorys] = useState<Category[]>([]);

  const onCategoryClick = (category: Category) => {
    setCategory(category);
  };

  //        function: 네비게이트 함수     //
  const navigate = useNavigate();

  //      event handler: 로고 클릭 이벤트 처리 함수       //
  const onUserListClickHandler = () => {
    navigate(USER_MANAGE_PATH());
  };

  //      event handler: 카테고리 클릭 이벤트 처리 함수       //
  const onCategoriesClickHandler = () => {
    navigate(CATEGORI_MANAGE_PATH());
  };

  const onAdminBoardListClickHandler = () => {
    navigate(ADMIN_BOARD_LIST());
  };

  // 관리자 페이지(게시글목록) - 게시글 목록 요청
  useEffect(() => {
    getBoardListRequest().then(getAdminBoardListResponse);
  }, []);
  const getAdminBoardListResponse = (
    responseBody: GetAdminBoardResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) {
      alert("서버로부터 응답이 없습니다.");
      return;
    }

    const { code } = responseBody;
    console.log("BoardList code 값 : ", JSON.stringify(code, null, 2));

    if (code === "VF") alert("유효성 검사 실패");
    if (code === "DBE") alert("데이터베이스 오류");
    if (code !== "SU") {
      return;
    }
    const result = responseBody as GetAdminBoardResponseDto;
    setBoards(result.boards);
    console.log("result : ", JSON.stringify(result, null, 2)); // 객체의 구조를 확인
  };

  // =========================================================

  // 카테고리
  const [categoryDrop, setCategoryDrop] = useState(false);
  const searchInputRef = useRef<any>(null);
  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };

  // 하단 카테고리 목록
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

  // 전체 선택/해제 함수
  const toggleSelectAll = () => {
    setSelectAll(!selectAll);
    const updatedBoards = boards.map((board) => ({
      ...board,
      selected: !selectAll,
    }));
    setBoards(updatedBoards);
  };
  // 개별 체크박스 선택 함수
  const toggleSelectUser = (index: number) => {
    const updatedBoards = [...boards];
    updatedBoards[index].selected = !updatedBoards[index].selected;
    setBoards(updatedBoards);
    const allChecked = updatedBoards.every((board) => board.selected);
    setSelectAll(allChecked);
  };

  return (
    <div id="admin-wrap">
      <div className="admin-top">
        <div className="admin-title">게시글목록</div>
      </div>
      <div className="admin-mid">
        <div className="admin-mid-left">
          <div className="admin-menu-userList" onClick={onUserListClickHandler}>
            회원목록
          </div>
          <div className="admin-menu-announcement">공지사항</div>
          <div
            className="admin-menu-post-bold"
            onClick={onAdminBoardListClickHandler}
          >
            게시글목록
          </div>
          <div
            className="admin-menu-category"
            onClick={onCategoriesClickHandler}
          >
            카테고리
          </div>
        </div>

        <div className="admin-mid-right">
          <div className="admin-mid-right-top">
            <div className="admin-classification">
              <div className="admin-item-check-box">
                <input
                  type="checkbox"
                  checked={selectAll}
                  onChange={toggleSelectAll}
                />
              </div>
              <div className="classification-id">ID</div>
              <div className="classification-email">제목</div>
              <div className="classification-nickName">닉네임</div>
              <div className="classification-writerDate">작성일</div>
              <div className="classification-actions">action</div>
            </div>

            <div className="admin-Item-box">
              {boards.map((board, index) => (
                <div key={index} className="admin-Item">
                  <div className="checkBox">
                    <input
                      type="checkbox"
                      checked={board.selected || false}
                      onChange={() => toggleSelectUser(index)}
                    />
                  </div>
                  <div className="admin-item-id">{board.boardId}</div>
                  <div className="admin-item-title">{board.title}</div>
                  <div className="admin-item-nickName">
                    {board.userDto.nickname}
                  </div>
                  <div className="admin-item-writerDate">
                    {board.writeDateTime
                      ? new Date(board.writeDateTime)
                          .toISOString()
                          .split("T")[0]
                      : "Invalid Date"}
                  </div>
                  <div className="admin-item-action">
                    <div className="actions-icon-img"></div>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="admin-mid-right-bottom">
            <div className="admin-delete-btn">삭제</div>
          </div>
        </div>
      </div>

      <div className="admin-bottom">
        <div className="admin-bottom-top">
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

          <div className="admin-search">
            <input type="text" placeholder="검색어 입력" />
            <div className="admin-search-img"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminBoardList;
