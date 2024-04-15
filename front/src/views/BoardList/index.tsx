import React from "react";
import "./style.css";
import { useNavigate } from "react-router-dom";
import { BOARD_WRITE } from "../../constant";
const BoardList = () => {
  const navigator = useNavigate();
  const onBoardWriteClickHandler = () => {
    navigator(BOARD_WRITE());
  };
  return (
    <div id="board-list-wrap">
      <div className="board-list-top">
        <div className="list-write-btn" onClick={onBoardWriteClickHandler}>
          <div className="list-write-btn-icon"></div>
          작성하기
        </div>
      </div>
      <div className="board-list-mid">
        <div className="board-items">
          <div className="board-item-top">
            <div className="board-item-profile">
              <div className="board-item-profile-img"></div>
              <div className="board-item-profile-information">
                <div className="board-item-profile-nickName">{`닉네임`}</div>
                <div className="board-item-profile-date">{`2024. 02. 19`}</div>
              </div>
            </div>
          </div>
          <div className="board-item-mid">{`제가 이런 고민에 빠졌습니다..`}</div>

          <div className="board-item-bottom">
            <div className="board-item-categorys">
              {`#안녕`} {`#고민`}
            </div>

            <div className="board-item-metaData">
              <div className="board-comment-box">
                <div className="board-comment-img"></div>
                <div className="comment-count">{`3`}</div>
              </div>

              <div className="board-like-box">
                <div className="board-like-img"></div>
                <div className="like-count">{`1`}</div>
              </div>

              <div className="board-view-box">
                <div className="board-view-img"></div>
                <div className="view-count">{`10`}</div>
              </div>
            </div>
          </div>
        </div>

        <div className="board-items">
          <div className="board-item-top">
            <div className="board-item-profile">
              <div className="board-item-profile-img"></div>
              <div className="board-item-profile-information">
                <div className="board-item-profile-nickName">{`닉네임`}</div>
                <div className="board-item-profile-date">{`2024. 02. 19`}</div>
              </div>
            </div>
          </div>
          <div className="board-item-mid">{`제가 이런 고민에 빠졌습니다..`}</div>

          <div className="board-item-bottom">
            <div className="board-item-categorys">
              {`#안녕`} {`#고민`}
            </div>

            <div className="board-item-metaData">
              <div className="board-comment-box">
                <div className="board-comment-img"></div>
                <div className="comment-count">{`3`}</div>
              </div>

              <div className="board-like-box">
                <div className="board-like-img"></div>
                <div className="like-count">{`1`}</div>
              </div>

              <div className="board-view-box">
                <div className="board-view-img"></div>
                <div className="view-count">{`10`}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BoardList;
