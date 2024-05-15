import React, {useState} from 'react';
import './style.css';
import {Board, Tag} from "../../types/interface";

interface Props {
    board: Board;
}

const BoardItem3 = ({board}: Props) => {
    const [tags, setTags] = useState<Tag[]>([]);
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
    return (
        <div className="board-items">
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
                    {tags.map(tag =>
                        <>
                            {tag.name}
                        </>
                    )}

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
    );
};

export default BoardItem3;