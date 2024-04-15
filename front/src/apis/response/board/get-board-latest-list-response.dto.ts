import {Board} from "../../../types/interface";

export default interface BoardListResponse {
    condition: string | null;
    boards: Board[];
}