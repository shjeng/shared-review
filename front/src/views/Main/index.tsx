import React from "react";
import "./style.css";
import BoardItem from "../../components/BoardItem";

const index = () => {
  return (
    <div id="main-wrap">
      <div className="main-top-box">
        <div className="main-jumbotron">
          SReview에서 <br /> 다양한 리뷰를 해주세요!
        </div>
      </div>

      <div className="main-bottom-box">
        <div className="board-top">
          <div className="board-top-title">이번달 인기 게시물</div>
          <div className="board-top-item-list">
            <BoardItem />
            <BoardItem />
            <BoardItem />
          </div>
        </div>

        <div className="board-middle">
          <div className="board-middle-title">이번주 인기 게시물</div>
          <div className="board-middle-item-list">
            <BoardItem />
            <BoardItem />
            <BoardItem />
          </div>
        </div>

        <div className="board-bottom">
          <div className="board-bottom-title">오늘의 인기 게시물</div>
          <div className="board-bottom-item-list">
            <BoardItem />
            <BoardItem />
            <BoardItem />
          </div>
        </div>
      </div>
    </div>
  );
};

export default index;
