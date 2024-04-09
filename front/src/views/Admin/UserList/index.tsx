import { useNavigate } from "react-router-dom";
import "./style.css";
import { CATEGORI_MANAGE_PATH, USER_MANAGE_PATH } from "../../../constant";
import { useEffect, useRef, useState } from "react";
import { getUserList } from "../../../apis";
import GetUserListResponseDto from "../../../apis/response/user/get-user-list-response.dto";

const UserList = () => {
  const [users, setUsers] = useState<GetUserListResponseDto[]>([]);

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

  // 카테고리
  const [categoryDrop, setCategoryDrop] = useState(false);
  const searchInputRef = useRef<any>(null);
  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };
  const onDropdownCategory = () => {};

  useEffect(() => {
    const fetchData = async () => {
      const result = await getUserList();
      if (result && Array.isArray(result.userList)) {
        setUsers(result.userList);
        console.error("받은 데이터 : ", result.userList);
      } else {
        console.error("Error fetching user list:", result.userList);
      }
    };

    fetchData();
  }, []);
  return (
    <div id="userList-wrap">
      <div className="userList-top">
        <div className="userList-title">회원목록</div>
      </div>
      <div className="userList-mid">
        <div className="userList-mid-left">
          <div
            className="admin-menu-userList-bold"
            onClick={onUserListClickHandler}
          >
            회원목록
          </div>
          <div className="admin-menu-announcement">공지사항</div>
          <div className="admin-menu-post">게시글목록</div>
          <div
            className="admin-menu-category"
            onClick={onCategoriesClickHandler}
          >
            카테고리
          </div>
        </div>

        <div className="userList-mid-right">
          <div className="userList-classification">
            <div className="userList-item-check-box">
              <input type="checkbox" />
            </div>
            <div className="classification-id">ID</div>
            <div className="classification-nickName">닉네임</div>
            <div className="classification-email">이메일</div>
            <div className="classification-writerDate">가입일</div>
            <div className="classification-authority">권한</div>

            <div className="classification-actions">action</div>
          </div>

          <div className="userList-Item-box">
            {users.map((user, index) => (
              <div key={index} className="userList-Item">
                <div className="checkBox">
                  <input type="checkbox" />
                </div>
                <div className="userList-item-id">{user.id}</div>
                <div className="userList-item-nickName">{user.nickname}</div>
                <div className="userList-item-email">{user.email}</div>
                <div className="userList-item-writerDate">
                  {new Date(user.createDate).toISOString().split("T")[0]}
                  {/* 날짜형식 백에서 처리하기 */}
                </div>
                <div className="userList-item-authority">{user.admin}</div>
                <div className="userList-item-action">
                  <div className="actions-icon-img"></div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>

      <div className="userList-bottom">
        <div className="userList-bottom-top">
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

export default UserList;
