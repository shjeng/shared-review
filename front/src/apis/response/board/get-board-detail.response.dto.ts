import ResponseDto from "../response.dto";
import {Board, Comment, Favorite, Tag, User} from "../../../types/interface";

export default interface GetBoardDetailResponseDto extends ResponseDto{
    user: User;
    boardDetail: Board;
    comments: Comment[];
    favorites: Favorite[];
    tags: Tag[];
}