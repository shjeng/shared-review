import React from 'react';
import "./style.css";
import {Board} from "../../types/interface";

interface Props{
    board: Board
}
const BoardItem2 = ({board}: Props) => {
    return (
        <div className="recent-board-item-box">
            <div className="recent-board-item-top">
                <div className="recent-board-item-title">
                    {board.title}
                </div>
                <div className="recent-board-item-comment"> {board.commentCount}</div>
            </div>
            <div className="recent-board-item-bottom">
                <div className="recent-board-item-category">{board.category}</div>
                <div className="recent-board-item-timeInfo">{board.updateDateTime}</div>
                <div className="recent-board-item-nickname">{board.user.nickname}</div>
            </div>
        </div>
    );
};

export default BoardItem2;