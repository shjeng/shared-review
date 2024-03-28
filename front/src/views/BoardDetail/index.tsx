import React, {useEffect} from 'react';
import {useParams} from "react-router-dom";

const BoardDetail = () => {
    const {boardId} = useParams();
    useEffect(() => {
        alert(boardId);
    }, []);
    return (
        <div>
            게시글 상세보기
        </div>
    );
};

export default BoardDetail;