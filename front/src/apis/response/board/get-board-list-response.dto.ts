import BoardListInterface from "../../../types/interface/board-list.interface";
import ResponseDto from "../response.dto";

export default interface GetBoardListResponseDto extends ResponseDto {
  boardPage: {
    content: BoardListInterface[];
    pageable: any;
    last: boolean;
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
    sort: any;
    first: boolean;
    numberOfElements: number;
    empty: boolean;
  };
}
