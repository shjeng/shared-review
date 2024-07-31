import React, { useEffect } from "react";
import "./style.css";
import { Board } from "../../types/interface";
import { useLocation, useNavigate } from "react-router-dom";
import { BOARD_DETAIL } from "../../constant";

interface Props {
  board: Board;
}
const BoardItem = ({ board }: Props) => {
  const navigator = useNavigate();
  const detailView = () => {
    navigator(BOARD_DETAIL(board.boardId));
  };

  return (
    <div id="board-item-wrap" onClick={detailView}>
      <div className="board-item-top-box">{/* 이미지 넣는건가 */}</div>

      <div className="board-item-middle-box">
        <div className="board-item-title">{board.title}</div>
      </div>

      <div className="board-item-bottom-box">
        <div className="board-item-profile">
          {board.user.profileImage ? (
            <div
              className="board-item-profile-image"
              style={{ backgroundImage: `url(${board.user.profileImage})` }}
            ></div>
          ) : (
            <div className="board-item-profile-image-default"></div>
          )}
          <div className="board-item-write-box">
            <div className="board-item-nickname">{board.user.nickname}</div>
            <div className="board-item-write-date">{board.updateDateTime}</div>
          </div>
        </div>

        <div className="board-item-counts">
          <div className="comment-count-box">
            <div className="comment-count-image"></div>
            <div className="comment-count">{board.commentCount}</div>
          </div>

          <div className="like-count-box">
            <div className="like-count-image"></div>
            <div className="like-count">{board.favoriteCount}</div>
          </div>

          <div className="view-count-box">
            <div className="view-count-image"></div>
            <div className="view-count">{board.viewCount}</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BoardItem;
