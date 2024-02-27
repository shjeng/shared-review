import React from "react";
import "./style.css";

const index = () => {
  return (
    <div id="board-item-wrap">
      <div className="board-item-top-box">{/* 이미지 넣는건가 */}</div>

      <div className="board-item-middle-box">
        <div className="board-item-title">제목입니다.</div>
      </div>

      <div className="board-item-bottom-box">
        <div className="board-item-profile">
          <div className="board-item-profile-image"></div>
          <div className="board-item-write-box">
            <div className="board-item-nickname">닉네임</div>
            <div className="board-item-write-date">2014.09.09</div>
          </div>
        </div>

        <div className="board-item-counts">
          <div className="comment-count-box">
            <div className="comment-count-image"></div>
            <div className="comment-count">{`3`}</div>
          </div>

          <div className="like-count-box">
            <div className="like-count-image"></div>
            <div className="like-count">{`1`}</div>
          </div>

          <div className="view-count-box">
            <div className="view-count-image"></div>

            <div className="view-count">{`10`}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default index;
