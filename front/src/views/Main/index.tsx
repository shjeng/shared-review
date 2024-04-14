import React, {useEffect, useState} from "react";
import "./style.css";
import BoardItem from "../../components/BoardItem";
import {Board} from "../../types/interface";
import {getBoardLatestList} from "../../apis";
import ResponseDto from "../../apis/response/response.dto";
import {GetBoardLatestListResponseDto} from "../../apis/response/board";
import {ResponseUtil} from "../../utils";

const Main = () => {
  const [latestBoards, setLatestBoards] = useState<Board[]>([]);
  useEffect(()=>{
    getBoardLatestList().then(getBoardLatestListResponse);
  },[])

  const getBoardLatestListResponse = (responseBody: null | ResponseDto | GetBoardLatestListResponseDto) => {
    const result = ResponseUtil(responseBody);
    if(!result){
      return;
    }
    const latestResult = result as GetBoardLatestListResponseDto;
    setLatestBoards(latestResult.boards);
  }

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
            <BoardItem/>
            <BoardItem/>
            <BoardItem/>
          </div>
        </div>

        <div className="board-middle">
          <div className="board-middle-title">이번주 인기 게시물</div>
          <div className="board-middle-item-list">
            <BoardItem/>
            <BoardItem/>
            <BoardItem/>
          </div>
        </div>

        <div className="board-bottom">
          <div className="board-bottom-title">오늘의 인기 게시물</div>
          <div className="board-bottom-item-list">
            <BoardItem/>
            <BoardItem/>
            <BoardItem/>
          </div>
        </div>

        <div className="board-bottom">
          <div className="board-bottom-title">최신 게시물</div>
          <div className="board-bottom-item-list">
            <BoardItem/>
            <BoardItem/>
            <BoardItem/>
            <BoardItem/>
            <BoardItem/>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Main;
