import ResponseDto from "../response.dto";
import {Comment} from "../../../types/interface";
import Pageable from "../../../types/interface/pageable.interface";

export default interface CommentWriteResponseDto extends ResponseDto{
    comments: Pageable<Comment>
}