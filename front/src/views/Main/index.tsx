import React, {useEffect, useState} from "react";
import "./style.css";
import BoardItem from "../../components/BoardItem";
import {Board} from "../../types/interface";
import {getBoardLatestList, getFavoriteBoardTop3} from "../../apis";
import ResponseDto from "../../apis/response/response.dto";
import {ResponseUtil} from "../../utils";
import BoardListResponse from "../../apis/response/board/get-board-latest-list-response.dto";

const Main = () => {
  const [favoriteBoardTop3ForWeek, setFavoriteBoardTop3ForWeek] = useState<Board[]>([]);
  const [favoriteBoardTop3ForMonth, setFavoriteBoardTop3Month] = useState<Board[]>([]);
  const [latestBoards, setLatestBoards] = useState<Board[]>([]);
  useEffect(()=>{
    getBoardLatestList().then(getBoardLatestListResponse);
    getFavoriteBoardTop3('week').then(getFavoriteBoardTop3Response); // 일주일동안 인기 게시물
    getFavoriteBoardTop3('month').then(getFavoriteBoardTop3Response);
  },[])

  const getBoardLatestListResponse = (responseBody: null | ResponseDto | BoardListResponse) => {
    const result = ResponseUtil(responseBody);
    if(!result){
      return;
    }
    const latestResult = result as BoardListResponse;
    setLatestBoards(latestResult.boards);
  }
  const getFavoriteBoardTop3Response = (responseBody: null | ResponseDto | BoardListResponse) => {
    const result = ResponseUtil(responseBody);
    if(!result){
      return;
    }
    const resultBody = result as BoardListResponse;
    if (resultBody.condition === 'week') {
      setFavoriteBoardTop3ForWeek(resultBody.boards);
    }
    if (resultBody.condition === 'month') {
      setFavoriteBoardTop3Month(resultBody.boards);
    }
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
