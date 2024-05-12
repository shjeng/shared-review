import "./style.css";
import React, {useEffect, useState} from "react";
import {getUserBoard} from "../../apis";
import {useParams} from "react-router-dom";
import {Board} from "../../types/interface";
import {BoardListResponse} from "../../apis/response/board";
import ResponseDto from "../../apis/response/response.dto";
import {ResponseUtil} from "../../utils";
import BoardItem2 from "../../components/BoardItem2";
import Pagination from "../../components/Pagination";
import usePagination from "../../hooks/pagination.hook";

const UserBoard = () => {
  const countPerPage = 15;
  const [boards, setBoards] = useState<Board[]>([]);
  const {startPage, endPage, currentPage, pageList, currentSection, totalSection
    , setCurrentPage, setCurrentSection, setTotalCount, setCountPerItem}
      = usePagination(countPerPage);
  const {userEmail} = useParams();
  useEffect(() => {
    if (!userEmail){
      alert('잘못된 접근입니다.');
      return;
    }
    getBoards(userEmail, 1);
  }, []);
  const pageButtonClick = () => {
    if (!userEmail){
      return;
    }
    getBoards(userEmail, currentPage);
  }

  const getBoards = (userEmail: string, currentPage: number) => {
    getUserBoard(userEmail, currentPage - 1).then(getUserBoardResponse);
  }
  const getUserBoardResponse = (response: BoardListResponse | ResponseDto | null) => {
    console.log(response);
    const result = ResponseUtil(response);
    if (!result) {
      return;
    }
    const boardListResponse = result as BoardListResponse;
    setBoards(boardListResponse.boardPage.content);
    setTotalCount(boardListResponse.boardPage.totalElements);
    setCountPerItem(boardListResponse.boardPage.size);
    setCurrentPage(boardListResponse.boardPage.pageable.pageNumber + 1);
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
          {boards.map(board =>
              <BoardItem2 board={board} />
          )}
        </div>
        <Pagination currentPage={currentPage} currentSection={currentSection} setCurrentPage={setCurrentPage} totalSection={totalSection} countPerPage={countPerPage} pageList={pageList} pageClick={pageButtonClick}></Pagination>
      </div>
    </div>
  );
};

export default UserBoard;
