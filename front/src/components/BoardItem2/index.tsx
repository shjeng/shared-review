import React from "react";
import "./style.css";
import { Board } from "../../types/interface";
import {useNavigate} from "react-router-dom";
import {BOARD_DETAIL} from "../../constant";

interface Props {
  board: Board;
}
const BoardItem2 = ({ board }: Props) => {
    const navigator = useNavigate();
    const detailView = () => {
        navigator(BOARD_DETAIL(board.boardId));
    };

  return (
    <div className="recent-board-item-box" onClick={detailView}>
      <div className="recent-board-item-top">
        <div className="recent-board-item-title">{board.title}</div>
          <div> / 댓글 수</div>
        <div className="recent-board-item-comment"> {board.commentCount}</div>
      </div>
      <div className="recent-board-item-bottom">
        <div className="recent-board-item-category">{board.category}</div>
        <div className="recent-board-item-timeInfo">
          {new Date(board.writeDateTime).toISOString().split("T")[0]}
        </div>
        <div className="recent-board-item-nickname">{board.user.nickname}</div>
      </div>
    </div>
  );
};

export default BoardItem2;
