import "./style.css";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getBoardRequest } from "../../apis";
import { MAIN_PATH } from "../../constant";
import { GetBoardDetailResponseDto } from "../../apis/response/board";
import ResponseDto from "../../apis/response/response.dto";
import { ResponseCode } from "../../types/enum";
import loginUserStore from "../../store/login-user.store";
import { Comment, Favorite } from "../../types/interface";

const BoardDetail = () => {
  const { boardId } = useParams();
  const navigator = useNavigate();
  const { loginUser } = loginUserStore();

  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [viewCount, setViewCount] = useState<number>(0);
  const [updateDateTime, setUpdateDateTime] = useState<string>("");
  const [comments, setComments] = useState<Comment[]>([]);
  const [favorites, setFavorites] = useState<Favorite[]>([]);

  useEffect(() => {
    if (!boardId) {
      alert("잘못된 접근입니다.");
      navigator(MAIN_PATH());
      return;
    }
    getBoardRequest(boardId).then(getBoardResponse);
  }, [boardId]);
  const getBoardResponse = (
    responseBody: GetBoardDetailResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) {
      alert("네트워크 오류");
      navigator(MAIN_PATH());
      return;
    }
    const { code } = responseBody;
    if (code === ResponseCode.NOT_EXISTED_BOARD) {
      alert("존재하지 않는 게시물입니다.");
    }
    if (code !== ResponseCode.SUCCESS) {
      navigator(MAIN_PATH());
    }
    console.log(responseBody); // 테스트용
    const result = responseBody as GetBoardDetailResponseDto;
    setTitle(result.boardDetail.title);
    setContent(result.boardDetail.content);
    setViewCount(result.boardDetail.viewCount);
    setUpdateDateTime(result.boardDetail.updateDateTime);
    setComments(result.comments);
    setFavorites(result.favorites);
  };
  return (
    <div id="board-detail-wrap">
      <div className="board-detail-content">
        <div className="board-detail-top">
          <div className="board-detail-top-left">
            <div className="board-detail-title">test게시글1</div>
            <div className="board-detail-category">[카테고리]</div>
          </div>
          <div className="board-detail-top-right">
            <div className="board-detail-create-date">2024. 04. 10. 14:45</div>
          </div>
        </div>

        <div className="board-detail-mid">
          <div className="board-detail-mid-left">
            <div className="board-detail-profile-img"></div>
            <div className="board-detail-profile-name">작성자</div>
          </div>

          <div className="board-detail-mid-right">
            <div className="board-detail-views-icon"></div>
            <div className="board-detail-views-count">5</div>
          </div>
        </div>

        <div className="board-detail-bottom">
          <div className="board-detail">제가 이런 고민에 빠졌습니다..</div>
        </div>
      </div>
      <div className="board-list-btn-container">
        <div className="board-list-btn">목록으로</div>
      </div>
    </div>
  );
};

export default BoardDetail;
