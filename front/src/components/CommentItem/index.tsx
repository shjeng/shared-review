import React, { useEffect, useState } from "react";
import "./style.css";
import { Comment } from "../../types/interface";
import loginUserStore from "../../store/login-user.store";
import moment from "moment";
import { useNavigate } from "react-router-dom";
import { USER_BOARD } from "../../constant";

interface Props {
  comment: Comment;
}

const CommentItem = ({ comment }: Props) => {
  const navigator = useNavigate();

  const [createDateTime, setCreateDateTime] = useState<string | null>("");
  const timeFormat = moment(comment.createDateTime).format(
    "YYYY. MM. DD HH:mm:ss"
  );

  useEffect(() => {
    setCreateDateTime(timeFormat);
  }, []);

  const userBoard = () => {
    navigator(USER_BOARD(comment.user.email));
  };
  const { loginUser } = loginUserStore();
  return (
    <div className="comment-item-wrap">
      <div className="comment-item-top">
        <div className="comment-user-box" onClick={userBoard}>
          {comment.user.profileImage ? (
            <div className="comment-profile-img-box pointer">
              <div
                className="profile-img"
                style={{ backgroundImage: `url(${comment.user.profileImage})` }}
              ></div>
            </div>
          ) : (
            <div className="board-detail-profile-img pointer"></div>
          )}
          <div className="comment-item-nickname pointer">
            {comment.user.nickname}
          </div>
        </div>
        <div className="height-line-box">
          <div className="height-line"></div>
        </div>
        <div className="comment-item-date">{createDateTime}</div>
        <div className="pointer">삭제버튼 자리</div>
      </div>

      <div className="comment-item-detail">{comment.content}</div>
    </div>
  );
};

export default CommentItem;
