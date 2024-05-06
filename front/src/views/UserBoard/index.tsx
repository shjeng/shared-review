import "./style.css";
import {useEffect, useState} from "react";
import {getUserBoard} from "../../apis";
import {useParams} from "react-router-dom";
import {Board} from "../../types/interface";
import {BoardListResponse} from "../../apis/response/board";
import ResponseDto from "../../apis/response/response.dto";
import {ResponseUtil} from "../../utils";

const UserBoard = () => {
  const [boards, setBoards] = useState<Board[]>([]);
  const {userEmail} = useParams();
  useEffect(() => {
    if (!userEmail){
      alert('잘못된 접근입니다.');
      return;
    }
    getBoards(userEmail, 1);
  }, []);


  const getBoards = (userEmail: string, currentPage: number) => {
    getUserBoard(userEmail, currentPage - 1).then(getUserBoardResponse);
  }
  const getUserBoardResponse = (response: BoardListResponse | ResponseDto | null) => {
    const result = ResponseUtil(response);
    if (!result) {
      return;
    }
    const boardListResponse = result as BoardListResponse;
    setBoards(boardListResponse.boardPage.content);
  }
  const userDefinedColumns = [
    { label: "글번호", field: "id" },
    { label: "제목", field: "title" },
    { label: "카테고리", field: "category" },
    { label: "작성날짜", field: "writeDateTime" },
  ];
  return (
    <div>
      <div className="user-board-main-bottom-box">
        <div className="user-bottom-title">"{`**님`}" 게시글</div>
        {/* <SearchInputBox columns={userDefinedColumns} /> */}
        <div className="user-board-item-list">
          <div className="user-board-item-box">
            <div className="user-board-item-top">
              <div className="user-board-item-title">{`제목`}</div>
              <div className="user-board-item-comment">{`[0]`}</div>
            </div>
            <div className="user-board-item-bottom">
              <div className="user-board-item-category">{`카테고리`}</div>
              <div className="user-board-item-timeInfo">{`2024-04-30`}</div>
              <div className="user-board-item-nickname">{`testNick`}</div>
            </div>
          </div>

          <div className="user-board-item-box">
            <div className="user-board-item-top">
              <div className="user-board-item-title">{`제목`}</div>
              <div className="user-board-item-comment">{`[0]`}</div>
            </div>
            <div className="user-board-item-bottom">
              <div className="user-board-item-category">{`카테고리`}</div>
              <div className="user-board-item-timeInfo">{`2024-04-30`}</div>
              <div className="user-board-item-nickname">{`testNick`}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserBoard;
