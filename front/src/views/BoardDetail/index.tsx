import React, { useEffect } from "react";
import { useParams } from "react-router-dom";
import "./style.css";

const BoardDetail = () => {
  const { boardId } = useParams();
  useEffect(() => {}, [boardId]);

  return (
    <div id="board-detail-wrap">
      <div className="board-detail-content">
        <div className="board-detail-top">
          <div className="board-detail-top-left">
            <div className="board-detail-title">test게시글1</div>
            <div className="board-detail-category">[카테고리]</div>
          </div>
          <div className="board-detail-top-right">
            <div className="board-detail-create-date">2024. 04. 10. 14:45</div>
          </div>
        </div>

        <div className="board-detail-mid">
          <div className="board-detail-mid-left">
            <div className="board-detail-profile-img"></div>
            <div className="board-detail-profile-name">작성자</div>
          </div>

          <div className="board-detail-mid-right">
            <div className="board-detail-views-icon"></div>
            <div className="board-detail-views-count">5</div>
          </div>
        </div>

        <div className="board-detail-bottom">
          <div className="board-detail">제가 이런 고민에 빠졌습니다..</div>
        </div>
      </div>
      <div className="board-list-btn-container">
        <div className="board-list-btn">목록으로</div>
      </div>
    </div>
  );
};

export default BoardDetail;
