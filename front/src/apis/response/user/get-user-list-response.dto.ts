import UserList from "../../../types/interface/user-list.interface";
import ResponseDto from "../response.dto";

export default interface GetUserListResponseDto extends ResponseDto {
  id: number;
  nickname: string;
  email: string;
  createDate: string;
  admin: boolean;
  userList: UserList[];
  // userList만 남겨두기
}
