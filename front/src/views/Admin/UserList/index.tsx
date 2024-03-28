import { useNavigate } from "react-router-dom";
import "./style.css";
import { USER_LIST_PATH } from "../../../constant";
import { useRef, useState } from "react";

const UserList = () => {
  //        function: 네비게이트 함수     //
  const navigate = useNavigate();

  //      event handler: 로고 클릭 이벤트 처리 함수       //
  const onUserListClickHandler = () => {
    navigate(USER_LIST_PATH());
  };

  // 카테고리
  const [categoryDrop, setCategoryDrop] = useState(false);
  const searchInputRef = useRef<any>(null);
  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };
  const onDropdownCategory = () => {};

  return (
    <div id="userList-wrap">
      <div className="userList-top">
        <div className="userList-title">회원목록</div>
      </div>
      <div className="userList-mid">
        <div className="userList-mid-left">
          <div className="admin-menu-userList">회원목록</div>
          <div className="admin-menu-announcement">공지사항</div>
          <div className="admin-menu-post" onClick={onUserListClickHandler}>
            게시글목록
          </div>
          <div className="admin-menu-category">카테고리</div>
        </div>

        <div className="userList-mid-right">
          <div className="userList-classification">
            <div className="userList-item-check-box">
              <input type="checkbox" />
            </div>
            <div className="classification-id">ID</div>
            <div className="classification-nickName">닉네임</div>
            <div className="classification-name">사용자 이름</div>
            <div className="classification-email">사용자 이메일</div>
            <div className="classification-writerDate">등록일</div>
            <div className="classification-authority">권한</div>

            <div className="classification-actions">action</div>
          </div>

          <div className="userList-Item">
            <div className="checkBox">
              <input type="checkbox" />
            </div>
            <div className="userList-item-id">{"99"}</div>
            <div className="userList-item-nickName">{"가나다라마"}</div>
            <div className="userList-item-name">{"박한규"}</div>
            <div className="userList-item-email">
              {"sprgmsrl0033@email.com"}
            </div>
            <div className="userList-item-writerDate">22. 02. 22</div>

            <div className="userList-item-authority">관리자9</div>

            <div className="userList-item-action">
              <div className="actions-icon-img"></div>
            </div>
          </div>

          <div className="userList-Item">
            <div className="checkBox">
              <input type="checkbox" />
            </div>
            <div className="userList-item-id">{"99"}</div>
            <div className="userList-item-nickName">{"가나다라마"}</div>
            <div className="userList-item-name">{"박한규"}</div>
            <div className="userList-item-email">
              {"sprgmsrl0033@email.com"}
            </div>
            <div className="userList-item-writerDate">22. 02. 22</div>

            <div className="userList-item-authority">관리자9</div>

            <div className="userList-item-action">
              <div className="actions-icon-img"></div>
            </div>
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

          <div className="userList-search">
            <input type="text" placeholder="검색어 입력" />
            <div className="userList-search-img"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserList;
