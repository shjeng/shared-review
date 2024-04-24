import ResponseDto from "../response.dto";
import {Comment} from "../../../types/interface";

export default interface CommentWriteResponseDto extends ResponseDto{
    comments: Comment[]
}