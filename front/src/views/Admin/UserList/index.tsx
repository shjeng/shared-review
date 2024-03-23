import React from "react";
import "./style.css";

const UserList = () => {
  return (
    <div id="userList-wrap">
      <div className="userList-top">
        <div className="userList-title">게시글목록</div>
      </div>
      <div className="userList-mid">
        <div className="userList-mid-left">
          <div className="admin-menu-userList">회원목록</div>
          <div className="admin-menu-announcement">공지사항</div>
          <div className="admin-menu-post">게시글목록</div>
          <div className="admin-menu-category">카테고리</div>
        </div>
        <div className="userList-mid-right">
          <div className="userList-classification">
            <div className="userList-item-check-box">
              <input type="checkbox" />
            </div>
            <div className="userList-item-id">ID</div>
            <div className="userList-item-id">Title</div>
            <div className="userList-item-id">Writer</div>
            <div className="userList-item-id">WriterDate</div>
          </div>

          <div className="userList-item">
            <div className="userList-item-check-box">
              <input type="checkbox" />
            </div>
            <div className="userList-item-id">12</div>
            <div className="userList-item-title">제가 요즘 고민이..</div>
            <div className="userList-item-writer">조성호</div>
            <div className="userList-item-writeDate">22. 02. 22</div>
            <div className="userList-item-delete"></div>
          </div>
        </div>
      </div>

      <div className="userList-bottom"></div>
    </div>
  );
};

export default UserList;
