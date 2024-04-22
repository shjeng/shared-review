import "./style.css";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getBoardRequest } from "../../apis";
import { GetBoardDetailResponseDto } from "../../apis/response/board";
import { BOARD_LIST, MAIN_PATH } from "../../constant";

import ResponseDto from "../../apis/response/response.dto";
import { ResponseCode } from "../../types/enum";
import loginUserStore from "../../store/login-user.store";
import { Category, Comment, Favorite, Tag, User } from "../../types/interface";

const BoardDetail = () => {
  const { boardId } = useParams();
  const navigator = useNavigate();
  const { loginUser } = loginUserStore();

  const [title, setTitle] = useState<string>("");
  const [content, setContent] = useState<string>("");
  const [viewCount, setViewCount] = useState<number>(0);
  const [updateDateTime, setUpdateDateTime] = useState<string>("");
  const [category, setCategory] = useState<string>("");
  const [commentCount, setCommentCount] = useState<number>(0);
  const [comments, setComments] = useState<Comment[]>([]);
  const [favorites, setFavorites] = useState<Favorite[]>([]);
  const [favoriteCount, setFavoriteCount] = useState<number>(0);
  const [writer, setWriter] = useState<User>();
  const [tags, setTags] = useState<Tag[]>([]);
  const [isMyPost, setIsMyPost] = useState<boolean>(false);

  const [nicknameDrop, setNicknameDrop] = useState<boolean>(false);

  //  처름 렌더링 될 때
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
    setCategory(result.boardDetail.category);
    setComments(result.comments);
    setFavorites(result.favorites);
    setWriter(result.user);
    setTags(result.tags);
    if (writer?.email === loginUser?.email) {
      setIsMyPost(true);
    }
  };

  // 닉네임 클릭 이벤트
  const writerClickEvent = () => {
    setNicknameDrop(!nicknameDrop);
  };

  //      event handler: 회원목록 클릭 이벤트 처리 함수       //
  const onBoardListClickHandler = () => {
    navigator(BOARD_LIST());
  };

  return (
    <div id="board-detail-wrap">
      <div className="board-detail-content">
        <div className="board-detail-top">
          <div className="board-detail-top-left">
            <div className="board-detail-title">{title}</div>
            <div className="board-detail-category">{category}</div>
          </div>

          {/* <div className="board-detail-mid-left"> */}
          <div className="board-detail-top-right" onClick={writerClickEvent}>
            {writer?.profileImage ? (
              <div className="board-detail-profile-img-box">
                <div
                  className="profile-img"
                  style={{ backgroundImage: `url(${writer.profileImage})` }}
                ></div>
              </div>
            ) : (
              <div className="board-detail-profile-img"></div>
            )}
            <div className="board-detail-profile-name">{writer?.nickname}</div>
            {nicknameDrop && (
              <>
                <div className="user-information-box">
                  <div
                    className="user-information-box-child"
                    onClick={() => {}}
                  >
                    유저 글
                  </div>
                  <div className="user-information-box-child">유저 정보</div>
                </div>
              </>
            )}
          </div>
        </div>

        <div className="board-detail-mid">
          <div
            className="board-detail"
            dangerouslySetInnerHTML={{ __html: content }}
          ></div>
          <div className="border-detail-tag">
            {tags.map((tag) => (
              <div className="border-detail-tag-item" onClick={() => {}}>
                #{tag.name}
              </div>
            ))}
          </div>

          <div className="board-detail-info">
            <div className="board-detail-info-left">
              <div className="board-detail-create-date">
                {new Date(updateDateTime).toLocaleString()}
                {/* {updateDateTime} */}
              </div>
            </div>
            <div className="board-detail-info-right">
              <div className="board-detail-views-icon"></div>
              <div className="board-detail-views-count">{viewCount}</div>
            </div>
          </div>
        </div>

        <div className="board-detail-bottom">
          <div className="board-detail-interactions">
            <div className="board-detail-like">
              {favorites.findIndex(
                (favorite) => favorite.userEmail === loginUser?.email
              ) === -1 ? (
                <div className="board-deatil-like-icon"></div>
              ) : (
                <div className="board-deatil-like-icon"></div>
              )}
              <div className="board-deatil-like-count">{favoriteCount}</div>
            </div>

            <div className="board-detail-comment">
              <div className="board-detail-comment-icon"></div>
              <div className="board-detail-comment-count">{commentCount}</div>
            </div>
          </div>

          {loginUser && (
            <div className="board-detail-comment-write">
              <textarea
                className="board-detail-comment-textarea"
                placeholder="댓글을 입력해주세요."
                maxLength={255}
                rows={4}
              />
              <div className="comment-write-btn">등록</div>
            </div>
          )}

          <div className="board-detail-comment-list">
            <div className="board-detail-comment-item">
              <div className="board-comment-item-nickname">닉네임1</div>
              <div className="board-comment-item-detail">
                댓글 내용 어쩌구 저쩌구
              </div>
              <div className="board-comment-item-date">
                2024. 04. 10. 21:44:37
              </div>
            </div>

            <div className="board-detail-comment-item">
              <div className="board-comment-item-nickname">닉네임1</div>
              <div className="board-comment-item-detail">
                댓글 내용 어쩌구 저쩌구
              </div>
              <div className="board-comment-item-date">
                2024. 04. 10. 21:44:37
              </div>
            </div>
          </div>

          {/* <div className="board-detail-comment-paging">1 2</div> */}
        </div>
      </div>
      <div className="board-list-btn-container">
        <div className="board-list-btn" onClick={onBoardListClickHandler}>
          목록으로
        </div>
      </div>
    </div>
  );
};

export default BoardDetail;
