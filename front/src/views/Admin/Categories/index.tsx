import { useNavigate } from "react-router-dom";
import "./style.css";
import {
  ADMIN_BOARD_LIST,
  CATEGORI_MANAGE_PATH,
  MAIN_PATH,
  USER_MANAGE_PATH,
  USER_PAGE_PATH,
} from "../../../constant";
import { useEffect, useRef, useState } from "react";
import {
  getAdminCategorySearchReqeust,
  getAdminCategorysReqeust,
  getCategorysReqeust,
  postCategotyAdd,
} from "../../../apis";
import ResponseDto from "../../../apis/response/response.dto";
import CategorieList from "../../../types/interface/admin-categorie.interface";
import GetAdminCategorysResponseDto from "../../../apis/response/board/get-admin-categorys-response.dto";
import SearchInputBox from "../../../components/SearchInputBox";
import { useLoginUserStore } from "../../../store";
import SearchResultsPage from "../../../components/SearchResultsPage";

const AdminCategories = () => {
  const { loginUser } = useLoginUserStore();

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

  // ===================================================
  // 백엔드 통신
  const [adminCategorys, setAdminCategorys] = useState<CategorieList[]>([]);
  useEffect(() => {
    getAdminCategorysReqeust().then(getAdminCategorysResponse);
  }, []);
  const getAdminCategorysResponse = (
    responseBody: GetAdminCategorysResponseDto | ResponseDto | null
  ) => {
    console.log("responseBody : ", responseBody);

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
    const result = responseBody as GetAdminCategorysResponseDto;

    setAdminCategorys(result.categorys);
    console.log("adminCategorys : ", adminCategorys);
  };
  // ===================================================

  // 전체 선택/해제 함수
  const [selectAll, setSelectAll] = useState<boolean>();

  const toggleSelectAll = () => {
    setSelectAll(!selectAll);
    const updatedBoards = adminCategorys.map((category) => ({
      ...category,
      selected: !selectAll,
    }));
    setAdminCategorys(updatedBoards);
  };
  // 개별 체크박스 선택 함수
  const toggleSelectUser = (index: number) => {
    const updatedBoards = [...adminCategorys];
    updatedBoards[index].selected = !updatedBoards[index].selected;
    setAdminCategorys(updatedBoards);
    const allChecked = updatedBoards.every((board) => board.selected);
    setSelectAll(allChecked);
  };

  const userDefinedColumns = [
    { label: "카테고리", field: "categoryName" },
    { label: "작성자", field: "userNickname" },
    { label: "작성날짜", field: "writeDateTime" },
  ];

  const getAdminCategorySearchResponse = (
    responseBody: GetAdminCategorysResponseDto | ResponseDto | null
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
    const result = responseBody as GetAdminCategorysResponseDto;
    if (result.categorys.length === 0) {
      alert("일치하는 데이터가 없습니다.");
      return;
    }
    setAdminCategorys(result.categorys);
  };

  const handleSearch = (searchValue: string, inputValue: string) => {
    if (searchValue === "전체") {
      getAdminCategorysReqeust().then(getAdminCategorysResponse);
      return;
    }
    getAdminCategorySearchReqeust(searchValue, inputValue).then(
      getAdminCategorySearchResponse
    );
  };

  const userInfo = (categoryId: number) => {
    // categoryId와 일치하는 카테고리 찾기
    const matchedCategory = adminCategorys.find(
      (category) => category.categoryId === categoryId
    );

    // 찾은 카테고리의 userEmail 값을 추출
    const userEmail = matchedCategory?.userEmail;

    if (userEmail) {
      navigate(USER_PAGE_PATH(userEmail));
    }
  };

  const addInputRef = useRef<HTMLInputElement>(null);
  const [addInputValue, setAddInputValue] = useState<string>("");

  const onCategoryAdd = () => {
    const inputValue = addInputRef.current?.value || "";
    setAddInputValue(inputValue);

    console.log("addInputValue : ", addInputValue);

    // postCategotyAdd(addInputValue);
    alert("추가되었습니다.");

    window.location.reload();
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
            <div className="admin-categori-feature-container">
              <SearchInputBox
                columns={userDefinedColumns}
                onSearch={(searchValue, inputValue) =>
                  handleSearch(searchValue, inputValue)
                }
              />

              <div className="admin-categori-add-container">
                <input
                  type="text"
                  placeholder="추가 내용 입력"
                  ref={addInputRef}
                />
                <div className="admin-categori-add" onClick={onCategoryAdd}>
                  추가하기
                </div>
              </div>
            </div>
            <div className="admin-categori-classification">
              <div className="admin-categori-item-check-box">
                <input
                  type="checkbox"
                  checked={selectAll}
                  onChange={toggleSelectAll}
                />
              </div>
              <div className="categori-classification-categoryId">ID</div>
              <div className="categori-classification-categoryName">
                카테고리명
              </div>
              <div className="categori-classification-userNickname">작성자</div>
              <div className="categori-classification-writeDateTime">
                작성날짜
              </div>

              {/* {userDefinedColumns.map(({ label, field }) => (
                <div key={field} className={`categori-classification-${field}`}>
                  {label}
                </div>
              ))} */}

              <div className="admin-categori-actions">action</div>
            </div>

            <div className="admin-categori-Item-box">
              {adminCategorys.map((category, index) => (
                <div key={index} className="userList-Item">
                  <div className="checkBox">
                    <input
                      type="checkbox"
                      checked={category.selected || false}
                      onChange={() => toggleSelectUser(index)}
                    />
                  </div>
                  <div className="admin-categori-item-id">
                    {category.categoryId}
                  </div>
                  <div className="admin-categori-item-title">
                    {category.categoryName}
                  </div>
                  <div
                    className="admin-categori-item-nickName"
                    onClick={() => userInfo(category.categoryId)}
                  >
                    {category.userNickname}
                  </div>
                  <div className="admin-categori-item-writerDate">
                    {
                      new Date(category.writeDateTime)
                        .toISOString()
                        .split("T")[0]
                    }
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

      <div className="admin-categori-bottom"></div>

      {/* <SearchResultsPage /> */}
    </div>
  );
};

export default AdminCategories;
