import React, { useEffect, useState } from "react";
import "./style.css";
import { useNavigate } from "react-router-dom";
import { BOARD_WRITE } from "../../constant";
import { getBoardListRequest } from "../../apis";
import ResponseDto from "../../apis/response/response.dto";
import GetBoardListResponseDto from "../../apis/response/board/get-board-list-response.dto";
import BoardListInterface from "../../types/interface/board-list.interface";
const BoardList = () => {
  const navigator = useNavigate();
  const onBoardWriteClickHandler = () => {
    navigator(BOARD_WRITE());
  };

  // 게시글 목록 요청
  const [boards, setBoards] = useState<BoardListInterface[]>([]);

  useEffect(() => {
    getBoardListRequest().then(getAdminBoardListResponse);
  }, []);
  const getAdminBoardListResponse = (
    responseBody: GetBoardListResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) {
      alert("서버로부터 응답이 없습니다.");
      return;
    }

    const { code } = responseBody;
    if (code === "VF") alert("유효성 검사 실패");
    if (code === "DBE") alert("데이터베이스 오류");
    if (code !== "SU") {
      return;
    }
    const result = responseBody as GetBoardListResponseDto;
    setBoards(result.boardPage.content);
  };

  const formatDate = (dateTimeString: string): string => {
    const date = new Date(dateTimeString);
    return date.toLocaleString("ko-KR", {
      year: "numeric",
      month: "2-digit",
      day: "2-digit",
      hour: "2-digit",
      minute: "2-digit",
      hour12: false,
    });
  };

  console.log(
    "JSON.stringify(boards, null, 2) : ",
    JSON.stringify(boards, null, 2)
  ); // 객체의 구조를 확인

  return (
    <div id="board-list-wrap">
      <div className="board-list-top">
        <div className="list-write-title">게시물</div>
      </div>
      <div className="board-list-mid">
        {boards.map((board, index) => (
          <div key={index} className="board-items">
            <div className="board-item-top">
              <div className="board-item-profile">
                <div className="board-item-profile-img"></div>
                <div className="board-item-profile-information">
                  <div className="board-item-profile-nickName">
                    {board.user.nickname}
                  </div>
                  <div className="board-item-profile-date">
                    {formatDate(board.writeDateTime)}
                  </div>
                </div>
              </div>
            </div>
            <div className="board-item-mid">{board.title}</div>

            <div className="board-item-bottom">
              <div className="board-item-categorys">
                {`#안녕`} {`#고민`}
              </div>

              <div className="board-item-metaData">
                <div className="board-comment-box">
                  <div className="board-comment-img"></div>
                  <div className="comment-count">{board.commentCount}</div>
                </div>

                <div className="board-like-box">
                  <div className="board-like-img"></div>
                  <div className="like-count">{board.favoriteCount}</div>
                </div>

                <div className="board-view-box">
                  <div className="board-view-img"></div>
                  <div className="view-count">{board.viewCount}</div>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>

      <div className="board-list-bottom">
        <div className="list-write-btn" onClick={onBoardWriteClickHandler}>
          <div className="list-write-btn-icon"></div>
          작성하기
        </div>
      </div>
    </div>
  );
};

export default BoardList;
